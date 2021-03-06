
    /**  
    * @Title: GoodsAction.java
    * @Package com.cza.web.goods.action
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月7日下午4:08:11
    * @version V1.0  
    */
    
package com.cza.web.unlogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.CategoryVo;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.goods.vo.SkuAttrVo;
import com.cza.service.goods.vo.SkuVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: GoodsAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月7日下午4:08:11
    *
    */
@Controller("goods")
@Scope("prototype")
@RequestMapping("/unlogin/goods")
public class GoodsAction extends CommonAction{
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("listNewGoods")
	public void listNewGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ServiceResponse<List<GoodsVo>> resp=goodsService.listNewGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData()));
		}
	}
	@RequestMapping("listHotGoods")
	public void listHotGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ServiceResponse<List<GoodsVo>> resp=goodsService.listHotGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData()));
		}
	}
	
	@RequestMapping("listCategoryGoods")
	public void listCategoryGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//查询出用户选择的类目下商品
		goods.setStatus(ShoppingContants.GOODS_STATUS_ON);
		ServiceResponse<List<GoodsVo>> resp=goodsService.listGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData()));
		}
	}
	
	@RequestMapping("goodsDetail")
	public String goodsDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			List<CategoryVo> categoryList=resp.getData();
			request.setAttribute("categoryList", categoryList);
		}else{
			return erroPage(request, resp.getCode());
		}
		GoodsVo param=new GoodsVo();
		param.setGid(Long.valueOf(request.getParameter("gid")));
		ServiceResponse<GoodsVo> goodsResp=goodsService.queryGoods(param);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(goodsResp.getCode())){
			GoodsVo goodsVo=goodsResp.getData();
			request.setAttribute("goods", goodsVo);
			request.setAttribute("attrs", initAttr(goodsVo));
			request.setAttribute("cid", goodsVo.getCid());
			return webPage("goods/goodsDetail");
		}else{
			return erroPage(request, goodsResp.getCode());
		}
	}
	
	
	//key是attrName,value是attrValue的集合
	private Map<String,Map<String,Long>> initAttr(GoodsVo goodsVo){
		List<SkuAttrVo> attrList=new ArrayList<>();
		for(SkuVo sku:goodsVo.getSkus()){
			attrList.addAll(sku.getAttrs());
		}
		Map<String,Map<String,Long>>map=new HashMap<String,Map<String,Long>>();
		for(SkuAttrVo attr:attrList){
			Map<String,Long>values=map.get(attr.getAttrName());
			if(values==null){
				values=new HashMap<String,Long>();
			}
			values.put(attr.getAttrValue(),attr.getAttrId());
			map.put(attr.getAttrName(), values);
		}
		return map;
	}
}
