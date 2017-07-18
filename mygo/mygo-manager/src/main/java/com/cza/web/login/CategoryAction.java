
    /**  
    * @Title: CategoryAction.java
    * @Package com.cza.web.manager
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月4日下午2:43:33
    * @version V1.0  
    */
    
package com.cza.web.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.vo.CategoryVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: CategoryAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月4日下午2:43:33
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("login/category")
public class CategoryAction extends CommonAction{
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("listAttrs")
	public @ResponseBody List<TCategoryAttr> listAttrs(HttpServletRequest request,HttpServletResponse response){
		String cid=request.getParameter("cid");
		List<TCategoryAttr>attrs=null;
		ServiceResponse<List<TCategoryAttr>> resp=categoryService.listAttrs(Long.valueOf(cid));
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			attrs=resp.getData();
		}
		return attrs;
	}
	@RequestMapping("listCategory")
	public String listCategory(@ModelAttribute CategoryVo category,HttpServletRequest request,HttpServletResponse response){
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("categoryList", resp.getData());
		}else{
			request.setAttribute("status", "service errro!");
		}
		return webPage("categoryList");
	}
	
	@RequestMapping("saveCategory")
	public void saveCategory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String categoryJson=request.getParameter("category");
		CategoryVo category = JSON.parseObject(categoryJson, CategoryVo.class);
		ServiceResponse<CategoryVo> resp=categoryService.saveCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("updateCategory")
	public void updateCategory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String categoryJson=request.getParameter("category");
		CategoryVo category = JSON.parseObject(categoryJson, CategoryVo.class);
		ServiceResponse<CategoryVo> resp=categoryService.updateCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("editCategory")
	public String editCategory(@ModelAttribute CategoryVo category,HttpServletRequest request,HttpServletResponse response){
		ServiceResponse<CategoryVo> resp=categoryService.queryCategory(category.getCid());
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("category", resp.getData());
			return webPage("editCategory");
		}else{
			return erro(request, resp);
		}
	}
	
	@RequestMapping("addCategory")
	public String addCategory(HttpServletRequest request,HttpServletResponse response){
		return webPage("addCategory");
	}
	
}
