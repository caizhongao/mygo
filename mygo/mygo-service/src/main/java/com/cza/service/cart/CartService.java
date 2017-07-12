/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.service.cart;

import java.util.List;

import com.cza.common.ServiceResponse;
import com.cza.service.cart.vo.CartVo;

/**
 * 作用：
 * @author zhongao
 * @date 2017年7月12日
 */
public interface CartService {

	/**作用：
	 *@param cart
	 *@return
	 *@return ServiceResponse<CartVo>
	 */
	ServiceResponse<CartVo> addCart(CartVo cart);

	/**作用：
	 *@param cart
	 *@return
	 *@return ServiceResponse<CartVo>
	 */
	ServiceResponse<CartVo> removeCart(CartVo cart);

	/**作用：
	 *@param listParam
	 *@return
	 *@return ServiceResponse<List<CartVo>>
	 */
	ServiceResponse<List<CartVo>> listCart(CartVo listParam);
	
}
