
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
import java.util.List;
import java.util.Map;
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
import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.GoodsIndexService;
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
	@Autowired
	private GoodsIndexService goodsIndexService;
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);
	@RequestMapping("search")
	public String search(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("GoodsAction.search param:{}",goods);
		//查询出类目
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			List<CategoryVo> categoryList=resp.getData();
			request.setAttribute("categoryList", categoryList);
		}
		goods.setStart(0);
		goods.setPageSize(20);
		ServiceResponse<Pager<GoodsVo>>goodsResp=goodsIndexService.search(goods);
		if(resp.isSuccess()){
			request.setAttribute("pager", goodsResp.getData());
			request.setAttribute("goods", goods);
		}
		return webPage("goods/searchResult");
	}
	
	//经过对比，发现scroll分页还是不好用，用from，size比较好，性能的话，控制数量就行，一般控制10页
	@RequestMapping("scrollSearch")
	public String scrollSearch(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("GoodsAction.search param:{}",goods);
		//查询出类目
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			List<CategoryVo> categoryList=resp.getData();
			request.setAttribute("categoryList", categoryList);
		}
		goods.setPageSize(20);
		ServiceResponse<Pager<GoodsVo>>goodsResp=goodsIndexService.scrollSearch(goods);
		if(resp.isSuccess()){
			request.setAttribute("pager", goodsResp.getData());
			goods.setScrollPage(goodsResp.getData().getScrollPage());
			goods.setScrollId(goodsResp.getData().getScrollId());
			goods.setCount(goodsResp.getData().getCount());
			request.setAttribute("goods", goods);
		}
		return webPage("goods/scrollSearchResult");
	}
	
	
	@RequestMapping("listNewGoods")
	public void listNewGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		goods.setStart(0);
		goods.setPageSize(8);
		ServiceResponse<List<GoodsVo>> resp=goodsService.listNewGoods(goods);
		if(resp.isSuccess()){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData()));
		}
	}
	@RequestMapping("listHotGoods")
	public void listHotGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		goods.setStart(0);
		goods.setPageSize(8);
		ServiceResponse<List<GoodsVo>> resp=goodsService.listHotGoods(goods);
		if(resp.isSuccess()){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData()));
		}
	}
	
	@RequestMapping("listCategoryGoods")
	public void listCategoryGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//查询出用户选择的类目下商品
		goods.setStatus(ShoppingContants.GOODS_STATUS_ON);
		goods.setPageSize(8);
		ServiceResponse<Pager<GoodsVo>> resp=goodsService.listGoods(goods);
		if(resp.isSuccess()){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData().getResult()));
		}
	}
	
	@RequestMapping("goodsDetail")
	public String goodsDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CategoryVo category=new CategoryVo();
		category.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		category.setPid(0l);
		ServiceResponse<List<CategoryVo>> resp=categoryService.listCategory(category);
		if(resp.isSuccess()){
			List<CategoryVo> categoryList=resp.getData();
			request.setAttribute("categoryList", categoryList);
		}else{
			return erroPage(request, resp.getCode());
		}
		GoodsVo param=new GoodsVo();
		param.setGid(Long.valueOf(request.getParameter("gid")));
		ServiceResponse<GoodsVo> goodsResp=goodsService.queryGoods(param);
		if(resp.isSuccess()){
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
