
    /**  
    * @Title: OrderAction.java
    * @Package com.cza.web.login
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月9日上午10:19:45
    * @version V1.0  
    */
    
package com.cza.web.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.util.AliPayUtils;
import com.cza.common.MygoUtil;
import com.cza.common.Pager;
import com.cza.common.Param;
import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TArea;
import com.cza.dto.addr.TUserAddr;
import com.cza.service.goods.GoodsService;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderDetailVo;
import com.cza.service.order.vo.OrderVo;
import com.cza.service.user.AddrService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: OrderAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月9日上午10:19:45
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("/login/order")
public class OrderAction extends CommonAction{
	@Autowired
	private AddrService addrService;
	@Autowired
	private OrderService orderService;
	private static final Logger log = LoggerFactory.getLogger(OrderAction.class); 
	
	
	/**
	 * 
	    * @Title: toPreOrderPage
	    * @Description: 跳转订单生成页面
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("savePreOrder")
	public String savePreOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVo userVo=getUser(request);
			log.info("OrderAction.makePreOrder 请求参数:{}",order);
			//参数校验
			if(order.getDetailVos()==null||order.getDetailVos().size()<=0){
				log.info("OrderAction.makePreOrder 订单详细不能为空");
				return erroPage(ShoppingContants.RESP_CODE_PARAM_ERRO);
			}
			for(OrderDetailVo detail:order.getDetailVos()){
				if(MygoUtil.hasZeroNull(detail.getSid(),detail.getNumber())){
					log.info("OrderAction.makePreOrder 订单详细里面的sid或者number不能为空");
					return erroPage(ShoppingContants.RESP_CODE_PARAM_ERRO);
				}
			}
			order.setUid(userVo.getUid());
			order.setUserName(userVo.getUserName());
			ServiceResponse<OrderVo> resp=orderService.savePreOrder(order);
			return webAction("/login/order/preOrderPage", new Param("oid", resp.getData().getOid()), new Param("type",order.getType()));
		} catch (Exception e) {
			log.info("OrderAction.makePreOrder exception:",e);
			return erroPage(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
	}
	
	
	@RequestMapping("preOrderPage")
	public String preOrderPage(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response){
		UserVo userVo=getUser(request);
		ServiceResponse<OrderVo> resp=orderService.queryOrder(order.getOid());
		if(resp.isSuccess()){
			OrderVo orderVo=resp.getData();
			orderVo.setType(order.getType());
			request.setAttribute("order",orderVo );
		}else{
			log.info("OrderAction.queryOrder faild!");
			if(ShoppingContants.RESP_CODE_SYSTEM_ERRO.equals(resp.getCode())){
				return erroPage(resp.getCode());
			}else{
				return orderErro(resp.getCode());
			}
		}
		//查处找所有省份（一级下拉框）
		TArea area=new TArea();
		area.setPaid(0l);
		ServiceResponse<List<TArea>>areaResp=addrService.listAreas(area);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(areaResp.getCode())){
			List<TArea> provinces=areaResp.getData();
			request.setAttribute("provinces", provinces);
		}else{
			return erroPage( areaResp.getCode());
		}
		//获取地址
		TUserAddr addrParam=new TUserAddr();
		addrParam.setUid(userVo.getUid());
		ServiceResponse<List<TUserAddr>> addrResp=addrService.listAddrs(addrParam);
		if(addrResp.isSuccess()){
			request.setAttribute("addrs", addrResp.getData());
		}else{
			return erroPage( addrResp.getCode());
		}
		return webPage("/home/preOrderPage");
	}
	
	@RequestMapping("confirmOrder")
	public String confirmOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ){
		UserVo userVo=getUser(request);
		log.info("OrderAction.saveOrder 请求参数,order:{}",order);
		//参数校验
		if(MygoUtil.hasZeroNull(order.getAddrId())){
			log.info("OrderAction.saveOrder 参数错误,地址为空！");
			return erroPage( ShoppingContants.RESP_CODE_PARAM_ERRO);
		}
		order.setUid(userVo.getUid());;
		ServiceResponse<OrderVo> resp=orderService.confirmOrder(order);
		if(resp.isSuccess()){
			log.info("OrderAction.saveOrder success,orderNo:{}",resp.getData().getOid());
			return webAction("/login/order/toOrderPayPage", new Param("oid",resp.getData().getOid()));
		}else{
			log.info("OrderAction.saveOrder faild!");
			if(ShoppingContants.RESP_CODE_SYSTEM_ERRO.equals(resp.getCode())){
				return erroPage(resp.getCode());
			}else{
				return orderErro(resp.getCode());
			}
		}
	}
	
	/**
     * @Title: toOrderPayPage
     * @Description: 跳转到系统付款页面
	 */
	@RequestMapping("toOrderPayPage")
	public String toOrderPayPage(HttpServletRequest request,HttpServletResponse response ){
		String str=request.getParameter("oid");
		if(StringUtils.isEmpty(str)){
			return erroPage( ShoppingContants.RESP_CODE_PARAM_ERRO);
		}
		ServiceResponse<OrderVo> resp=orderService.queryOrder(str);
		if(resp.isSuccess()){
			request.setAttribute("order", resp.getData());
			return webPage("/home/orderPayPage");
		}else{
			log.info("OrderAction.toOrderPayPage faild!");
			return erroPage(resp.getCode());
		}
	}
	
