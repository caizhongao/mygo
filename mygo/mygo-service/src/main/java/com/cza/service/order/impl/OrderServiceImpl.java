
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

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TUserAddr;
import com.cza.dto.goods.TGoods;
import com.cza.dto.goods.TSku;
import com.cza.dto.goods.TSkuStock;
import com.cza.dto.order.TOrder;
import com.cza.dto.user.TUser;
import com.cza.mapper.addr.UserAddrMapper;
import com.cza.mapper.goods.GoodsMapper;
import com.cza.mapper.goods.SkuMapper;
import com.cza.mapper.goods.SkuStockMapper;
import com.cza.mapper.order.OrderMapper;
import com.cza.mapper.user.UserMapper;
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
	private UserMapper userMapper;
	
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
	public ServiceResponse<PreOrderVo> saveOrder(PreOrderVo orderVo) {
		ServiceResponse<PreOrderVo> resp=new ServiceResponse<PreOrderVo>();
		try {
			TSkuStock skuStock=new TSkuStock();
			skuStock.setSid(orderVo.getSkuId());
			skuStock.setStock(orderVo.getNumber().longValue());
			int row=stockMapper.reduceSkuStock(skuStock);
			if(row==0){//扣减库存失败
				log.warn("扣减库存失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
				resp.setCode(ShoppingContants.RESP_CODE_ERRO);
				return resp;
			}
			TOrder saveOrder=new TOrder();
			TUserAddr addr=addrMapper.queryAddr(orderVo.getAddrId());
			if(addr==null){
				log.warn("查询地址失败!");
				resp.setData(null);
				resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
				resp.setCode(ShoppingContants.RESP_CODE_ERRO);
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
			saveOrder.setGoodsName(sku.getGoodsName());
			saveOrder.setOid(makeOrderId());
			saveOrder.setUid(orderVo.getUid());
			saveOrder.setNumber(orderVo.getNumber());
			orderMapper.saveOrder(saveOrder);
			orderVo.setOrderId(saveOrder.getOid());
			//销量
			TGoods goods=goodsMapper.queryGoods(sku.getGid());
			goods.setSales(goods.getSales()+orderVo.getNumber());
			goodsMapper.updateGoods(goods);
			resp.setData(orderVo);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			log.error("保存商品异常!",e);
		}
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
	public ServiceResponse<List<OrderVo>> listOrder(OrderVo listOrderVo) {
		ServiceResponse<List<OrderVo>> resp=new ServiceResponse<List<OrderVo>>();
		try {
			TOrder listParam=new TOrder();
			BeanUtils.copyProperties(listOrderVo, listParam);
			List<TOrder> orderList=orderMapper.listOrder(listParam);
			List<OrderVo>voList=new ArrayList<>();
			if(orderList!=null&&orderList.size()>0){
				for(TOrder order:orderList){
					OrderVo vo=new OrderVo();
					BeanUtils.copyProperties(order, vo);
					TUser user=userMapper.queryUser(vo.getUid());
					if(user!=null){
						vo.setUserName(user.getUserName());
					}
					voList.add(vo);
				}
			}
			resp.setData(voList);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
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
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			log.error("保存商品异常!",e);
		}
		return resp;
	}
}
