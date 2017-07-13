
    /**  
    * @Title: OrderVo.java
    * @Package com.cza.service.order.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月9日上午10:23:59
    * @version V1.0  
    */
    
package com.cza.service.cart.vo;


import java.math.BigDecimal;

import com.cza.dto.addr.TUserAddr;
import com.cza.service.goods.vo.SkuVo;

/**
    * @ClassName: OrderVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月9日上午10:23:59
    *
    */

public class CartVo {
	private Long sid;
	private Long number;
	private Long uid;
	
	private SkuVo sku;
	private BigDecimal amount;
	
	
	







	public Long getUid() {
		return uid;
	}



	public void setUid(Long uid) {
		this.uid = uid;
	}






	
	
	
	


	
	
	/**
	* @return sid
	*/
	
	public Long getSid() {
		return sid;
	}



	
	/**
	 * @param sid the sid to set
	 */
	
	public void setSid(Long sid) {
		this.sid = sid;
	}



	/**
	* @return sku
	*/
	
	public SkuVo getSku() {
		return sku;
	}


	
	/**
	 * @param sku the sku to set
	 */
	
	public void setSku(SkuVo sku) {
		this.sku = sku;
	}


	/**
	* @return number
	*/
	
	public Long getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
	/**
	* @return amount
	*/
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * @param amount the amount to set
	 */
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {
		return "CartVo [sku=" + sku + ", sid=" + sid + ", number=" + number + ", amount=" + amount + ", uid=" + uid + "]";
	}















	
	
	
}
