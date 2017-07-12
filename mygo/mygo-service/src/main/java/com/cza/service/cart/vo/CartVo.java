
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
	private Long skuId;
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
	* @return skuId
	*/
	
	public Long getSkuId() {
		return skuId;
	}


	
	/**
	 * @param skuId the skuId to set
	 */
	
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
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
		return "CartVo [sku=" + sku + ", skuId=" + skuId + ", number=" + number + ", amount=" + amount + ", uid=" + uid + "]";
	}















	
	
	
}
