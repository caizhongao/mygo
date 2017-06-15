
    /**  
    * @Title: GoodsAction.java
    * @Package com.cza.web.manager
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午6:12:39
    * @version V1.0  
    */
    
package com.cza.web.manager;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.goods.vo.SkuVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: GoodsAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日下午6:12:39
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("manager/goods")
public class GoodsAction extends CommonAction{
	private static final Logger log = LoggerFactory.getLogger(GoodsAction.class); 
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("listGoods")
	public String listGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response){
		ServiceResponse<List<GoodsVo>> resp=goodsService.listGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("goodsList", resp.getData());
		}else{
			request.setAttribute("status", "service errro!");
		}
		return managerPage("goodsList");
	}

	@RequestMapping("editGoods")
	public String editGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response){
		GoodsVo param=new GoodsVo();
		param.setGid(goods.getGid());
		ServiceResponse<GoodsVo> resp=goodsService.queryGoods(param);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			ServiceResponse<List<TCategoryAttr>> resp2=categoryService.listAttrs(resp.getData().getCid());
			if(ShoppingContants.RESP_CODE_SUCESS.equals(resp2.getCode())){
				request.setAttribute("attrs", resp2.getData());
			}
			request.setAttribute("goods", resp.getData());
			return managerPage("editGoods");
		}else{
			return erro(request, resp.getMsg());
		}
	}
	@RequestMapping("updateGoods")
	public void updateGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String goodsJson=request.getParameter("goods");
		GoodsVo goods = JSON.parseObject(goodsJson, GoodsVo.class);
		//获取sku的最小价格，设置到商品
		BigDecimal price=goods.getSkus().get(0).getPrice();
		for(SkuVo sku:goods.getSkus()){
			if(price.doubleValue()>sku.getPrice().doubleValue()){
				price=sku.getPrice();
			}
		}
		goods.setPrice(price);
		ServiceResponse<GoodsVo> resp=goodsService.updateGoodsPageInfo(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("onShelf")
	public void onShelf(HttpServletRequest request,HttpServletResponse response) throws IOException{
		GoodsVo param=new GoodsVo();
		param.setGid(Long.valueOf(request.getParameter("gid")));
		param.setStatus(ShoppingContants.GOODS_STATUS_ON);
		ServiceResponse<GoodsVo> resp=goodsService.updateGoods(param);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("offShelf")
	public void offShelf(HttpServletRequest request,HttpServletResponse response) throws IOException{
		GoodsVo param=new GoodsVo();
		param.setGid(Long.valueOf(request.getParameter("gid")));
		param.setStatus(ShoppingContants.GOODS_STATUS_OFF);
		ServiceResponse<GoodsVo> resp=goodsService.updateGoods(param);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("addGoods")
	public String addGoods(HttpServletRequest request,HttpServletResponse response){
		return managerPage("addGoods");
	}
	@RequestMapping("uploadPic")
	public void uploadPic(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext()){
               //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
                	String originalFileName=file.getOriginalFilename();
                	String fileName=request.getParameter("goodsCode")+"."+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
                	String filePath=getGoodsUploadPath(fileName);
                    //上传
                	log.info("save file:{}",filePath);
                    file.transferTo(new File(filePath));
                    String requestPath=getGoodsRequestPath(fileName);
                    log.info("request path file:{}",requestPath);
                    response.getWriter().println(requestPath);
                }
            }
        }
	}
	
	@RequestMapping("saveGoods")
	public void saveGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String goodsJson=request.getParameter("goods");
		GoodsVo goods = JSON.parseObject(goodsJson, GoodsVo.class);
		//获取sku的最小价格，设置到商品
		BigDecimal price=goods.getSkus().get(0).getPrice();
		for(SkuVo sku:goods.getSkus()){
			if(price.doubleValue()>sku.getPrice().doubleValue()){
				price=sku.getPrice();
			}
		}
		goods.setPrice(price);
		ServiceResponse<GoodsVo> resp=goodsService.saveGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
}
