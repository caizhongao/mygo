
    /**  
    * @Title: HomeAction.java
    * @Package com.cza.web.home.action
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    * @version V1.0  
    */
    
package com.cza.web.unlogin;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.cart.CartService;
import com.cza.service.cart.vo.CartVo;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.vo.CategoryVo;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;
import com.cza.web.login.CartAction;

/**
    * @ClassName: HomeAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("/unlogin/home")
public class HomeAction extends CommonAction{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	public CartService cartService;
	private static final Logger log = LoggerFactory.getLogger(CartAction.class); 
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("cid", -1);
		return webPage("home/index");
	}
	
	@RequestMapping("erro")
	public String erro(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("erroCode", request.getParameter("erroCode"));
		return "/common/erro";
	}
	
	@RequestMapping("orderErro")
	public String orderErro(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("erroCode", request.getParameter("erroCode"));
		return "/common/orderErro";
	}
	
	@RequestMapping("listCategory")
	public void listCategory(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		log.info("HomeAction.listCategory request.");
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(resp.isSuccess()){
			response.getOutputStream().write(JSON.toJSONString(resp.getData()).getBytes("utf-8"));
		}
	}
	
	@RequestMapping("listCartAjax")
	public void listCartAjax(HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("listCartAjax request.");
		UserVo user=getUser(request);
		if(user==null){
			log.warn("listCartAjax failed,user not login");
			response.getWriter().println(new RespMsg("fail",null));
		}else{
			CartVo listParam=new CartVo();
			listParam.setUid(user.getUid());
			ServiceResponse<List<CartVo>>resp=cartService.listCart(listParam);
			if(resp.isSuccess()){
				log.info("listCartAjax success,result:{}",resp.getData());
				response.setCharacterEncoding("utf-8");
				response.getWriter().println(new RespMsg("success",resp.getData()));
			}else{
				log.warn("listCartAjax has erro,respCode:{}",resp.getCode());
				response.getWriter().println(new RespMsg("fail",resp.getCode()));
			}
		}
	}
	
}
