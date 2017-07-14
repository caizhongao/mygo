
    /**  
    * @Title: OrderServiceImpl.java
    * @Package com.cza.service.order.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月1日下午10:23:25
    * @version V1.0  
    */
    
package com.cza.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TUserAddr;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.dto.goods.TGoods;
import com.cza.dto.goods.TSku;
import com.cza.dto.goods.TSkuAttr;
import com.cza.dto.goods.TSkuStock;
import com.cza.dto.order.TOrder;
import com.cza.dto.user.TUser;
import com.cza.mapper.addr.UserAddrMapper;
import com.cza.mapper.goods.CategoryAttrMapper;
import com.cza.mapper.goods.GoodsMapper;
import com.cza.mapper.goods.SkuAttrMapper;
import com.cza.mapper.goods.SkuMapper;
import com.cza.mapper.goods.SkuStockMapper;
import com.cza.mapper.order.OrderMapper;
import com.cza.mapper.user.UserMapper;
import com.cza.service.goods.vo.SkuAttrVo;
import com.cza.service.goods.vo.SkuVo;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.PreOrderVo;
import com.cza.service.order.vo.OrderVo;

/**
    * @ClassName: OrderServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月1日下午10:23:25
    *
    */
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Autowired
	private SkuStockMapper stockMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserAddrMapper addrMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private SkuAttrMapper skuAttrMapper;
	@Autowired
	private CategoryAttrMapper attrMapper;
	@Autowired
	private SkuMapper skuMapper;
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class); 
	    /* (非 Javadoc)
	    * 1.扣减sku库存
	    * 2.保存订单信息
	    * 3.增加商品销量
	    * @param orderVo
	    * @see com.cza.service.order.OrderService#saveOrder(com.cza.service.order.vo.OrderVo)
	    */
	    
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ServiceResponse<PreOrderVo> saveOrder(PreOrderVo orderVo) {
		ServiceResponse<PreOrderVo> resp=new ServiceResponse<PreOrderVo>();
			TSkuStock skuStock=new TSkuStock();
			skuStock.setSid(orderVo.getSkuId());
			skuStock.setStock(orderVo.getNumber().longValue());
			int row=stockMapper.reduceSkuStock(skuStock);
			if(row==0){//扣减库存失败
				log.warn("扣减库存失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
				resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
				return resp;
			}
			Long now=System.currentTimeMillis()/1000;
			TOrder saveOrder=new TOrder();
			TUserAddr addr=addrMapper.queryAddr(orderVo.getAddrId());
			if(addr==null){
				log.warn("查询地址失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
				resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
				return resp;
			}
			saveOrder.setAddr(addr.getAddr());
			saveOrder.setArea(addr.getArea());
			saveOrder.setCity(addr.getCity());
			saveOrder.setMobilphone(addr.getMobilphone());
			saveOrder.setProvince(addr.getProvince());
			saveOrder.setReceiver(addr.getReceiver());
			//金额
			TSku sku=skuMapper.querySku(orderVo.getSkuId());
			saveOrder.setOrderPrice(sku.getPrice());
			saveOrder.setAmount(sku.getPrice().multiply(new BigDecimal(orderVo.getNumber())));
			saveOrder.setSid(orderVo.getSkuId());
			saveOrder.setGid(sku.getGid());
			saveOrder.setGoodsName(sku.getGoodsName());
			saveOrder.setOid(makeOrderId());
			saveOrder.setUid(orderVo.getUid());
			saveOrder.setUserName(orderVo.getUserName());
			saveOrder.setNumber(orderVo.getNumber());
			saveOrder.setCreateTime(now);
			saveOrder.setUpdateTime(now);
			saveOrder.setOrderVersion(0L);
			orderMapper.saveOrder(saveOrder);
			orderVo.setOrderId(saveOrder.getOid());
			//销量
			TGoods goods=goodsMapper.queryGoods(sku.getGid());
			goods.setSales(goods.getSales()+orderVo.getNumber());
			goodsMapper.updateGoods(goods);
			resp.setData(orderVo);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		return resp;
	}
	
	
	
	private Long makeOrderId(){
		return System.currentTimeMillis()/1000;
	}
	
    /* (非 Javadoc)
    * 
    * 
    * @param order
    * @return
    * @see com.cza.service.order.OrderService#listOrder(com.cza.dto.order.TOrder)
    */
    
@Override
public ServiceResponse<List<Long>> listOrderIds(OrderVo listParam) {
	ServiceResponse<List<Long>> resp=new ServiceResponse<List<Long>>();
	try {
		listParam.setStart((listParam.getPageNum()-1)*listParam.getPageSize());
		log.info("listOrderIds param:{}",listParam);
		List<Long> orderIds=orderMapper.listOrderIds(listParam);
		resp.setData(orderIds);
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
	    * @param order
	    * @return
	    * @see com.cza.service.order.OrderService#listOrder(com.cza.dto.order.TOrder)
	    */
	    
	@Override
	public ServiceResponse<Pager<OrderVo>> listOrder(OrderVo listParam) {
		ServiceResponse<Pager<OrderVo>> resp=new ServiceResponse<Pager<OrderVo>>();
		try {
			Long count=orderMapper.countOrder(listParam);
			listParam.setStart((listParam.getPageNum()-1)*listParam.getPageSize());
			List<TOrder> orderList=orderMapper.listOrder(listParam);
			List<OrderVo>voList=new ArrayList<>();
			if(orderList!=null&&orderList.size()>0){
				for(TOrder order:orderList){
					OrderVo vo=new OrderVo();
					BeanUtils.copyProperties(order, vo);
					TSku sku=skuMapper.querySku(vo.getSid());
					SkuVo skuVo=new SkuVo();
					skuVo.setPrice(sku.getPrice());
					skuVo.setBarcode(sku.getBarcode());
					skuVo.setSid(sku.getSid());
					skuVo.setSkuPic(sku.getSkuPic());
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
					vo.setSku(skuVo);
					voList.add(vo);
				}
			}
			resp.setData(new Pager<OrderVo>(listParam.getPageSize(),listParam.getPageNum(),count,voList));
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
	    * @param order
	    * @return
	    * @see com.cza.service.order.OrderService#updateOrder(com.cza.service.order.vo.ListOrderVo)
	    */
	    
	@Override
	public ServiceResponse<OrderVo> updateOrder(OrderVo order) {
		ServiceResponse<OrderVo> resp=new ServiceResponse<OrderVo>();
		try {
			TOrder updateParam=new TOrder();
			BeanUtils.copyProperties(order, updateParam);
			orderMapper.updateOrder(updateParam);
			resp.setData(order);
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

	
	
	/**
	 * @param 订单id
	 * */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ServiceResponse<OrderVo> closeOrder(OrderVo orderVo) {
		ServiceResponse<OrderVo> resp=new ServiceResponse<OrderVo>();
			TOrder queryOrder=orderMapper.queryOrder(orderVo.getOid());
			if(ShoppingContants.ORDER_STATUS_NORMAL.equals(queryOrder.getStatus())&&ShoppingContants.ORDER_PAY_STATUS_NOT.equals(queryOrder.getPayStatus())){
				TOrder updateParam=new TOrder();
				updateParam.setOrderVersion(queryOrder.getOrderVersion()+1);
				updateParam.setOid(queryOrder.getOid());
				updateParam.setStatus(orderVo.getStatus());
				updateParam.setDeleteDesc(orderVo.getDeleteDesc());
				int row=orderMapper.updateOrder(updateParam);
				if(row==0){
					log.warn("订单已被操作，此次操作失败!");
					resp.setData(null);
					resp.setMsg(ShoppingContants.RESP_MSG_ORDER_HAS_OPT);
					resp.setCode(ShoppingContants.RESP_CODE_ORDER_HAS_OPT);
					return resp;
				}
				//订单关闭完后，归还库存
				TSkuStock skuStock=new TSkuStock();
				skuStock.setSid(queryOrder.getSid());
				skuStock.setStock(-queryOrder.getNumber());
				stockMapper.reduceSkuStock(skuStock);
				log.info("订单关闭成功，订单号:{}",orderVo.getOid());
				resp.setData(orderVo);
				resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
				resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			}else{
				log.warn("订单已被操作，此次操作失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_ORDER_HAS_OPT);
				resp.setCode(ShoppingContants.RESP_CODE_ORDER_HAS_OPT);
				return resp;
			}
			return resp;
	}


    /* (非 Javadoc)
    * 
    * 
    * @param order
    * @return
    * @see com.cza.service.order.OrderService#updatePayStatus(com.cza.service.order.vo.OrderVo)
    */
	@Override
	public ServiceResponse<OrderVo> orderPay(OrderVo orderVo) {
		ServiceResponse<OrderVo> resp=new ServiceResponse<OrderVo>();
		try {
			TOrder queryOrder=orderMapper.queryOrder(orderVo.getOid());
			//按照阿里的要求，验证订单号和订单金额
			if(queryOrder==null){
				log.warn("订单不存在，此次操作失败,oid:{}!",orderVo.getOid());
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_ORDER_NOT_EXIST);
				resp.setCode(ShoppingContants.RESP_CODE_ORDER_NOT_EXIST);
				return resp;
			}
			//验证金额
			if(!queryOrder.getAmount().equals(orderVo.getAmount())){
				log.warn("订单金额不对，此次操作失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_ORDER_AMOUNT_ERRO);
				resp.setCode(ShoppingContants.RESP_CODE_ORDER_AMOUNT_ERRO);
				return resp;
			}
			
			if(ShoppingContants.ORDER_PAY_STATUS_HAS.equals(queryOrder.getPayStatus())){
				//如果订单状态已经是付款成功，则不处理
				//因为支付宝同一个订单号只能支付一次，所以不用担心用户多支付，之所以可能出现已处理，是因为支付宝可能会多次通知，导致这边已经处理过,所以已经处理的，不用再处理)
				resp.setData(orderVo);
				resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
				resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			}else{//如果不是已付款，那就要判断订单状态是否正常， 并且支付状态是否未支付，满足才能支付成功
				if(ShoppingContants.ORDER_STATUS_NORMAL.equals(queryOrder.getStatus())&&ShoppingContants.ORDER_PAY_STATUS_NOT.equals(queryOrder.getPayStatus())){
					
					TOrder updateParam=new TOrder();
					updateParam.setOrderVersion(queryOrder.getOrderVersion()+1);
					updateParam.setOid(queryOrder.getOid());
					updateParam.setPayStatus(ShoppingContants.ORDER_PAY_STATUS_HAS);
					updateParam.setPayNo(orderVo.getPayNo());
					int row=orderMapper.updateOrder(updateParam);
					if(row==0){
						log.warn("订单已被操作，此次操作失败!");
						resp.setData(null);
						resp.setMsg(ShoppingContants.RESP_MSG_ORDER_HAS_OPT);
						resp.setCode(ShoppingContants.RESP_CODE_ORDER_HAS_OPT);
						return resp;
					}
					resp.setData(orderVo);
					resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
					resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
				}else{//订单支付更新失败，应该要退款操作
					log.warn("订单已被操作，此次操作失败!");
					resp.setData(null);
					resp.setMsg(ShoppingContants.RESP_MSG_ORDER_HAS_OPT);
					resp.setCode(ShoppingContants.RESP_CODE_ORDER_HAS_OPT);
					return resp;
				}
			}
			
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("orderPay exception,",e);
		}
		return resp;
	}
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ServiceResponse<OrderVo> orderRefund(OrderVo orderVo) {
		ServiceResponse<OrderVo> resp=new ServiceResponse<OrderVo>();
		TOrder queryOrder=orderMapper.queryOrder(orderVo.getOid());
		if(ShoppingContants.ORDER_PAY_STATUS_REFUND.equals(queryOrder.getPayStatus())){
			log.warn("订单已被操作，此次操作失败!");
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_ORDER_HAS_OPT);
			resp.setCode(ShoppingContants.RESP_CODE_ORDER_HAS_OPT);
			return resp;
		}else{
			TOrder updateParam=new TOrder();
			updateParam.setOrderVersion(queryOrder.getOrderVersion()+1);
			updateParam.setOid(queryOrder.getOid());
			updateParam.setPayStatus(ShoppingContants.ORDER_PAY_STATUS_REFUND);
			int row=orderMapper.updateOrder(updateParam);
			if(row==0){
				log.warn("订单已被操作，此次操作失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_ORDER_HAS_OPT);
				resp.setCode(ShoppingContants.RESP_CODE_ORDER_HAS_OPT);
				return resp;
			}
			//订单关闭完后，归还库存
			TSkuStock skuStock=new TSkuStock();
			skuStock.setSid(queryOrder.getSid());
			skuStock.setStock(-queryOrder.getNumber());
			stockMapper.reduceSkuStock(skuStock);
			log.info("订单退款成功，订单号:{}",orderVo.getOid());
			resp.setData(orderVo);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		}
		return resp;
	}
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param oid
	    * @return
	    * @see com.cza.service.order.OrderService#queryOrder(java.lang.Long)
	    */
	    
	@Override
	public ServiceResponse<OrderVo> queryOrder(Long oid) {
		ServiceResponse<OrderVo> resp=new ServiceResponse<OrderVo>();
		try {
			TOrder order=orderMapper.queryOrder(oid);
			if(order!=null){
				OrderVo vo=new OrderVo();
				BeanUtils.copyProperties(order, vo);
				resp.setData(vo);
				resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
				resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			}else{
				resp.setMsg(ShoppingContants.RESP_MSG_ORDER_NOT_EXIST);
				resp.setCode(ShoppingContants.RESP_CODE_ORDER_NOT_EXIST);
			}
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			log.error("保存商品异常!",e);
		}
		return resp;
	}
}
