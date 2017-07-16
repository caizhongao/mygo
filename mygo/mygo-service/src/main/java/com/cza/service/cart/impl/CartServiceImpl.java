/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.service.cart.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.cart.TCart;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.dto.goods.TSku;
import com.cza.dto.goods.TSkuAttr;
import com.cza.dto.goods.TSkuStock;
import com.cza.mapper.cart.CartMapper;
import com.cza.mapper.goods.CategoryAttrMapper;
import com.cza.mapper.goods.SkuAttrMapper;
import com.cza.mapper.goods.SkuMapper;
import com.cza.mapper.goods.SkuStockMapper;
import com.cza.service.cart.CartService;
import com.cza.service.cart.vo.CartVo;
import com.cza.service.goods.vo.SkuAttrVo;
import com.cza.service.goods.vo.SkuVo;

/**
 * 作用：
 * @author zhongao
 * @date 2017年7月12日
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private SkuAttrMapper skuAttrMapper;
	@Autowired
	private CategoryAttrMapper attrMapper;
	@Autowired
	private SkuMapper skuMapper;
	
	@Autowired
	private SkuStockMapper stockMapper;
	@Override
	public ServiceResponse<CartVo> addCart(CartVo cart) {
		ServiceResponse<CartVo> resp=new ServiceResponse<CartVo>();
		try{	
			TCart listParam=new TCart();
			listParam.setSid(cart.getSid());
			listParam.setUid(cart.getUid());
			List<TCart>cartList=cartMapper.listCart(listParam);
			if(cartList!=null&&cartList.size()>0){
				TCart updateParam=cartList.get(0);
				updateParam.setNumber(updateParam.getNumber()+cart.getNumber());
				cartMapper.updateCart(updateParam);
			}else{
				TCart saveParam=new TCart();
				saveParam.setNumber(cart.getNumber());
				saveParam.setSid(cart.getSid());
				saveParam.setUid(cart.getUid());
				cartMapper.saveCart(saveParam);
			}
			resp.setData(cart);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("addCart failed",e);
		}
		return resp;
	}

	@Override
	public ServiceResponse<CartVo> removeCart(CartVo cart) {
		ServiceResponse<CartVo> resp=new ServiceResponse<CartVo>();
		try{
			TCart deleteParam=new TCart();
			deleteParam.setSid(cart.getSid());
			deleteParam.setUid(cart.getUid());
			cartMapper.deleteCart(deleteParam);
			resp.setData(cart);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("addCart failed",e);
		}
		return resp;
	}

	@Override
	public ServiceResponse<List<CartVo>> listCart(CartVo cart) {
		ServiceResponse<List<CartVo>> resp=new ServiceResponse<List<CartVo>>();
		try{
			TCart listParam=new TCart();
			listParam.setUid(cart.getUid());
			List<TCart> cartList=cartMapper.listCart(listParam);
			List<CartVo> voList=new ArrayList<>();
			if(cartList!=null&&cartList.size()>0){
				for(TCart result:cartList){
					CartVo vo=new CartVo();
					vo.setNumber(result.getNumber());
					vo.setSid(result.getSid());
					vo.setUid(result.getUid());
					//查询sku
					TSku sku=skuMapper.querySku(vo.getSid());
					SkuVo skuVo=new SkuVo();
					skuVo.setPrice(sku.getPrice());
					skuVo.setBarcode(sku.getBarcode());
					skuVo.setSid(sku.getSid());
					skuVo.setSkuPic(sku.getSkuPic());
					skuVo.setGid(sku.getGid());
					skuVo.setGoodsName(sku.getGoodsName());
					//购买的sku属性
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
					TSkuStock stockParam=new TSkuStock();
					stockParam.setSid(sku.getSid());
					TSkuStock stock=stockMapper.querySkuStock(stockParam);
					if(stock!=null){
						skuVo.setNumber(stock.getNumber());
						skuVo.setStock(stock.getStock());
					}else{
						skuVo.setNumber(0L);
						skuVo.setStock(0L);
					}
					
					
					vo.setSku(skuVo);
					vo.setAmount(skuVo.getPrice().multiply(new BigDecimal(vo.getNumber())));
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
			log.error("addCart failed",e);
		}
		return resp;
	}

}
