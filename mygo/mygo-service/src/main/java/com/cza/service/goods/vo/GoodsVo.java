
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
	private Integer goodsIndex;
	
	
	private String searchKey;
	
	private Integer pageNum=1;
	private Integer pageSize=15;
	private Integer start=0;
	/**用于scroll分页*/
	//scroId对应的页码
	private Integer scrollPage=0;
	//scroId
	private String scrollId;
	//总记录数
	private Long count;
	
	
	
	
	
	





















	
	
	
	/**
	* @return count
	*/
	
	public Long getCount() {
		return count;
	}












	
	/**
	 * @param count the count to set
	 */
	
	public void setCount(Long count) {
		this.count = count;
	}












	/**
	* @return scrollPage
	*/
	
	public Integer getScrollPage() {
		return scrollPage;
	}











	
	/**
	 * @param scrollPage the scrollPage to set
	 */
	
	public void setScrollPage(Integer scrollPage) {
		this.scrollPage = scrollPage;
	}











	/**
	* @return scrollId
	*/
	
	public String getScrollId() {
		return scrollId;
	}









	
	/**
	 * @param scrollId the scrollId to set
	 */
	
	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}









	/**
	* @return searchKey
	*/
	
	public String getSearchKey() {
		return searchKey;
	}








	
	/**
	 * @param searchKey the searchKey to set
	 */
	
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}








	/**
	* @return pageNum
	*/
	
	public Integer getPageNum() {
		return pageNum;
	}







	
	/**
	 * @param pageNum the pageNum to set
	 */
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}







	
	/**
	* @return pageSize
	*/
	
	public Integer getPageSize() {
		return pageSize;
	}







	
	/**
	 * @param pageSize the pageSize to set
	 */
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}







	
	/**
	* @return start
	*/
	
	public Integer getStart() {
		return start;
	}







	
	/**
	 * @param start the start to set
	 */
	
	public void setStart(Integer start) {
		this.start = start;
	}







	/**
	* @return goodsIndex
	*/
	
	public Integer getGoodsIndex() {
		return goodsIndex;
	}






	
	/**
	 * @param goodsIndex the goodsIndex to set
	 */
	
	public void setGoodsIndex(Integer goodsIndex) {
		this.goodsIndex = goodsIndex;
	}






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
				+ goodsPic + ", cid=" + cid + ", categoryName=" + categoryName + ", price=" + price + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", sales=" + sales + ", stock=" + stock + ", status="
				+ status + ", skus=" + skus + ", goodsIndex=" + goodsIndex + ", searchKey=" + searchKey + ", pageNum="
				+ pageNum + ", pageSize=" + pageSize + ", start=" + start + ", scrollPage=" + scrollPage + ", scrollId="
				+ scrollId + ", count=" + count + "]";
	}









	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    




	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    



}
