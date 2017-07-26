
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
import com.cza.service.goods.GoodsIndexService;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.goods.vo.SkuAttrVo;
import com.cza.service.goods.vo.SkuVo;
import com.cza.service.goods.vo.UserLikeVo;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderVo;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;
import com.cza.web.filter.cache.WrapperResponse;

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
	private OrderService orderService;
	@Autowired
	private GoodsIndexService goodsIndexService;
	private static final Logger log = LoggerFactory.getLogger(GoodsAction.class);
	@RequestMapping("search")
	public String search(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("GoodsAction.search param:{}",goods);
		Long startTime=System.currentTimeMillis();
		goods.setStart(0);
		goods.setPageSize(20);
		ServiceResponse<Pager<GoodsVo>>goodsResp=goodsIndexService.search(goods);
		if(goodsResp.isSuccess()){
			request.setAttribute("pager", goodsResp.getData());
			request.setAttribute("goods", goods);
		}
		log.info("GoodsAction.search cost time:{}",System.currentTimeMillis()-startTime);
		return webPage("goods/searchResult");
	}
	
	//经过对比，发现scroll分页还是不好用，用from，size比较好，性能的话，控制数量就行，一般控制10页
	@RequestMapping("scrollSearch")
	public String scrollSearch(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("GoodsAction.search param:{}",goods);
		goods.setPageSize(20);
		ServiceResponse<Pager<GoodsVo>>goodsResp=goodsIndexService.scrollSearch(goods);
		if(goodsResp.isSuccess()){
			request.setAttribute("pager", goodsResp.getData());
			goods.setScrollPage(goodsResp.getData().getScrollPage());
			goods.setScrollId(goodsResp.getData().getScrollId());
			goods.setCount(goodsResp.getData().getCount());
			request.setAttribute("goods", goods);
		}
		return webPage("goods/scrollSearchResult");
	}
	
	@RequestMapping("listNewGoods")
	public void listNewGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,WrapperResponse response) throws IOException{
		Long startTime=System.currentTimeMillis();
		goods.setStart(0);
		goods.setPageSize(9);
		ServiceResponse<List<GoodsVo>> resp=goodsService.listNewGoods(goods);
		if(resp.isSuccess()){
			response.getOutputStream().write(JSON.toJSONString(resp.getData()).getBytes("utf-8"));
		}
		log.info("GoodsAction.listNewGoods cost time:{}",System.currentTimeMillis()-startTime);
	}
	
	
	@RequestMapping("listUserLikeGoods")
	public void listUserLikeGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserVo user=getUser(request);
		UserLikeVo likeVo=null;
		if(user!=null){
			OrderVo orderVo=new OrderVo();
			orderVo.setUid(user.getUid());
			likeVo=orderService.getUserLike(orderVo);
		}else{
			likeVo=new UserLikeVo();
		}
		log.info("listUserLikeGoods likeVo:{}",likeVo);
		Long startTime=System.currentTimeMillis();
		likeVo.setStart(0);
		likeVo.setPageSize(9);
		ServiceResponse<List<GoodsVo>> resp=goodsService.listUserLikeGoods(likeVo);
		if(resp.isSuccess()){
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(JSON.toJSONString(resp.getData()));
		}
		log.info("GoodsAction.listNewGoods cost time:{}",System.currentTimeMillis()-startTime);
	}
	
	@RequestMapping("listHotGoods")
	public void listHotGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,WrapperResponse response) throws IOException{
		Long startTime=System.currentTimeMillis();
		goods.setStart(0);
		goods.setPageSize(9);
		ServiceResponse<List<GoodsVo>> resp=goodsService.listHotGoods(goods);
		if(resp.isSuccess()){
			response.getOutputStream().write(JSON.toJSONString(resp.getData()).getBytes("utf-8"));
		}
		log.info("GoodsAction.listHotGoods cost time:{}",System.currentTimeMillis()-startTime);
	}
	
	@RequestMapping("listCategoryGoods")
	public String listCategoryGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("listCategoryGoods request params:{}",goods);
		Long startTime=System.currentTimeMillis();
		request.setAttribute("cid", goods.getCid());
		//查询出用户选择的类目下商品
		goods.setStatus(ShoppingContants.GOODS_STATUS_ON);
		goods.setPageSize(20);
		ServiceResponse<Pager<GoodsVo>> goodsResp=goodsService.listGoods(goods);
		if(goodsResp.isSuccess()){
			request.setAttribute("pager", goodsResp.getData());
			request.setAttribute("goods", goods);
		}
		log.info("GoodsAction.listCategoryGoods cost time:{}",System.currentTimeMillis()-startTime);
		return webPage("home/categoryGoods");
	}
	
	@RequestMapping("goodsDetail")
	public String goodsDetail(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("goodsDetail request params:{}",goods);
		Long startTime=System.currentTimeMillis();
		ServiceResponse<GoodsVo> goodsResp=goodsService.queryGoods(goods);
		if(goodsResp.isSuccess()){
			log.info("queryGoods success result:{}, cost time:{}",goodsResp.getData(),System.currentTimeMillis()-startTime);
			GoodsVo goodsVo=goodsResp.getData();
			request.setAttribute("goods", goodsVo);
			request.setAttribute("attrs", initAttr(goodsVo));
			request.setAttribute("cid", goodsVo.getCid());
			return webPage("goods/goodsDetail");
		}else{
			log.info("queryGoods has erro,respCode:{}",goodsResp.getCode());
			return erroPage(goodsResp.getCode());
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
