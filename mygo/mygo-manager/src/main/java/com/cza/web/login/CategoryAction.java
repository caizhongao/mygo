
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cza.common.RespMsg;
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
	private static final Logger log = LoggerFactory.getLogger(CategoryAction.class);
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("listAttrs")
	public void listAttrs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String cid=request.getParameter("cid");
		log.info("listAttrs request param:{}",cid);
		response.setCharacterEncoding("utf-8");
		ServiceResponse<List<TCategoryAttr>> resp=categoryService.listAttrs(Long.valueOf(cid));
		if(resp.isSuccess()){
			log.info("listAttrs success,result:{}",resp.getData());
			response.getWriter().println(new RespMsg("success", resp.getData()));
		}else{
			log.info("listAttrs has erro,respCode:{}",resp.getCode());
			response.getWriter().println(new RespMsg("fail", resp.getCode()));
		}
	}
	
	@RequestMapping("listCategory")
	public String listCategory(@ModelAttribute CategoryVo category,HttpServletRequest request,HttpServletResponse response){
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(resp.isSuccess()){
			log.info("listCategory success,result:{}", resp.getData());
			request.setAttribute("listCategory", resp.getData());
			return webPage("categoryList");
		}else{
			log.info("listCategory has erro,respCode:{}",resp.getCode());
			return erro(request, resp);
		}
		
	}
	
	@RequestMapping("saveCategory")
	public void saveCategory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String categoryJson=request.getParameter("category");
		CategoryVo category = JSON.parseObject(categoryJson, CategoryVo.class);
		ServiceResponse<CategoryVo> resp=categoryService.saveCategory(category);
		if(resp.isSuccess()){
			log.info("saveCategory success,result:{}", resp.getData());
			response.getWriter().print("success");
		}else{
			log.info("saveCategory has erro,respCode:{}",resp.getCode());
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("updateCategory")
	public void updateCategory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String categoryJson=request.getParameter("category");
		CategoryVo category = JSON.parseObject(categoryJson, CategoryVo.class);
		log.info("updateCategory param:{}",category);
		ServiceResponse<CategoryVo> resp=categoryService.updateCategory(category);
		if(resp.isSuccess()){
			log.info("updateCategory success,result:{}", resp.getData());
			response.getWriter().print("success");
		}else{
			log.info("updateCategory has erro,respCode:{}",resp.getCode());
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("editCategory")
	public String editCategory(@ModelAttribute CategoryVo category,HttpServletRequest request,HttpServletResponse response){
		ServiceResponse<CategoryVo> resp=categoryService.queryCategory(category.getCid());
		if(resp.isSuccess()){
			log.info("queryCategory success,result:{}", resp.getData());
			request.setAttribute("category", resp.getData());
			return webPage("editCategory");
		}else{
			log.info("queryCategory has erro,respCode:{}",resp.getCode());
			return erro(request, resp);
		}
	}
	
	@RequestMapping("addCategory")
	public String addCategory(HttpServletRequest request,HttpServletResponse response){
		return webPage("addCategory");
	}
	
}
