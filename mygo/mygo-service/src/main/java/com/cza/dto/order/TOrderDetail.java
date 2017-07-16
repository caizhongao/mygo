
    /**  
    * @Title: TOrder.java
    * @Package com.cza.dto.order
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月3日下午5:50:50
    * @version V1.0  
    */
    
package com.cza.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
    * @ClassName: TOrder
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月3日下午5:50:50
    *
    */

public class TOrderDetail implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -6854927376341586525L;
	private String odid; 
	private String oid;         
	private Long sid;  
	private Long gid;
	private String goodsName;
	private BigDecimal orderPrice;     
	private Long number;
	private BigDecimal amount;
	

	public String getOdid() {
		return odid;
	}
	public void setOdid(String odid) {
		this.odid = odid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	@Override
	public String toString() {
		return "TOrderDetail [odid=" + odid + ", oid=" + oid + ", sid=" + sid + ", orderPrice=" + orderPrice + ", amount=" + amount + ", number=" + number + ", goodsName=" + goodsName + ", gid=" + gid + "]";
	}
	
	
	

	
}
