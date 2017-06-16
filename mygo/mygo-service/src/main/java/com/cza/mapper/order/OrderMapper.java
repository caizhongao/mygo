
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.order;

import java.util.List;

import com.cza.dto.order.TOrder;


/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface OrderMapper {
	
	public List<TOrder> listOrder(TOrder param);
	
	public TOrder queryOrder(Long oid);
	
	public void saveOrder(TOrder order);

	
	    /**
	    * @Title: updateOrder
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param updateParam
	    * @param @return    参数
	    * @return List<TOrder>    返回类型
	    * @throws
	    */
	    
	public void updateOrder(TOrder updateParam);
	
}
