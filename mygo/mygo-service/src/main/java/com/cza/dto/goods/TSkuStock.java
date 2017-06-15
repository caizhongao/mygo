
    /**  
    * @Title: TSku.java
    * @Package com.cza.dto.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月2日上午11:32:55
    * @version V1.0  
    */
    
package com.cza.dto.goods;


/**
    * @ClassName: TSku
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月2日上午11:32:55
    *
    */

public class TSkuStock {
	private Long sid;                
	private Long  number;      
	private Long stock;
	
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

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TSkuStock [sid=" + sid + ", number=" + number + ", stock=" + stock + "]";
	}
	
	
	
}
