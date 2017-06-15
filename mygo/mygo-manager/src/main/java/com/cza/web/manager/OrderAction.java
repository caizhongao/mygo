
    /**  
    * @Title: OrderAction.java
    * @Package com.cza.web.login
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月9日上午10:19:45
    * @version V1.0  
    */
    
package com.cza.web.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TUserAddr;
import com.cza.dto.goods.TSkuStock;
import com.cza.dto.order.TOrder;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.SkuVo;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.PreOrderVo;
import com.cza.service.order.vo.OrderVo;
import com.cza.service.user.AddrService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;
import com.cza.web.manager.GoodsAction;

/**
    * @ClassName: OrderAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月9日上午10:19:45
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("/manager/order")
public class OrderAction extends CommonAction{
	@Autowired
	private OrderService orderService;
	private static final Logger log = LoggerFactory.getLogger(OrderAction.class); 
	
	
	@RequestMapping("listNotPayOrder")
	public String listNotPayOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ){
		log.info("OrderAction.listNotPayOrder 请求参数,order:{}",order);
		order.setPayStatus(ShoppingContants.ORDER_NOT_PAY);
		ServiceResponse<List<OrderVo>> resp=orderService.listOrder(order);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("orderList", resp.getData());
			log.info("OrderAction.listNotPayOrder success,orderNo:{}",order.getOid());
			return managerPage("listNotPayOrder");
		}else{
			log.info("OrderAction.listNotPayOrder faild!");
			return erro(request, resp.getMsg());
		}
	}
	
	@RequestMapping("listPayOrder")
	public String listPayOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ){
		log.info("OrderAction.listPayOrder 请求参数,order:{}",order);
		order.setPayStatus(ShoppingContants.ORDER_HAS_PAY);
		ServiceResponse<List<OrderVo>> resp=orderService.listOrder(order);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("orders", resp.getData());
			log.info("OrderAction.listPayOrder success,orderNo:{}",order.getOid());
			return managerPage("listPayOrder");
		}else{
			log.info("OrderAction.listNotPayOrder faild!");
			return erro(request, resp.getMsg());
		}
	}

	@RequestMapping("deleteOrder")
	public String deleteOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ){
		log.info("OrderAction.deleteOrder 请求参数,order:{}",order);
		order.setStatus(ShoppingContants.ORDER_STATUS_DELETE);
		ServiceResponse<OrderVo> resp=orderService.updateOrder(order);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			log.info("OrderAction.deleteOrder success,orderNo:{}",order.getOid());
			return managerPage("listNotPayOrder");
		}else{
			log.info("OrderAction.deleteOrder faild!");
			return erro(request, resp.getMsg());
		}
	}
	
	
}
