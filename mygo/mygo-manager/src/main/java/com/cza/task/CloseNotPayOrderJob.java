
    /**  
    * @Title: GoodsAddIndex.java
    * @Package com.cza.web.task
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    * @version V1.0  
    */
    
package com.cza.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.cza.common.ParamUtil;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.common.log.LogUtil;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderVo;
import com.cza.task.base.BaseTask;

/**
    * @ClassName: GoodsAddIndex
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    *
    */
@Component("CloseNotPayOrderJob")
public class CloseNotPayOrderJob extends BaseTask{
	private Long defaultExpireTime=5*60*1000l;
	@Autowired
	private OrderService orderService;
	
	public Long invoke(){
		Long number=0l;
		OrderVo listOrderVo=new OrderVo();
		listOrderVo.setStatus(ShoppingContants.ORDER_STATUS_WAIT_PAY);
		listOrderVo.setPageSize(100);
		String expireTime=ParamUtil.getParam(ShoppingContants.ORDER_NOT_PAY_EXPIRE_TIME);
		if(!StringUtils.isEmpty(expireTime)){
			listOrderVo.setCreateTime((System.currentTimeMillis() -Long.parseLong(expireTime))/1000);
		}else{
			listOrderVo.setCreateTime((System.currentTimeMillis() -defaultExpireTime)/1000);
		}
		while(true){
			log.info("listOrderIds param:{}",listOrderVo);
			ServiceResponse<List<String>> resp=orderService.listOrderIds(listOrderVo);
			if(resp.isSuccess()){
				log.info("listOrderIds success,result:{}",resp.getData());
				List<String> oidList=resp.getData();
				if(oidList!=null&&oidList.size()>0){
					for(String oid:oidList){
						//关闭订单
						OrderVo order=new OrderVo();
						order.setOid(oid);
						order.setStatus(ShoppingContants.ORDER_STATUS_SYS_DELETE);
						order.setDeleteDesc("超时未付款关闭订单");
						ServiceResponse<OrderVo> closeResp=	orderService.closeOrder(order);
						if(!closeResp.isSuccess()){
							log.info("closeOrder success,result:{}",closeResp.getData());
						}else{
							log.info("closeOrder has erro,respCode:{}",closeResp.getCode());
						}
					}
					
				}else{
					break;
				}
				number+=oidList.size();
			}else{
				log.info("listOrderIds has erro,respCode:{}",resp.getCode());
			}
		}
		return number;
	}
}
