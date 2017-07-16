
    /**  
    * @Title: GoodsAddIndex.java
    * @Package com.cza.web.task
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    * @version V1.0  
    */
    
package com.cza.web.task;

import java.util.List;

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
public class DeletePreOrder {
	private static final Logger log = LoggerFactory.getLogger(DeletePreOrder.class); 
	@Autowired
	private OrderService orderService;
	public void execute(){
		log.info("DeletePreOrder.execute start!");
		long startTime=System.currentTimeMillis();
		OrderVo listOrderVo=new OrderVo();
		listOrderVo.setStatus(ShoppingContants.ORDER_STATUS_PRE);
		listOrderVo.setPageSize(100);
		listOrderVo.setCreateTime(startTime/1000 -5*60);
		while(true){
			ServiceResponse<List<String>> resp=orderService.listOrderIds(listOrderVo);
			if(resp.isSuccess()){
				List<String> oidList=resp.getData();
				if(oidList!=null&&oidList.size()>0){
					for(String oid:oidList){
						//删除订单
						OrderVo order=new OrderVo();
						order.setOid(oid);
						ServiceResponse<String> closeResp=	orderService.deleteOrder(oid);
						if(!closeResp.isSuccess()){
							log.warn("DeletePreOrder.execute erro code:{},msg:{}",closeResp.getCode(),closeResp.getMsg());
						}else{
							log.warn("DeletePreOrder.execute delete oid:{}",oid);
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
