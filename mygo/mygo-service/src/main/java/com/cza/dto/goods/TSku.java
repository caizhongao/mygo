
    /**  
    * @Title: TSku.java
    * @Package com.cza.dto.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月2日上午11:32:55
    * @version V1.0  
    */
    
package com.cza.dto.goods;

import java.math.BigDecimal;

/**
    * @ClassName: TSku
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月2日上午11:32:55
    *
    */

public class TSku {
	private Long sid;                
	private Long  gid;      
	private String goodsName;  
	private String barcode;        
	private BigDecimal price;        
	private Long createTime;  
	private Long updateTime;  
	private String skuPic;
	private Integer status;
	
	
	
	
	/**
	* @return status
	*/
	
	public Integer getStatus() {
		return status;
	}

	
	/**
	 * @param status the status to set
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
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
	* @return gid
	*/
	
	public Long getGid() {
		return gid;
	}
	
	/**
	 * @param gid the gid to set
	 */
	
	public void setGid(Long gid) {
		this.gid = gid;
	}
	
	/**
	* @return goodsName
	*/
	
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * @param goodsName the goodsName to set
	 */
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**
	* @return barcode
	*/
	
	public String getBarcode() {
		return barcode;
	}
	
	/**
	 * @param barcode the barcode to set
	 */
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	/**
	* @return price
	*/
	
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	* @return createTime
	*/
	
	public Long getCreateTime() {
		return createTime;
	}
	
	/**
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	/**
	* @return updateTime
	*/
	
	public Long getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * @param updateTime the updateTime to set
	 */
	
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	* @return skuPic
	*/
	
	public String getSkuPic() {
		return skuPic;
	}
	
	/**
	 * @param skuPic the skuPic to set
	 */
	
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TSku [sid=" + sid + ", gid=" + gid + ", goodsName=" + goodsName + ", barcode=" + barcode + ", price="
				+ price + ", createTime=" + createTime + ", updateTime=" + updateTime + ", skuPic=" + skuPic
				+ ", status=" + status + "]";
	}   
	
}
