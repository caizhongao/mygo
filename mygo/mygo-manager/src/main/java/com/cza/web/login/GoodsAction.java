
    /**  
    * @Title: GoodsAction.java
    * @Package com.cza.web.manager
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午6:12:39
    * @version V1.0  
    */
    
package com.cza.web.login;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.cza.common.Pager;
import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.dto.goods.TGoods;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.goods.vo.SkuAttrVo;
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
@RequestMapping("login/goods")
public class GoodsAction extends CommonAction{
	private static final Logger log = LoggerFactory.getLogger(GoodsAction.class); 
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("listGoods")
	public String listGoods(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response){
		log.info("GoodsAction.listGoods 请求参数,goods:{}",goods);
		request.setAttribute("goods", goods);
		ServiceResponse<Pager<GoodsVo>> resp=goodsService.listGoods(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("pager", resp.getData());
		}else{
			request.setAttribute("status", "service errro!");
		}
		return webPage("goodsList");
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
			return webPage("editGoods");
		}else{
			return erro(request, resp);
		}
	}
	@RequestMapping("updateGoods")
	public void updateGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			String goodsJson=request.getParameter("goods");
			GoodsVo goods = JSON.parseObject(goodsJson, GoodsVo.class);
			log.info("updateGoods param:{}",goods);
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
				response.getWriter().println(new RespMsg("success", null));
			}else{
				response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_MSG_SYSTEM_ERRO));
			}
		} catch (Exception e) {
			log.error("updateGoods exception:",e);
			response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_MSG_SYSTEM_ERRO));
		}
	}
	
	@RequestMapping("onShelf")
	public void onShelf(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		goods.setStatus(ShoppingContants.GOODS_STATUS_ON);
		ServiceResponse<GoodsVo> resp=goodsService.updateGoodsOnShelf(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("offShelf")
	public void offShelf(@ModelAttribute GoodsVo goods,HttpServletRequest request,HttpServletResponse response) throws IOException{
		goods.setStatus(ShoppingContants.GOODS_STATUS_OFF);
		ServiceResponse<GoodsVo> resp=goodsService.updateGoodsOffShelf(goods);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("failed");
		}
	}
	
	@RequestMapping("addGoods")
	public String addGoods(HttpServletRequest request,HttpServletResponse response){
		return webPage("addGoods");
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
                	String fileName=request.getParameter("skuCode")+'_'+System.currentTimeMillis()+"."+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
                	String filePath=getGoodsUploadPath(fileName);
                    //上传
                	log.info("save file:{}",filePath);
                    file.transferTo(new File(filePath));
                    String requestPath=getGoodsRequestPath(fileName);
                    log.info("request path file:{}",requestPath);
                    response.getWriter().println("{\"skuCode\":\""+request.getParameter("skuCode")+"\",\"skuPic\":\""+requestPath+"\"}");
                }
            }
        }
	}
	
	@RequestMapping("saveGoods")
	public void saveGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
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
				response.getWriter().println(new RespMsg("success", null));
			}else if(ShoppingContants.RESP_CODE_GOODS_CODE_EXIST.equals(resp.getCode())){
				response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_MSG_GOODS_CODE_EXIST));
			}else{
				response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_MSG_SYSTEM_ERRO));
			}
		} catch (Exception e) {
			log.info("saveGoods exception:",e);
			response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_MSG_SYSTEM_ERRO));
		}
	}
	
	
	@RequestMapping("initTestData")
	public void initTestData(HttpServletRequest request,HttpServletResponse response){
		for(int i=1;i<5000;i++){
			int num=(int)(Math.random()*4)+1;
			if(num==1){
				makeGoods1(i);
			}else if(num==2){
				makeGoods2(i);
			}else if(num==3){
				makeGoods3(i);
			}else if(num==4){
				makeGoods4(i);
			}
    	}
	}

	
	    /**
	    * @Title: makeGoods1
	    * @Description: 生成服装数据
	    * @param @param i    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	private void makeGoods1(int i) {
		String pic[]={"http://120.77.65.75:8088//goods/test001.jpg?d=1498794002365","http://120.77.65.75:8088//goods/fuzhuang.jpg","http://120.77.65.75:8088//goods/kuzi.jpg?d=1498820818598"};
		String name[]={"情侣短袖黑白款","女士灰色有领休闲款T恤","夏季新款休闲裤男韩版修身九分裤夏天学生男裤长裤薄款男士裤子男"};
		GoodsVo goods=new GoodsVo();
		goods.setGoodsCode("test"+i);
		goods.setGoodsName(name[i%3]+i);
		goods.setPrice(new BigDecimal(99.0));
		goods.setCid(2l);
		goods.setGoodsPic(pic[i%3]);
		List<SkuVo> skus=new ArrayList<>();
		
		SkuVo sku1=new SkuVo();
		sku1.setBarcode("sku"+i+"1");
		sku1.setGoodsName(goods.getGoodsName());
		sku1.setNumber(100l);
		sku1.setPrice(new BigDecimal(99.0));
		List<SkuAttrVo> skuAttrVos1=new ArrayList<>();
		SkuAttrVo skuAttr1=new SkuAttrVo();
		skuAttr1.setAttrId(3l);
		skuAttr1.setAttrValue("黑色");
		skuAttrVos1.add(skuAttr1);
		SkuAttrVo skuAttr2=new SkuAttrVo();
		skuAttr2.setAttrId(4l);
		skuAttr2.setAttrValue("大码");
		skuAttrVos1.add(skuAttr2);
		sku1.setAttrs(skuAttrVos1);
		skus.add(sku1);
		
		SkuVo sku2=new SkuVo();
		sku2.setBarcode("sku"+i+"2");
		sku2.setGoodsName(goods.getGoodsName());
		sku2.setNumber(100l);
		sku2.setPrice(new BigDecimal(105.0));
		List<SkuAttrVo> skuAttrVos2=new ArrayList<>();
		SkuAttrVo skuAttr2_1=new SkuAttrVo();
		skuAttr2_1.setAttrId(3l);
		skuAttr2_1.setAttrValue("白色");
		skuAttrVos2.add(skuAttr2_1);
		SkuAttrVo skuAttr2_2=new SkuAttrVo();
		skuAttr2_2.setAttrId(4l);
		skuAttr2_2.setAttrValue("中码");
		skuAttrVos2.add(skuAttr2_2);
		sku2.setAttrs(skuAttrVos2);
		skus.add(sku2);
		
		SkuVo sku3=new SkuVo();
		sku3.setBarcode("sku"+i+"3");
		sku3.setGoodsName(goods.getGoodsName());
		sku3.setNumber(100l);
		sku3.setPrice(new BigDecimal(100.0));
		List<SkuAttrVo> skuAttrVos3=new ArrayList<>();
		SkuAttrVo skuAttr3_1=new SkuAttrVo();
		skuAttr3_1.setAttrId(3l);
		skuAttr3_1.setAttrValue("灰色");
		skuAttrVos3.add(skuAttr3_1);
		SkuAttrVo skuAttr3_2=new SkuAttrVo();
		skuAttr3_2.setAttrId(4l);
		skuAttr3_2.setAttrValue("小码");
		skuAttrVos3.add(skuAttr3_2);
		sku3.setAttrs(skuAttrVos3);
		skus.add(sku3);
		goods.setSkus(skus);
		goodsService.saveGoods(goods);
		log.info("goods:{}",goods);
	}
	
	
	
	   /**
	    * @Title: makeGoods1
	    * @Description: 生成电器数据
	    * @param @param i    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	private void makeGoods2(int i) {
		String pic[]={"http://120.77.65.75:8088//goods/dianshi1.jpg?","http://120.77.65.75:8088//goods/bingxiang.jpg?d=1498820673193","http://120.77.65.75:8088//goods/xiyiji.jpg?d=1498820747866"};
		String name[]={"4k液晶高清大电视","对开四门变频电脑冰箱节能家用","智能波轮家用全自动洗衣机 大容量"};
		
		GoodsVo goods=new GoodsVo();
		goods.setGoodsCode("test"+i);
		goods.setGoodsName(name[i%3]+i);
		goods.setPrice(new BigDecimal(1999.0));
		goods.setCid(4l);
		goods.setGoodsPic(pic[i%3]);
		List<SkuVo> skus=new ArrayList<>();
		
		SkuVo sku1=new SkuVo();
		sku1.setBarcode("sku"+i+"1");
		sku1.setGoodsName(goods.getGoodsName());
		sku1.setNumber(100l);
		sku1.setPrice(new BigDecimal(2299.0));
		List<SkuAttrVo> skuAttrVos1=new ArrayList<>();
		SkuAttrVo skuAttr1=new SkuAttrVo();
		skuAttr1.setAttrId(5l);
		skuAttr1.setAttrValue("黑色");
		skuAttrVos1.add(skuAttr1);
		SkuAttrVo skuAttr2=new SkuAttrVo();
		skuAttr2.setAttrId(6l);
		skuAttr2.setAttrValue("32寸");
		skuAttrVos1.add(skuAttr2);
		sku1.setAttrs(skuAttrVos1);
		skus.add(sku1);
		
		SkuVo sku2=new SkuVo();
		sku2.setBarcode("sku"+i+"2");
		sku2.setGoodsName(goods.getGoodsName());
		sku2.setNumber(100l);
		sku2.setPrice(new BigDecimal(2999.0));
		List<SkuAttrVo> skuAttrVos2=new ArrayList<>();
		SkuAttrVo skuAttr2_1=new SkuAttrVo();
		skuAttr2_1.setAttrId(5l);
		skuAttr2_1.setAttrValue("灰色");
		skuAttrVos2.add(skuAttr2_1);
		SkuAttrVo skuAttr2_2=new SkuAttrVo();
		skuAttr2_2.setAttrId(6l);
		skuAttr2_2.setAttrValue("34寸");
		skuAttrVos2.add(skuAttr2_2);
		sku2.setAttrs(skuAttrVos2);
		skus.add(sku2);
		
		SkuVo sku3=new SkuVo();
		sku3.setBarcode("sku"+i+"3");
		sku3.setGoodsName(goods.getGoodsName());
		sku3.setNumber(100l);
		sku3.setPrice(new BigDecimal(1999.0));
		List<SkuAttrVo> skuAttrVos3=new ArrayList<>();
		SkuAttrVo skuAttr3_1=new SkuAttrVo();
		skuAttr3_1.setAttrId(5l);
		skuAttr3_1.setAttrValue("银色");
		skuAttrVos3.add(skuAttr3_1);
		SkuAttrVo skuAttr3_2=new SkuAttrVo();
		skuAttr3_2.setAttrId(6l);
		skuAttr3_2.setAttrValue("30寸");
		skuAttrVos3.add(skuAttr3_2);
		sku3.setAttrs(skuAttrVos3);
		skus.add(sku3);
		goods.setSkus(skus);
		goodsService.saveGoods(goods);
		log.info("goods:{}",goods);
	}
	
	
	
	 /**
	    * @Title: makeGoods1
	    * @Description: 生成电器数据
	    * @param @param i    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	private void makeGoods3(int i) {
		String pic[]={"http://120.77.65.75:8088//goods/shipin.jpg?d=1498815087266","http://120.77.65.75:8088//goods/mianbao.jpg?d=1498819804254","http://120.77.65.75:8088//goods/gaodian.jpg?d=1498820211974"};
		String name[]={"小贱香香脆米锅巴","良品铺子手撕大面包营养早餐食品","三只松鼠休闲零食特产小吃饼干糕点点心"};
		
		
		
		GoodsVo goods=new GoodsVo();
		goods.setGoodsCode("test"+i);
		goods.setGoodsName(name[i%3]+i);
		goods.setPrice(new BigDecimal(2.2));
		goods.setCid(1l);
		goods.setGoodsPic(pic[i%3]+i);
		List<SkuVo> skus=new ArrayList<>();
		
		SkuVo sku1=new SkuVo();
		sku1.setBarcode("sku"+i+"1");
		sku1.setGoodsName(goods.getGoodsName());
		sku1.setNumber(100l);
		sku1.setPrice(new BigDecimal(2.2));
		List<SkuAttrVo> skuAttrVos1=new ArrayList<>();
		SkuAttrVo skuAttr1=new SkuAttrVo();
		skuAttr1.setAttrId(1l);
		skuAttr1.setAttrValue("100g");
		skuAttrVos1.add(skuAttr1);
		SkuAttrVo skuAttr2=new SkuAttrVo();
		skuAttr2.setAttrId(2l);
		skuAttr2.setAttrValue("袋装");
		skuAttrVos1.add(skuAttr2);
		sku1.setAttrs(skuAttrVos1);
		skus.add(sku1);
		
		SkuVo sku2=new SkuVo();
		sku2.setBarcode("sku"+i+"2");
		sku2.setGoodsName(goods.getGoodsName());
		sku2.setNumber(100l);
		sku2.setPrice(new BigDecimal(4));
		List<SkuAttrVo> skuAttrVos2=new ArrayList<>();
		SkuAttrVo skuAttr2_1=new SkuAttrVo();
		skuAttr2_1.setAttrId(1l);
		skuAttr2_1.setAttrValue("200g");
		skuAttrVos2.add(skuAttr2_1);
		SkuAttrVo skuAttr2_2=new SkuAttrVo();
		skuAttr2_2.setAttrId(2l);
		skuAttr2_2.setAttrValue("袋装");
		skuAttrVos2.add(skuAttr2_2);
		sku2.setAttrs(skuAttrVos2);
		skus.add(sku2);
		
		SkuVo sku3=new SkuVo();
		sku3.setBarcode("sku"+i+"3");
		sku3.setGoodsName(goods.getGoodsName());
		sku3.setNumber(100l);
		sku3.setPrice(new BigDecimal(7));
		List<SkuAttrVo> skuAttrVos3=new ArrayList<>();
		SkuAttrVo skuAttr3_1=new SkuAttrVo();
		skuAttr3_1.setAttrId(1l);
		skuAttr3_1.setAttrValue("500g");
		skuAttrVos3.add(skuAttr3_1);
		SkuAttrVo skuAttr3_2=new SkuAttrVo();
		skuAttr3_2.setAttrId(2l);
		skuAttr3_2.setAttrValue("散装");
		skuAttrVos3.add(skuAttr3_2);
		sku3.setAttrs(skuAttrVos3);
		skus.add(sku3);
		
		SkuVo sku4=new SkuVo();
		sku4.setBarcode("sku"+i+"4");
		sku4.setGoodsName(goods.getGoodsName());
		sku4.setNumber(100l);
		sku4.setPrice(new BigDecimal(4));
		List<SkuAttrVo> skuAttrVos4=new ArrayList<>();
		SkuAttrVo skuAttr4_1=new SkuAttrVo();
		skuAttr4_1.setAttrId(1l);
		skuAttr4_1.setAttrValue("250g");
		skuAttrVos4.add(skuAttr4_1);
		SkuAttrVo skuAttr4_2=new SkuAttrVo();
		skuAttr4_2.setAttrId(2l);
		skuAttr4_2.setAttrValue("散装");
		skuAttrVos4.add(skuAttr4_2);
		sku4.setAttrs(skuAttrVos4);
		skus.add(sku4);
		
		
		goods.setSkus(skus);
		goodsService.saveGoods(goods);
		log.info("goods:{}",goods);
	}
	
	
	 /**
	    * @Title: makeGoods1
	    * @Description: 生成家具数据
	    * @param @param i    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	private void makeGoods4(int i) {
		String pic[]={"http://120.77.65.75:8088//goods/test003.jpg","http://120.77.65.75:8088//goods/chuangdian.jpg?d=1498820449657","http://120.77.65.75:8088//goods/dengzi.jpg?d=1498820552660"};
		String name[]={"居家舒适大沙发","单双人榻榻米床垫保护垫薄防滑床护垫","小凳子实木沙发凳布艺高腿小板凳方凳矮凳时尚创意穿鞋换鞋凳"};
		
		
		
		GoodsVo goods=new GoodsVo();
		goods.setGoodsCode("test"+i);
		goods.setGoodsName(name[i%3]+i);
		goods.setPrice(new BigDecimal(499));
		goods.setCid(3l);
		goods.setGoodsPic(pic[i%3]);
		List<SkuVo> skus=new ArrayList<>();
		
		SkuVo sku1=new SkuVo();
		sku1.setBarcode("sku"+i+"1");
		sku1.setGoodsName(goods.getGoodsName());
		sku1.setNumber(100l);
		sku1.setPrice(new BigDecimal(499));
		skus.add(sku1);
		
		goods.setSkus(skus);
		goodsService.saveGoods(goods);
		log.info("goods:{}",goods);
	}
	
}
