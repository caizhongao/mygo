
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

import org.apache.ibatis.annotations.Param;

import com.cza.common.Pager;
import com.cza.dto.order.TOrder;
import com.cza.dto.order.TOrderDetail;
import com.cza.service.order.vo.OrderVo;


/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface OrderDetailMapper {
	
	public List<TOrderDetail> listOrderDetails(TOrderDetail listParam);
	
	public TOrder queryOrderDetail(Long odid);
	
	public void saveOrderDetail(TOrderDetail order);
	
}