	/**
	 * 
     * @Title: toAliPayPage
     * @Description: 跳转到支付宝支付页面
	 */
	@RequestMapping("toAliPayPage")
	public String toAliPayPage(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ) throws UnsupportedEncodingException, AlipayApiException{
		log.info("toAliPayPage param:{}",order);
		ServiceResponse<String> resp=AliPayUtils.aliPayPage(order.getOid(),order.getAmount(),order.getOrderName());
		if(resp.isSuccess()){
			request.setAttribute("result", resp.getData());
			return webPage("/home/pay");
		}else{
			return erroPage(resp.getCode());
		}
	}
	
	
	/**
	 * @Title: notifyPayResult
	 * @Description: 支付宝同步通知用户支付结果
	   	用户支付完后，收到redirect请求，跳转到当前action
	 */
	@RequestMapping("toPayResultPage")
	@SuppressWarnings("unchecked")
	public String toPayResultPage(HttpServletRequest request,HttpServletResponse response ) throws UnsupportedEncodingException, AlipayApiException{
		request.setAttribute("payResult","failed");//设置默认为失败
		ServiceResponse<Boolean> validateResp=AliPayUtils.validatePayResult(request.getParameterMap());
		if(validateResp.isSuccess()){//方法执行成工
			if(validateResp.getData()){//验证成功
				request.setAttribute("oid", request.getParameter("out_trade_no"));
				request.setAttribute("amount", request.getParameter("total_amount"));
				request.setAttribute("payNo", request.getParameter("trade_no"));
				request.setAttribute("payResult","success");//设置通知结果为成功
			}
		}
		return webPage("/home/payResult");
	}
	
	/**
	 * @Title: notifyPayResult
	 * @Description: 支付宝异步通知服务器支付结果
	   	实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
	 */
	@RequestMapping("notifyPayResult")
	@SuppressWarnings("unchecked")
	public void notifyPayResult(HttpServletRequest request,HttpServletResponse response ) throws IOException, AlipayApiException{
		ServiceResponse<Boolean> validateResp=AliPayUtils.validatePayResult(request.getParameterMap());
		if(validateResp.isSuccess()){//方法执行成功
			if(validateResp.getData()) {//验证成功
				//商户订单号
				String oid = request.getParameter("out_trade_no");
				String amount= request.getParameter("total_amount");
				//支付宝交易号
				String trade_no =request.getParameter("trade_no");
				//交易状态
				String trade_status =request.getParameter("trade_status");
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				}else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					OrderVo order=new OrderVo();
					order.setOid(oid);
					order.setPayNo(trade_no);
					order.setAmount(new BigDecimal(amount));
					ServiceResponse<OrderVo> payResp=orderService.orderPay(order);
					if(payResp.isSuccess()){
						log.info("订单支付状态更新成功,oid:",oid);
					}else{
						//状态变更失败，退款
						ServiceResponse<Boolean> refundResp=AliPayUtils.refund(oid,trade_no,new BigDecimal(amount));
						if(refundResp.isSuccess()&&refundResp.getData()){
							log.info("系统发起退款成功,oid:{}!",oid);
						}else{
							log.info("系统发起退款失败,oid:{}!",oid);
						}
					}
				}
				response.getWriter().println("success");
				return;
			}
		}
		response.getWriter().println("fail");
		
	}
	
	
	/**
	 * 
     * @Title: toRefund
     * @Description: 用户发起退款，同步收到退款结果
	 */
	@RequestMapping("toRefund")
	public void toRefund(HttpServletRequest request,HttpServletResponse response ) throws IOException{
		String oid=request.getParameter("oid");
		log.info("toRefund oid:{}",oid);
		if(!StringUtils.isEmpty(oid)){
			ServiceResponse<OrderVo> resp=orderService.queryOrder(oid);
			if(resp.isSuccess()){
				final OrderVo order=resp.getData();
				ServiceResponse<Boolean> refundResp=AliPayUtils.refund(order.getOid(), order.getPayNo(), order.getAmount());
				if(refundResp.isSuccess()){//方法执行成功
					if(refundResp.getData()){//退款成功
						OrderVo refundParam=new OrderVo();
						refundParam.setOid(order.getOid());
						orderService.orderRefund(refundParam);
						response.getWriter().println(new RespMsg("success", null).toJson());
						return;
					}
				}
			}
		}
		response.getWriter().println(new RespMsg("fail", null).toJson());
	}
	
	
	@RequestMapping("listOrder")
	public String listOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ){
		UserVo userVo=getUser(request);
		log.info("OrderAction.listPayOrder 请求参数,order:{}",order);
		order.setUid(userVo.getUid());
		request.setAttribute("order", order);
		ServiceResponse<Pager<OrderVo>> resp=orderService.listOrder(order);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("pager", resp.getData());
			log.info("OrderAction.listPayOrder success,orders:{}",resp.getData());
			return webPage("user/listOrder");
		}else{
			log.info("OrderAction.listNotPayOrder faild!");
			return erroPage( resp.getCode());
		}
	}

	@RequestMapping("deleteOrder")
	public void deleteOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ) throws IOException{
		log.info("OrderAction.deleteOrder 请求参数,order:{}",order);
		order.setStatus(ShoppingContants.ORDER_STATUS_USER_DELETE);
		ServiceResponse<OrderVo> resp=orderService.closeOrder(order);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			log.info("OrderAction.deleteOrder success,orderNo:{}",order.getOid());
			response.getWriter().println(new RespMsg("success", null).toJson());
		}else{
			log.info("OrderAction.deleteOrder faild!");
			response.getWriter().println(new RespMsg("fail", null).toJson());
		}
	}
}
