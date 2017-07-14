
    /**  
    * @Title: SkuVo.java
    * @Package com.cza.service.goods.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月1日上午11:28:47
    * @version V1.0  
    */
    
package com.cza.service.goods.vo;

import java.math.BigDecimal;
import java.util.List;

/**
    * @ClassName: SkuVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月1日上午11:28:47
    *
    */

public class SkuVo {
	private Long sid;
	private BigDecimal price;
	private String barcode;
	private List<SkuAttrVo>attrs;
	private String goodsName;
	private String skuPic;
	private Long number;//库存
	private Long gid;
	private Long stock;
	
	
	
	
	
	
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
	* @return attrs
	*/
	
	public List<SkuAttrVo> getAttrs() {
		return attrs;
	}
	
	/**
	 * @param attrs the attrs to set
	 */
	
	public void setAttrs(List<SkuAttrVo> attrs) {
		this.attrs = attrs;
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





	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "SkuVo [sid=" + sid + ", price=" + price + ", barcode=" + barcode + ", attrs=" + attrs + ", goodsName="
				+ goodsName + ", skuPic=" + skuPic + ", number=" + number + ", stock=" + stock + "]";
	}








	

	
	
}
