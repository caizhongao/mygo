
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cza.common.Pager;
import com.cza.dto.cart.TCart;
import com.cza.dto.order.TOrder;
import com.cza.service.cart.vo.CartVo;
import com.cza.service.order.vo.OrderVo;


/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface CartMapper {
	
	public List<TCart> listCart(TCart listParam);
	
	public TCart queryCart(Long cartId);
	
	public void saveCart(TCart cart);
	    
	public int updateCart(TCart updateParam);

	
	    /**
	    * @Title: deleteCart
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param deleteParam    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	public void deleteCart(TCart deleteParam);
	
}
