
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

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TUserAddr;
import com.cza.dto.goods.TSkuStock;
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
	
	@RequestMapping("toMakeOrderPage")
	public String toMakeOrderPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserVo userVo=getUser(request);
		Long sid=Long.valueOf(request.getParameter("sid"));
		Long number=Long.valueOf(request.getParameter("number"));
		log.info("OrderAction.toMakeOrderPage 请求参数,sid:{},number:{}",sid,number);
		PreOrderVo order=new PreOrderVo();
		order.setNumber(number);
		ServiceResponse<TSkuStock> resp=goodsService.querySkuStock(sid);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			TSkuStock stock=resp.getData();
			//校验库存
			if(stock.getStock()<number){
				return referPage(request);
			}
		}
		//获取sku信息
		ServiceResponse<SkuVo> skuResp=goodsService.querySku(sid);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(skuResp.getCode())){
			SkuVo sku=skuResp.getData();
			order.setSku(sku);
			order.setAmount(sku.getPrice().multiply(new BigDecimal(order.getNumber())));
		}else{
			return referPage(request);
		}
		
		//获取默认地址
		TUserAddr addrParam=new TUserAddr();
		addrParam.setUid(userVo.getUid());
		addrParam.setIsDefault(ShoppingContants.ADDR_IS_DEFAULT);		
		ServiceResponse<List<TUserAddr>> respAddr=addrService.listAddrs(addrParam);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(respAddr.getCode())){
			List<TUserAddr> addrs=respAddr.getData();
			if(addrs!=null&&addrs.size()>0){
				order.setAddr(addrs.get(0));
			}
		}else{
			return referPage(request);
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
		}else{
			UserVo userVo=getUser(request);
			order.setUid(userVo.getUid());
			ServiceResponse<PreOrderVo> resp=orderService.saveOrder(order);
			if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
				log.info("OrderAction.saveOrder success,orderNo:{}",order.getOrderId());
				return "redirect:/login/order/toOrderPayPage.do?oid="+resp.getData().getOrderId();
			}else{
				log.info("OrderAction.saveOrder faild!");
			}
		}
		return webPage("erro");
	}
	@RequestMapping("toOrderPayPage")
	public String toOrderPayPage(HttpServletRequest request,HttpServletResponse response ){
		String str=request.getParameter("oid");
		if(StringUtils.isEmpty(str)){
			return webPage("erro");
		}
		ServiceResponse<OrderVo> resp=orderService.queryOrder(Long.valueOf(str));
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("order", resp.getData());
			return webPage("/home/orderPage");
		}else{
			log.info("OrderAction.toOrderPayPage faild!");
			return webPage("erro");
		}
	}
}
