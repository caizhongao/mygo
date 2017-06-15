
    /**  
    * @Title: HomeAction.java
    * @Package com.cza.web.home.action
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    * @version V1.0  
    */
    
package com.cza.web.unlogin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.CategoryVo;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;

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
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("index")
	public String index(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			List<CategoryVo> categoryList=resp.getData();
			request.setAttribute("categoryList", categoryList);
		}
		return webPage("home/index");
	}
	
	@RequestMapping("categoryGoods")
	public String categoryGoods(HttpServletRequest request,HttpServletResponse response){
		//查询出类目
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			List<CategoryVo> categoryList=resp.getData();
			request.setAttribute("categoryList", categoryList);
		}
		//查询出用户选择的类目下商品
		Long cid=Long.valueOf(request.getParameter("cid"));
		request.setAttribute("cid", cid);
		GoodsVo goods=new GoodsVo();
		goods.setCid(cid);
		goods.setStatus(ShoppingContants.GOODS_STATUS_ON);
		ServiceResponse<List<GoodsVo>> resp1=goodsService.listGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp1.getCode())){
			List<GoodsVo> goodsList=resp1.getData();
			request.setAttribute("goodsList", goodsList);
			
		}
		return webPage("home/categoryGoods");
	}
	
}
