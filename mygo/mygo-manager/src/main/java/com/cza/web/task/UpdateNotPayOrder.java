
    /**  
    * @Title: GoodsAddIndex.java
    * @Package com.cza.web.task
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    * @version V1.0  
    */
    
package com.cza.web.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderVo;

/**
    * @ClassName: GoodsAddIndex
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    *
    */
@Component
public class UpdateNotPayOrder {
	private static final Logger log = LoggerFactory.getLogger(UpdateNotPayOrder.class); 
	@Autowired
	private OrderService orderService;
	public void execute(){
		log.info("UpdateNotPayOrder.execute start!");
		long startTime=System.currentTimeMillis();
		OrderVo listOrderVo=new OrderVo();
		listOrderVo.setPayStatus(ShoppingContants.ORDER_PAY_STATUS_NOT);
		listOrderVo.setStatus(ShoppingContants.ORDER_STATUS_NORMAL);
		listOrderVo.setPageSize(100);
		listOrderVo.setCreateTime(startTime/1000 -5*60);
		while(true){
			ServiceResponse<List<Long>> resp=orderService.listOrderIds(listOrderVo);
			if(resp.isSuccess()){
				List<Long> oidList=resp.getData();
				if(oidList!=null&&oidList.size()>0){
					for(Long oid:oidList){
						//关闭订单
						OrderVo order=new OrderVo();
						order.setOid(oid);
						order.setStatus(ShoppingContants.ORDER_STATUS_SYS_DELETE);
						order.setDeleteDesc("超时未付款关闭订单");
						ServiceResponse<OrderVo> closeResp=	orderService.closeOrder(order);
						if(!closeResp.isSuccess()){
							log.info("UpdateNotPayOrder.execute erro:{}",closeResp.getCode());
						}
					}
				}else{
					break;
				}
			}else{
				log.info("UpdateNotPayOrder.execute query orderID param:{}, erro:{}",listOrderVo,resp.getCode());
				break;
			}
		}
		log.info("UpdateNotPayOrder.execute cost time:{}",System.currentTimeMillis()-startTime);
	}
}