
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

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.service.order.vo.OrderVo;

/**
    * @ClassName: OrderService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月1日下午9:58:51
    *
    */

public interface OrderService {

			public ServiceResponse<OrderVo> savePreOrder(OrderVo orderVo);
			
			public ServiceResponse<OrderVo> confirmOrder(OrderVo orderVo);
		
		    /**
		    * @Title: listOrder
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param order
		    * @param @return    参数
		    * @return ServiceResponse<List<TOrder>>    返回类型
		    * @throws
		    */
		    
			public ServiceResponse<Pager<OrderVo>> listOrder(OrderVo listOrderVo);





			
		    /**
		    * @Title: updateOrder
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param order
		    * @param @return    参数
		    * @return ServiceResponse<ListOrderVo>    返回类型
		    * @throws
		    */
			    
			 public ServiceResponse<OrderVo> updateOrder(OrderVo order);

			 /**
			 * @param orderversion，oid，sid
			  * */
			 public ServiceResponse<OrderVo> closeOrder(OrderVo orderVo);
	
			 /**
			  * 
			     * @Title: orderPay
			     * @Description: 更新订单支付状态，能进入这个方法的，说明肯定是付款成功了
			     * 1.验证订单号是否存在，订单金额是否符合
			     * 2.如果订单已支付，则不处理，返回success
			     * 3.else如果订单支付状态不为未支付，则返回failed
			     * 4.满足所有条件，更新订单状态
			     * @param @param order
			     * @param @return    参数
			     * @return ServiceResponse<OrderVo>    返回类型
			  */
			 public ServiceResponse<OrderVo> orderPay(OrderVo order);
			 
			 /**
			  * 
			     * @Title: orderRefund
			     * @Description: 更新订单退款状态，能进入这个方法，说明肯定是退款成功的，所以数据的状态必须是已退款
			     * 1.如果订单状态是已退款，则不需要处理
			     * 2.如果订单状态不是已退款，则更新退款状态，并且归还库存
			  */
			 public ServiceResponse<OrderVo> orderRefund(OrderVo orderVo);


				
				    /**
				     * @return 
				    * @Title: queryOrder
				    * @Description: TODO(这里用一句话描述这个方法的作用)
				    * @param @param valueOf    参数
				    * @return void    返回类型
				    * @throws
				    */
			  public ServiceResponse<OrderVo> queryOrder(String oid);

					
					    /**
					    * @Title: listOrderIds
					    * @Description: TODO(这里用一句话描述这个方法的作用)
					    * @param @param listOrderVo
					    * @param @return    参数
					    * @return ServiceResponse<Pager<Long>>    返回类型
					    * @throws
					    */
					    
					public ServiceResponse<List<String>>listOrderIds(OrderVo listOrderVo);

						/**作用：
						 *@param oid
						 *@return
						 *@return ServiceResponse<OrderVo>
						 */
						public ServiceResponse<String> deleteOrder(String oid);
}
