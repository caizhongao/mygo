
    /**  
    * @Title: GoodsServiceImpl.java
    * @Package com.cza.service.goods.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午6:28:28
    * @version V1.0  
    */
    
package com.cza.service.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.goods.TCategory;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.dto.goods.TGoods;
import com.cza.dto.goods.TSku;
import com.cza.dto.goods.TSkuAttr;
import com.cza.dto.goods.TSkuStock;
import com.cza.mapper.goods.CategoryAttrMapper;
import com.cza.mapper.goods.CategoryMapper;
import com.cza.mapper.goods.GoodsMapper;
import com.cza.mapper.goods.SkuAttrMapper;
import com.cza.mapper.goods.SkuMapper;
import com.cza.mapper.goods.SkuStockMapper;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.goods.vo.SkuAttrVo;
import com.cza.service.goods.vo.SkuVo;


/**
    * @ClassName: GoodsServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日下午6:28:28
    *
    */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private CategoryAttrMapper attrMapper;
	@Autowired
	private SkuStockMapper stockMapper;
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private SkuAttrMapper skuAttrMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class); 
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param goods
	    * @return
	    * @see com.cza.service.goods.GoodsService#listGoods(com.cza.service.goods.vo.GoodsVo)
	    */
	    
	@Override
	public ServiceResponse<List<GoodsVo>> listGoods(GoodsVo goods) {
		ServiceResponse<List<GoodsVo>> resp=new ServiceResponse<>();
		List<GoodsVo>voList=null;
		try {
			TGoods param=new TGoods();
			param.setCid(goods.getCid());
			param.setGoodsCode(goods.getGoodsCode());
			param.setGoodsName(goods.getGoodsName());
			param.setStatus(goods.getStatus());
			param.setPrice(goods.getPrice());
			List<TGoods> goodsList=goodsMapper.listGoods(param);
			if(goodsList!=null&&goodsList.size()>0){
				voList=new ArrayList<GoodsVo>();
				for(TGoods result:goodsList){
					GoodsVo vo=new GoodsVo();
					vo.setCid(result.getCid());
					TCategory category=categoryMapper.queryCategory(result.getCid());
					vo.setCategoryName(category.getCname());
					vo.setCreateTime(result.getCreateTime());
					vo.setGid(result.getGid());
					vo.setGoodsCode(result.getGoodsCode());
					vo.setGoodsName(result.getGoodsName());
					vo.setGoodsPic(result.getGoodsPic());
					vo.setPrice(result.getPrice());
					vo.setUpdateTime(result.getUpdateTime());
					vo.setStatus(result.getStatus());
					voList.add(vo);
				}
			}
			resp.setData(voList);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("查询商品出错!",e);
		}
		return resp;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param goods
	    * @return
	    * @see com.cza.service.goods.GoodsService#saveGoods(com.cza.service.goods.vo.GoodsVo)
	    */
	    
	@Override
	public ServiceResponse<GoodsVo> saveGoods(GoodsVo goods) {
		ServiceResponse<GoodsVo> resp=new ServiceResponse<GoodsVo>();
		try {
			Long now=System.currentTimeMillis()/1000;
			TGoods param=new TGoods();
			param.setCid(goods.getCid());
			param.setCreateTime(now);
			param.setUpdateTime(now);
			param.setGid(null);//自动生成
			param.setGoodsCode(goods.getGoodsCode());
			param.setGoodsName(goods.getGoodsName());
			param.setGoodsPic(goods.getGoodsPic());
			param.setPrice(goods.getPrice());
			param.setStatus(ShoppingContants.GOODS_STATUS_WAIT);
			goodsMapper.saveGoods(param);
			goods.setGid(param.getGid());
			//保存sku
			List<SkuVo>skus=goods.getSkus();
			for(SkuVo sku:skus){
				//保存sku基本信息
				TSku skuParam=new TSku();
				skuParam.setBarcode(sku.getBarcode());
				skuParam.setPrice(sku.getPrice());
				skuParam.setCreateTime(now);
				skuParam.setUpdateTime(now);
				skuParam.setGid(goods.getGid());
				skuParam.setGoodsName(goods.getGoodsName());
				skuParam.setSkuPic(goods.getGoodsPic());
				skuParam.setStatus(ShoppingContants.SKU_STATUS_NORMAL);
				skuMapper.saveSku(skuParam);
				sku.setSid(skuParam.getSid());
				//保存库存信息
				TSkuStock stock=new TSkuStock();
				stock.setSid(sku.getSid());
				stock.setStock(sku.getNumber());
				stock.setNumber(sku.getStock());
				stockMapper.saveSkuStock(stock);
				//保存sku属性
				List<SkuAttrVo>attrs=sku.getAttrs();
				if(attrs!=null&&attrs.size()>0){
					for(SkuAttrVo attr:attrs){
						TSkuAttr attrParam=new TSkuAttr();
						attrParam.setAttrValue(attr.getAttrValue());
						attrParam.setCaid(attr.getAttrId());
						attrParam.setSid(sku.getSid());
						skuAttrMapper.saveSkuAttr(attrParam);
					}
				}
			}
			resp.setData(goods);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("保存商品异常!",e);
		}
		return resp;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param param
	    * @return
	    * @see com.cza.service.goods.GoodsService#queryGoods(com.cza.service.goods.vo.GoodsVo)
	    */
	    
	@Override
	public ServiceResponse<GoodsVo> queryGoods(GoodsVo param) {
		ServiceResponse<GoodsVo> resp=new ServiceResponse<GoodsVo>();
		try {
			GoodsVo goodsVo=new GoodsVo();
			TGoods goods=goodsMapper.queryGoods(param.getGid());
			if(goods==null){
				resp.setCode(ShoppingContants.RESP_CODE_GOODS_NOT_EXIST);
				resp.setMsg(ShoppingContants.RESP_MSG_GOODS_NOT_EXIST);
				return resp;
			}
			goodsVo.setCid(goods.getCid());
			goodsVo.setGid(goods.getGid());
			goodsVo.setGoodsCode(goods.getGoodsCode());
			goodsVo.setGoodsName(goods.getGoodsName());
			goodsVo.setGoodsPic(goods.getGoodsPic());
			goodsVo.setPrice(goods.getPrice());
			goodsVo.setStatus(goods.getStatus());
			goodsVo.setSales(goods.getSales());
			TSku skuParam=new TSku();
			skuParam.setGid(goods.getGid());
			skuParam.setStatus(ShoppingContants.SKU_STATUS_NORMAL);
			List<TSku> skus=skuMapper.listSkus(skuParam);
			List<SkuVo> skuVoList=new ArrayList<SkuVo>();
			Long goodsStock=0l;
			if(skus!=null&&skus.size()>0){
				for(TSku sku:skus){
					SkuVo skuVo=new SkuVo();
					skuVo.setPrice(sku.getPrice());
					skuVo.setBarcode(sku.getBarcode());
					skuVo.setSid(sku.getSid());
					 
					TSkuStock stockParam=new TSkuStock();
					stockParam.setSid(sku.getSid());
					TSkuStock stock=stockMapper.querySkuStock(stockParam);
					if(stock!=null){
						skuVo.setNumber(stock.getNumber());
						skuVo.setStock(stock.getStock());
						goodsStock+=stock.getStock();
					}else{
						skuVo.setNumber(0L);
						skuVo.setStock(0L);
					}
					TSkuAttr attrParam=new TSkuAttr();
					attrParam.setSid(sku.getSid());
					List<TSkuAttr>attrs=skuAttrMapper.listSkuAttrs(attrParam);
					List<SkuAttrVo> attrVoList=new ArrayList<SkuAttrVo>();
					if(attrs!=null&&attrs.size()>0){
						for(TSkuAttr attr:attrs){
							SkuAttrVo attrVo=new SkuAttrVo();
							attrVo.setAttrId(attr.getCaid());
							attrVo.setAttrValue(attr.getAttrValue());
							TCategoryAttr ca= attrMapper.queryAttr(attr.getCaid());
							attrVo.setAttrName(ca.getAttrName());
							attrVoList.add(attrVo);
						}
					}
					skuVo.setAttrs(attrVoList);
					skuVoList.add(skuVo);
				}
			}
			goodsVo.setStock(goodsStock);
			goodsVo.setSkus(skuVoList);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setData(goodsVo);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("查询商品异常!",e);
		}
		return resp;
	}


	
	@Override
	public ServiceResponse<GoodsVo> updateGoods(GoodsVo goods) {
		ServiceResponse<GoodsVo> resp=new ServiceResponse<GoodsVo>();
		try {
			Long now=System.currentTimeMillis()/1000;
			TGoods param=new TGoods();
			param.setGid(goods.getGid());
			param.setCid(goods.getCid());
			param.setUpdateTime(now);
			param.setGoodsCode(goods.getGoodsCode());
			param.setGoodsName(goods.getGoodsName());
			param.setGoodsPic(goods.getGoodsPic());
			param.setPrice(goods.getPrice());
			param.setStatus(goods.getStatus());
			goodsMapper.updateGoods(param);	
			resp.setData(goods);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("更新商品异常!",e);
		}
		return resp;
	}
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param goods
	    * @return
	    * @see com.cza.service.goods.GoodsService#updateGoods(com.cza.service.goods.vo.GoodsVo)
	    */
	    
	@Override
	public ServiceResponse<GoodsVo> updateGoodsPageInfo(GoodsVo goods) {
		ServiceResponse<GoodsVo> resp=new ServiceResponse<GoodsVo>();
		try {
			Long now=System.currentTimeMillis()/1000;
			TGoods param=new TGoods();
			param.setGid(goods.getGid());
			param.setCid(goods.getCid());
			param.setUpdateTime(now);
			param.setGoodsCode(goods.getGoodsCode());
			param.setGoodsName(goods.getGoodsName());
			param.setGoodsPic(goods.getGoodsPic());
			param.setPrice(goods.getPrice());
			param.setStatus(ShoppingContants.GOODS_STATUS_WAIT);
			goodsMapper.updateGoods(param);
			//保存sku
			List<SkuVo>skus=goods.getSkus();
			//先更新已有的sku为删除状态
			TSku updateSkuStatusParam=new TSku();
			updateSkuStatusParam.setGid(param.getGid());
			updateSkuStatusParam.setStatus(ShoppingContants.SKU_STATUS_DELETE);
			skuMapper.updateStatusByGid(updateSkuStatusParam);
			for(SkuVo sku:skus){
				if(sku.getSid()!=null){
					TSku updateSkuParam=new TSku();
					updateSkuParam.setSid(sku.getSid());
					updateSkuParam.setBarcode(sku.getBarcode());
					updateSkuParam.setUpdateTime(now);
					updateSkuParam.setGid(goods.getGid());
					updateSkuParam.setPrice(sku.getPrice());
					updateSkuParam.setSkuPic(goods.getGoodsPic());
					updateSkuParam.setStatus(ShoppingContants.SKU_STATUS_NORMAL);
					skuMapper.updateSku(updateSkuParam);
					
					//保存库存信息
					TSkuStock stock=new TSkuStock();
					stock.setSid(sku.getSid());
					stock.setStock(sku.getStock());
					stock.setNumber(sku.getNumber());
					stockMapper.updateSkuStock(stock);
					
					skuAttrMapper.deleteAttrBySkuId(sku.getSid());
					List<SkuAttrVo>attrs=sku.getAttrs();
					if(attrs!=null&&attrs.size()>0){
						for(SkuAttrVo attr:attrs){
							TSkuAttr attrParam=new TSkuAttr();
							attrParam.setAttrValue(attr.getAttrValue());
							attrParam.setCaid(attr.getAttrId());
							attrParam.setSid(sku.getSid());
							skuAttrMapper.saveSkuAttr(attrParam);
						}
					}
				}else{
					TSku skuParam=new TSku();
					skuParam.setBarcode(sku.getBarcode());
					skuParam.setCreateTime(now);
					skuParam.setUpdateTime(now);
					skuParam.setGid(goods.getGid());
					skuParam.setGoodsName(goods.getGoodsName());
					skuParam.setPrice(sku.getPrice());
					skuParam.setSkuPic(goods.getGoodsPic());
					skuParam.setStatus(ShoppingContants.SKU_STATUS_NORMAL);
					skuMapper.saveSku(skuParam);
					sku.setSid(skuParam.getSid());
					
					//保存库存信息
					TSkuStock stock=new TSkuStock();
					stock.setSid(sku.getSid());
					stock.setStock(sku.getNumber());
					stock.setNumber(sku.getNumber());
					stockMapper.saveSkuStock(stock);
					
					List<SkuAttrVo>attrs=sku.getAttrs();
					if(attrs!=null&&attrs.size()>0){
						for(SkuAttrVo attr:attrs){
							TSkuAttr attrParam=new TSkuAttr();
							attrParam.setAttrValue(attr.getAttrValue());
							attrParam.setCaid(attr.getAttrId());
							attrParam.setSid(sku.getSid());
							skuAttrMapper.saveSkuAttr(attrParam);
						}
					}
				}
			}
			resp.setData(goods);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("更新商品异常!",e);
		}
		return resp;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param goods
	    * @return
	    * @see com.cza.service.goods.GoodsService#listNewGoods(com.cza.service.goods.vo.GoodsVo)
	    */
	    
	@Override
	public ServiceResponse<List<GoodsVo>> listNewGoods(GoodsVo goods) {
		ServiceResponse<List<GoodsVo>> resp=new ServiceResponse<List<GoodsVo>>();
		List<GoodsVo>voList=null;
		try {
			List<TGoods>goodsList=goodsMapper.listNewGoods();
			if(goodsList!=null&&goodsList.size()>0){
				voList=new ArrayList<GoodsVo>();
				for(TGoods tgoods:goodsList){
					GoodsVo vo=new GoodsVo();
					vo.setCid(tgoods.getCid());
					vo.setGid(tgoods.getGid());
					vo.setGoodsCode(tgoods.getGoodsCode());
					vo.setGoodsName(tgoods.getGoodsName());
					vo.setGoodsPic(tgoods.getGoodsPic());
					vo.setPrice(tgoods.getPrice());
					voList.add(vo);
				}
			}
			resp.setData(voList);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("查询商品异常!",e);
		}
		return resp;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param goods
	    * @return
	    * @see com.cza.service.goods.GoodsService#listHotGoods(com.cza.service.goods.vo.GoodsVo)
	    */
	    
	@Override
	public ServiceResponse<List<GoodsVo>> listHotGoods(GoodsVo goods) {
		ServiceResponse<List<GoodsVo>> resp=new ServiceResponse<List<GoodsVo>>();
		List<GoodsVo>voList=null;
		try {
			List<TGoods>goodsList=goodsMapper.listHotGoods();
			if(goodsList!=null&&goodsList.size()>0){
				voList=new ArrayList<GoodsVo>();
				for(TGoods tgoods:goodsList){
					GoodsVo vo=new GoodsVo();
					vo.setCid(tgoods.getCid());
					vo.setGid(tgoods.getGid());
					vo.setGoodsCode(tgoods.getGoodsCode());
					vo.setGoodsName(tgoods.getGoodsName());
					vo.setGoodsPic(tgoods.getGoodsPic());
					vo.setPrice(tgoods.getPrice());
					voList.add(vo);
				}
			}
			resp.setData(voList);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("查询商品异常!",e);
		}
		return resp;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param sid
	    * @see com.cza.service.goods.GoodsService#querySku(java.lang.Long)
	    */
	    
	@Override
	public ServiceResponse<SkuVo> querySku(Long sid) {
		ServiceResponse<SkuVo> resp=new ServiceResponse<SkuVo>();
		try {
			TSku sku=skuMapper.querySku(sid);
			if(sku==null){
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_SKU_NOT_EXIST);
				resp.setCode(ShoppingContants.RESP_CODE_SKU_NOT_EXIST);
			}
			SkuVo skuVo=new SkuVo();
			skuVo.setPrice(sku.getPrice());
			skuVo.setBarcode(sku.getBarcode());
			skuVo.setSid(sku.getSid());
			skuVo.setGoodsName(sku.getGoodsName());
			TSkuAttr attrParam=new TSkuAttr();
			attrParam.setSid(sku.getSid());
			List<TSkuAttr>attrs=skuAttrMapper.listSkuAttrs(attrParam);
			List<SkuAttrVo> attrVoList=new ArrayList<SkuAttrVo>();
			if(attrs!=null&&attrs.size()>0){
				for(TSkuAttr attr:attrs){
					SkuAttrVo attrVo=new SkuAttrVo();
					attrVo.setAttrId(attr.getCaid());
					attrVo.setAttrValue(attr.getAttrValue());
					TCategoryAttr ca= attrMapper.queryAttr(attr.getCaid());
					attrVo.setAttrName(ca.getAttrName());
					attrVoList.add(attrVo);
				}
			}
			skuVo.setAttrs(attrVoList);
			resp.setData(skuVo);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("查询sku异常!",e);
		}
		return resp;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param sid
	    * @return
	    * @see com.cza.service.goods.GoodsService#querySkuStock(java.lang.Long)
	    */
	    
	@Override
	public ServiceResponse<TSkuStock> querySkuStock(Long sid) {
		ServiceResponse<TSkuStock> resp=new ServiceResponse<>();
		try{
			TSkuStock queryParam=new TSkuStock();
			queryParam.setSid(sid);
			TSkuStock result=stockMapper.querySkuStock(queryParam);
			resp.setData(result);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("查询sku异常!",e);
		}
		return resp;
	}
	

}
