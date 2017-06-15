
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

public class TSkuAttr {
	private Long sid;                
	private Long  caid;      
	private String attrValue;
	
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
	* @return caid
	*/
	
	public Long getCaid() {
		return caid;
	}
	
	/**
	 * @param caid the caid to set
	 */
	
	public void setCaid(Long caid) {
		this.caid = caid;
	}
	
	/**
	* @return attrValue
	*/
	
	public String getAttrValue() {
		return attrValue;
	}
	
	/**
	 * @param attrValue the attrValue to set
	 */
	
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TSkuAttr [sid=" + sid + ", caid=" + caid + ", attrValue=" + attrValue + "]";
	}  
	
	
}
