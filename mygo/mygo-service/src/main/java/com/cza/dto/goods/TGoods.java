
    /**  
    * @Title: TGoods.java
    * @Package com.cza.dto.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午5:44:27
    * @version V1.0  
    */
    
package com.cza.dto.goods;

import java.math.BigDecimal;

/**
    * @ClassName: TGoods
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日下午5:44:27
    *
    */

public class TGoods {
	private Long	gid;
	private String	goodsName;
	private String	goodsCode;//商品编码
	private String goodsPic;
	private Long	cid;//分类id
	private BigDecimal	price;
	private Long	createTime;
	private Long	updateTime;
	private String status;//上下架状态
	private Long sales;
	
	
	
	
	
	/**
	* @return sales
	*/
	
	public Long getSales() {
		return sales;
	}



	
	/**
	 * @param sales the sales to set
	 */
	
	public void setSales(Long sales) {
		this.sales = sales;
	}



	/**
	* @return status
	*/
	
	public String getStatus() {
		return status;
	}


	
	/**
	 * @param status the status to set
	 */
	
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	* @return goodsPic
	*/
	
	public String getGoodsPic() {
		return goodsPic;
	}

	
	/**
	 * @param goodsPic the goodsPic to set
	 */
	
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
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
	* @return goodsCode
	*/
	
	public String getGoodsCode() {
		return goodsCode;
	}
	
	/**
	 * @param goodsCode the goodsCode to set
	 */
	
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	/**
	* @return cid
	*/
	
	public Long getCid() {
		return cid;
	}
	
	/**
	 * @param cid the cid to set
	 */
	
	public void setCid(Long cid) {
		this.cid = cid;
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




	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TGoods [gid=" + gid + ", goodsName=" + goodsName + ", goodsCode=" + goodsCode + ", goodsPic=" + goodsPic
				+ ", cid=" + cid + ", price=" + price + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", status=" + status + ", sales=" + sales + "]";
	}

	
}
