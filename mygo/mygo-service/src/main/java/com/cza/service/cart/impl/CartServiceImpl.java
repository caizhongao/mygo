/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.service.cart.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.cart.TCart;
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
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	private CartMapper cartMapper;
	@Override
	public ServiceResponse<CartVo> addCart(CartVo cart) {
		ServiceResponse<CartVo> resp=new ServiceResponse<CartVo>();
		try{	
			TCart listParam=new TCart();
			listParam.setSid(cart.getSid());
			listParam.setUid(cart.getUid());
			List<TCart>cartList=cartMapper.listCart(listParam);
			if(cartList!=null&&cartList.size()>0){
				TCart updateParam=cartList.get(0);
				updateParam.setNumber(updateParam.getNumber()+cart.getNumber());
				cartMapper.updateCart(updateParam);
			}else{
				TCart saveParam=new TCart();
				saveParam.setNumber(cart.getNumber());
				saveParam.setSid(cart.getSid());
				saveParam.setUid(cart.getUid());
				cartMapper.saveCart(saveParam);
			}
			resp.setData(cart);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("addCart failed",e);
		}
		return resp;
	}

	@Override
	public ServiceResponse<CartVo> removeCart(CartVo cart) {
		ServiceResponse<CartVo> resp=new ServiceResponse<CartVo>();
		try{
			TCart deleteParam=new TCart();
			deleteParam.setSid(cart.getSid());
			deleteParam.setUid(cart.getUid());
			cartMapper.deleteCart(deleteParam);
			resp.setData(cart);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("addCart failed",e);
		}
		return resp;
	}

	@Override
	public ServiceResponse<List<CartVo>> listCart(CartVo cart) {
		ServiceResponse<List<CartVo>> resp=new ServiceResponse<List<CartVo>>();
		try{
			TCart listParam=new TCart();
			listParam.setSid(cart.getUid());
			List<TCart> cartList=cartMapper.listCart(listParam);
			List<CartVo> voList=new ArrayList<>();
			if(cartList!=null&&cartList.size()>0){
				for(TCart result:cartList){
					CartVo vo=new CartVo();
					vo.setNumber(result.getNumber());
					vo.setSid(result.getSid());
					vo.setUid(result.getUid());
					voList.add(vo);
				}
			}
			resp.setData(voList);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("addCart failed",e);
		}
		return resp;
	}

}
