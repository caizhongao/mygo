
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.order;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cza.common.Pager;
import com.cza.dto.order.TOrder;
import com.cza.service.order.vo.OrderVo;


/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface OrderMapper {
	
	public List<OrderVo> listOrderVo(OrderVo listParam);
	
	public OrderVo queryOrderVo(String oid);
	
	public TOrder queryOrder(String oid);
	
	public void saveOrder(TOrder order);

	
	    /**
	    * @Title: updateOrder
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param updateParam
	    * @param @return    参数
	    * @return List<TOrder>    返回类型
	    * @throws
	    */
	    
	public int updateOrder(TOrder updateParam);

		
		    /**
		     * @return 
		    * @Title: countOrder
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param listParam    参数
		    * @return void    返回类型
		    * @throws
		    */
		    
		public Long countOrder(OrderVo listParam);

			
			    /**
			    * @Title: listOrderIds
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param listParam
			    * @param @return    参数
			    * @return List<Long>    返回类型
			    * @throws
			    */
			    
			public List<String> listOrderIds(OrderVo listParam);

				/**作用：
				 *@param oid
				 *@return
				 *@return TOrder
				 */
				public int deleteOrder(TOrder param);

				
				    /**
				    * @Title: listAvgOrderPrice
				    * @Description: TODO(这里用一句话描述这个方法的作用)
				    * @param @param orderVo
				    * @param @return    参数
				    * @return BigDecimal    返回类型
				    * @throws
				    */
				    
				public BigDecimal queryAvgOrderPrice(OrderVo orderVo);

					
					    /**
					    * @Title: queryHotCategory
					    * @Description: TODO(这里用一句话描述这个方法的作用)
					    * @param @param orderVo
					    * @param @return    参数
					    * @return Long    返回类型
					    * @throws
					    */
					    
					public Long queryHotCategory(OrderVo orderVo);
	
}
