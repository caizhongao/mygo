
    /**  
    * @Title: TCategoryAttr.java
    * @Package com.cza.dto.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月1日上午10:59:49
    * @version V1.0  
    */
    
package com.cza.dto.goods;


    /**
    * @ClassName: TCategoryAttr
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月1日上午10:59:49
    *
    */

public class TCategoryAttr {
	private Long caid;
	private Long cid;
	private String attrName;
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
	* @return attrName
	*/
	
	public String getAttrName() {
		return attrName;
	}
	
	/**
	 * @param attrName the attrName to set
	 */
	
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TCategoryAttr [caid=" + caid + ", cid=" + cid + ", attrName=" + attrName + ", status=" + status + "]";
	}
	
	
}
