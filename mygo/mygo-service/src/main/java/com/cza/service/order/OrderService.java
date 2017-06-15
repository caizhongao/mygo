
    /**  
    * @Title: OrderService.java
    * @Package com.cza.service.order
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月1日下午9:58:51
    * @version V1.0  
    */
    
package com.cza.service.order;

import java.util.List;

import com.cza.common.ServiceResponse;
import com.cza.dto.order.TOrder;
import com.cza.service.order.vo.PreOrderVo;
import com.cza.service.order.vo.OrderVo;

/**
    * @ClassName: OrderService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月1日下午9:58:51
    *
    */

public interface OrderService {
	public ServiceResponse<PreOrderVo> saveOrder(PreOrderVo orderVo);

	


		
		    /**
		    * @Title: listOrder
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param order
		    * @param @return    参数
		    * @return ServiceResponse<List<TOrder>>    返回类型
		    * @throws
		    */
		    
		public ServiceResponse<List<OrderVo>> listOrder(OrderVo order);





			
			    /**
			    * @Title: updateOrder
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param order
			    * @param @return    参数
			    * @return ServiceResponse<ListOrderVo>    返回类型
			    * @throws
			    */
			    
			public ServiceResponse<OrderVo> updateOrder(OrderVo order);
}
