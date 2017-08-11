
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
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
@Component("DeletePreOrderJob")
public class DeletePreOrderJob extends BaseTask {
	@Autowired
	private OrderService orderService;
	public Long invoke(){
		Long number=0l;
		OrderVo listOrderVo=new OrderVo();
		listOrderVo.setStatus(ShoppingContants.ORDER_STATUS_PRE);
		listOrderVo.setPageSize(100);
		listOrderVo.setCreateTime(System.currentTimeMillis()/1000 -5*60);
		while(true){
			log.info("listOrderIds param:{}",listOrderVo);
			ServiceResponse<List<String>> resp=orderService.listOrderIds(listOrderVo);
			if(resp.isSuccess()){
				log.info("listOrderIds success,result:{}",resp.getData());
				List<String> oidList=resp.getData();
				if(oidList!=null&&oidList.size()>0){
					for(String oid:oidList){
						//删除订单
						OrderVo order=new OrderVo();
						order.setOid(oid);
						ServiceResponse<String> closeResp=	orderService.deleteOrder(oid);
						if(closeResp.isSuccess()){
							log.info("deleteOrder success,result:{}",closeResp.getData());
						}else{
							log.info("deleteOrder has erro,respCode:{}",closeResp.getCode());
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
