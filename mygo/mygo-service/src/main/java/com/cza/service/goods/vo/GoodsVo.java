
    /**  
    * @Title: GoodsVo.java
    * @Package com.cza.service.goods.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午6:15:23
    * @version V1.0  
    */
    
package com.cza.service.goods.vo;

import java.math.BigDecimal;
import java.util.List;

/**
    * @ClassName: GoodsVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日下午6:15:23
    *
    */

public class GoodsVo {
	private Long	gid;
	private String	goodsName;
	private String	goodsCode;//商品编码
	private String goodsPic;
	private Long	cid;//分类id
	private String categoryName;
	private BigDecimal	price;
	private Long	createTime;
	private Long	updateTime;
	private Long sales;
	private Long stock;
	private String status;//上下架状态
	private  List<SkuVo> skus;
	
	
	
	
	
	
	
	/**
	* @return stock
	*/
	
	public Long getStock() {
		return stock;
	}





	
	/**
	 * @param stock the stock to set
	 */
	
	public void setStock(Long stock) {
		this.stock = stock;
	}





	public Long getSales() {
		return sales;
	}





	public void setSales(Long sales) {
		this.sales = sales;
	}





	/**
	* @return categoryName
	*/
	
	public String getCategoryName() {
		return categoryName;
	}




	
	/**
	 * @param categoryName the categoryName to set
	 */
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	* @return skus
	*/
	
	public List<SkuVo> getSkus() {
		return skus;
	}


	
	/**
	 * @param skus the skus to set
	 */
	
	public void setSkus(List<SkuVo> skus) {
		this.skus = skus;
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
		return "GoodsVo [gid=" + gid + ", goodsName=" + goodsName + ", goodsCode=" + goodsCode + ", goodsPic="
				+ goodsPic + ", cid=" + cid + ", price=" + price + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", status=" + status + ", skus=" + skus + "]";
	}


}
