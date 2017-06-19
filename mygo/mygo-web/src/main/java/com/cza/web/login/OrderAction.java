
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cza.common.PropertyUtil;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TUserAddr;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.SkuVo;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderVo;
import com.cza.service.order.vo.PreOrderVo;
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
	private GoodsService goodsService;
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
	@RequestMapping("toMakeOrderPage")
	public String toMakeOrderPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserVo userVo=getUser(request);
		Long sid=Long.valueOf(request.getParameter("sid"));
		Long number=Long.valueOf(request.getParameter("number"));
		log.info("OrderAction.toMakeOrderPage 请求参数,sid:{},number:{}",sid,number);
		PreOrderVo order=new PreOrderVo();
		order.setNumber(number);
		//获取sku信息
		ServiceResponse<SkuVo> skuResp=goodsService.querySku(sid);
		if(skuResp.isSuccess()){
			SkuVo sku=skuResp.getData();
			order.setSku(sku);
			order.setAmount(sku.getPrice().multiply(new BigDecimal(order.getNumber())));
		}else{
			return erroPage(request, skuResp.getCode());
		}
		//获取默认地址
		TUserAddr addrParam=new TUserAddr();
		addrParam.setUid(userVo.getUid());
		addrParam.setIsDefault(ShoppingContants.ADDR_IS_DEFAULT);		
		ServiceResponse<List<TUserAddr>> addrResp=addrService.listAddrs(addrParam);
		if(addrResp.isSuccess()){
			List<TUserAddr> addrs=addrResp.getData();
			if(addrs!=null&&addrs.size()>0){
				order.setAddr(addrs.get(0));
			}
		}else{
			return erroPage(request, skuResp.getCode());
		}
		log.info("OrderAction.toMakeOrderPage 响应参数，order:{}",order);
		request.setAttribute("order", order);
		return webPage("/home/preOrder");
	}
	
	@RequestMapping("saveOrder")
	public String saveOrder(@ModelAttribute PreOrderVo order,HttpServletRequest request,HttpServletResponse response ){
		log.info("OrderAction.saveOrder 请求参数,order:{}",order);
		if(order.getSkuId()==null||order.getAddrId()==null||order.getNumber()==null){
			log.info("OrderAction.saveOrder 参数错误！");
			return erroPage(request, ShoppingContants.RESP_CODE_PARAM_ERRO);
		}else{
			UserVo userVo=getUser(request);
			order.setUid(userVo.getUid());
			ServiceResponse<PreOrderVo> resp=orderService.saveOrder(order);
			if(resp.isSuccess()){
				log.info("OrderAction.saveOrder success,orderNo:{}",order.getOrderId());
				return "redirect:/login/order/toOrderPayPage.do?oid="+resp.getData().getOrderId();
			}else{
				log.info("OrderAction.saveOrder faild!");
				return erroPage(request, resp.getCode());
			}
		}
	}
	
	@RequestMapping("toOrderPayPage")
	public String toOrderPayPage(HttpServletRequest request,HttpServletResponse response ){
		String str=request.getParameter("oid");
		if(StringUtils.isEmpty(str)){
			return erroPage(request, ShoppingContants.RESP_CODE_PARAM_ERRO);
		}
		ServiceResponse<OrderVo> resp=orderService.queryOrder(Long.valueOf(str));
		if(resp.isSuccess()){
			request.setAttribute("order", resp.getData());
			return webPage("/home/orderPayPage");
		}else{
			log.info("OrderAction.toOrderPayPage faild!");
			return erroPage(request, resp.getCode());
		}
	}
	
	@RequestMapping("toPay")
	public String toPay(HttpServletRequest request,HttpServletResponse response ) throws UnsupportedEncodingException, AlipayApiException{
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_GATEWAY_URL), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_APP_ID), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PRIVATE_KEY), "json",(String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE));
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_RETURN_URL));
		alipayRequest.setNotifyUrl((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_NOTIFY_URL));
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		//商品描述，可空
		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		//请求
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			request.setAttribute("result", result);
			return webPage("/home/pay");
		} catch (Exception e) {
			log.info("OrderAction.toPay erro",e);
			return erroPage(request, ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		
	}
	
	@RequestMapping("toPayResultPage")
	public String toPayResultPage(HttpServletRequest request,HttpServletResponse response ) throws UnsupportedEncodingException, AlipayApiException{
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		log.info("OrderAction.updatePayStatus params:{}",params);
		boolean signVerified = AlipaySignature.rsaCheckV1(params, (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE)); //调用SDK验证签名
		log.info("OrderAction.updatePayStatus public_key:{},charset:{},sign_type:{},signVerified:{}",PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY),PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE),signVerified);
		
		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			request.setAttribute("oid", out_trade_no);
			request.setAttribute("amount", total_amount);
			request.setAttribute("payNo", trade_no);
			request.setAttribute("payResult","success");
		}else {
			request.setAttribute("payResult","failed");
		}
		return webPage("/home/payResult");
	}
	
	
	@RequestMapping("notifyPayResult")
	public void notifyPayResult(HttpServletRequest request,HttpServletResponse response ) throws IOException, AlipayApiException{
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		@SuppressWarnings("unchecked")
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		log.info("OrderAction.updatePayStatus params:{}",params);
		boolean signVerified = AlipaySignature.rsaCheckV1(params, (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE)); //调用SDK验证签名
		log.info("OrderAction.updatePayStatus public_key:{},charset:{},sign_type:{},signVerified:{}",PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY),PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE),signVerified);
		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
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
				order.setOid(new Long(out_trade_no));
				order.setPayStatus(ShoppingContants.ORDER_PAY_STATUS_HAS);
				order.setPayNo(trade_no);
				orderService.updateOrder(order);
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
			response.getWriter().println("success");
			
		}else {//验证失败
			response.getWriter().println("fail");
		
			//调试用，写文本函数记录程序运行情况是否正常
			String sWord = AlipaySignature.getSignCheckContentV1(params);
			log.info("OrderAction.updatePayStatus valid faild sWord:{}",sWord);
		}
		
	}
}
