
    /**  
    * @Title: AttrVo.java
    * @Package com.cza.service.goods.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月1日上午11:30:54
    * @version V1.0  
    */
    
package com.cza.service.goods.vo;


    /**
    * @ClassName: AttrVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月1日上午11:30:54
    *
    */

public class SkuAttrVo {
	private Long attrId;
	private String attrValue;
	private String attrName;

	
	
	
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


	/**
	* @return attrId
	*/
	
	public Long getAttrId(){
		return attrId;
	}

	
	/**
	 * @param attrId the attrId to set
	 */
	
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
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
		return "SkuAttrVo [attrId=" + attrId + ", attrValue=" + attrValue + ", attrName=" + attrName + "]";
	}
	
}
