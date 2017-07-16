
    /**  
    * @Title: ListOrderVo.java
    * @Package com.cza.service.order.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月15日上午11:03:12
    * @version V1.0  
    */
    
package com.cza.service.order.vo;


import java.util.List;

import com.cza.dto.order.TOrder;

/**
    * @ClassName: ListOrderVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月15日上午11:03:12
    *
    */

public class OrderVo extends TOrder{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615552396138482416L;
	private List<OrderDetailVo> detailVos;
	private String token;
	private Long addrId;
	//默认是立即购买，1代表购物袋
	private Integer type=0;
	
	//查询参数
	private String goodsName;
	
	private String orderName;
	private Integer pageNum=1;
	private Integer pageSize=15;
	private Integer start=0;


	public Integer getType() {
		return type;
	}





	public void setType(Integer type) {
		this.type = type;
	}





	public String getGoodsName() {
		return goodsName;
	}





	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}





	public Long getAddrId() {
		return addrId;
	}





	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}





	public String getToken() {
		return token;
	}





	public void setToken(String token) {
		this.token = token;
	}





	public List<OrderDetailVo> getDetailVos() {
		return detailVos;
	}





	public void setDetailVos(List<OrderDetailVo> detailVos) {
		this.detailVos = detailVos;
	}





	public String getOrderName() {
		return orderName;
	}





	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}





	public Integer getPageNum() {
		return pageNum;
	}





	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}





	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}





	public Integer getStart() {
		return start;
	}



	public void setStart(Integer start) {
		this.start = start;
	}


	@Override
	public String toString() {
		return "OrderVo [getGoodsName()=" + getGoodsName() + ", getAddrId()=" + getAddrId() + ", getToken()=" + getToken() + ", getDetailVos()=" + getDetailVos() + ", getOrderName()=" + getOrderName() + ", getPageNum()=" + getPageNum() + ", getPageSize()=" + getPageSize() + ", getStart()=" + getStart() + ", getDetails()=" + getDetails() + ", getOid()=" + getOid() + ", getUid()=" + getUid() + ", getUserName()=" + getUserName() + ", getProvince()=" + getProvince() + ", getCity()=" + getCity() + ", getArea()=" + getArea() + ", getAddr()=" + getAddr() + ", getReceiver()="
				+ getReceiver() + ", getMobilphone()=" + getMobilphone() + ", getAmount()=" + getAmount() + ", getStatus()=" + getStatus() + ", getDeleteDesc()=" + getDeleteDesc() + ", getPayNo()=" + getPayNo() + ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + ", getOrderVersion()=" + getOrderVersion() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
