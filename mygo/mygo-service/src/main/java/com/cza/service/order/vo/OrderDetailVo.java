/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.service.order.vo;

import com.cza.dto.order.TOrderDetail;
import com.cza.service.goods.vo.SkuVo;

/**
 * 作用：
 * @author zhongao
 * @date 2017年7月14日
 */
public class OrderDetailVo extends TOrderDetail{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1902560342451493435L;
	
	private SkuVo sku;

	public SkuVo getSku() {
		return sku;
	}

	public void setSku(SkuVo sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "OrderDetailVo [getSku()=" + getSku() + ", getOdid()=" + getOdid() + ", getOid()=" + getOid() + ", getSid()=" + getSid() + ", getOrderPrice()=" + getOrderPrice() + ", getAmount()=" + getAmount() + ", getNumber()=" + getNumber() + ", getGoodsName()=" + getGoodsName() + ", getGid()=" + getGid() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
