
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.Pager;
import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderVo;
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
@RequestMapping("login/order")
public class OrderAction extends CommonAction{
	@Autowired
	private OrderService orderService;
	private static final Logger log = LoggerFactory.getLogger(OrderAction.class); 
	
	
	@RequestMapping("listOrder")
	public String listOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ){
		log.info("OrderAction.listNotPayOrder 请求参数,order:{}",order);
		request.setAttribute("order", order);
		ServiceResponse<Pager<OrderVo>> resp=orderService.listOrder(order);
		if(resp.isSuccess()){
			log.info("listOrder success,result:{}",resp.getData());
			request.setAttribute("pager", resp.getData());
			return webPage("listOrder");
		}else{
			log.info("listOrder has erro,respCode:{}",resp.getCode());
			return erro(request, resp);
		}
	}
	
	
	
	@RequestMapping("deleteOrder")
	public void deleteOrder(@ModelAttribute OrderVo order,HttpServletRequest request,HttpServletResponse response ) throws IOException{
		log.info("OrderAction.deleteOrder 请求参数,order:{}",order);
		order.setStatus(ShoppingContants.ORDER_STATUS_SYS_DELETE);
		ServiceResponse<OrderVo> resp=orderService.closeOrder(order);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			log.info("closeOrder success,result:{}",resp.getData());
			response.getWriter().print(new RespMsg("success",null));
		}else{
			log.info("closeOrder has erro,respCode:{}",resp.getCode());
			response.getWriter().print(new RespMsg("fail",resp.getCode()));
		}
	}
	
	
}
