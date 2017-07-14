
    /**  
    * @Title: TOrder.java
    * @Package com.cza.dto.order
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月3日下午5:50:50
    * @version V1.0  
    */
    
package com.cza.dto.cart;

import java.io.Serializable;
import java.math.BigDecimal;

/**
    * @ClassName: TOrder
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月3日下午5:50:50
    *
    */

public class TCart implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -8269380921183291045L;
	private Long cartId;         
	private Long uid ;         
	private Long sid;      
	private Long number;
	
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "TCart [cartId=" + cartId + ", uid=" + uid + ", sid=" + sid + ", number=" + number + "]";
	}

	
}
