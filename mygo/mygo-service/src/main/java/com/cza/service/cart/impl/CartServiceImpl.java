/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.service.cart.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.mapper.cart.CartMapper;
import com.cza.service.cart.CartService;
import com.cza.service.cart.vo.CartVo;

/**
 * 作用：
 * @author zhongao
 * @date 2017年7月12日
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;
	@Override
	public ServiceResponse<CartVo> addCart(CartVo cart) {
		return null;
	}

	@Override
	public ServiceResponse<CartVo> removeCart(CartVo cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<List<CartVo>> listCart(CartVo listParam) {
		// TODO Auto-generated method stub
		return null;
	}

}
