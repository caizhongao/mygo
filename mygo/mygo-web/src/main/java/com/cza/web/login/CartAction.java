/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.web.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.cart.CartService;
import com.cza.service.cart.vo.CartVo;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;

/**
 * 作用：
 * @author zhongao
 * @date 2017年7月12日
 */
@Controller
@Scope("prototype")
@RequestMapping("/login/cart")
public class CartAction extends CommonAction{
	@Autowired
	public CartService cartService;
	
	@RequestMapping("addCart")
	public void addCart(@ModelAttribute CartVo cart,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		UserVo user=getUser(request);
		cart.setUid(user.getUid());
		ServiceResponse<CartVo>resp=cartService.addCart(cart);
		if(resp.isSuccess()){
			pw.println(new RespMsg("success", null).toJson());
		}else{
			pw.println(new RespMsg("fail", null).toJson());
		}
	}
	
	@RequestMapping("removeCart")
	public String removeCart(@ModelAttribute CartVo cart,HttpServletRequest request,HttpServletResponse response){
		UserVo user=getUser(request);
		cart.setUid(user.getUid());
		ServiceResponse<CartVo>resp=cartService.removeCart(cart);
		if(resp.isSuccess()){
			return webAction("/login/cart/listCart");
		}else{
			return erroPage(resp.getCode());
		}
	}
	@RequestMapping("listCart")
	public String listCart(HttpServletRequest request,HttpServletResponse response){
		UserVo user=getUser(request);
		CartVo listParam=new CartVo();
		listParam.setUid(user.getUid());
		ServiceResponse<List<CartVo>>resp=cartService.listCart(listParam);
		if(resp.isSuccess()){
			return webPage("/user/cartList");
		}else{
			return erroPage(resp.getCode());
		}
		
	}
}
