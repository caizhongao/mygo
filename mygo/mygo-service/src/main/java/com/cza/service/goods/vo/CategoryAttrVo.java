
    /**  
    * @Title: CategoryAttrVo.java
    * @Package com.cza.service.goods.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月4日下午5:08:09
    * @version V1.0  
    */
    
package com.cza.service.goods.vo;


    /**
    * @ClassName: CategoryAttrVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月4日下午5:08:09
    *
    */

public class CategoryAttrVo {
	private Long caid;
	
	private String attrName;

	
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
		return "CategoryAttrVo [caid=" + caid + ", attrName=" + attrName + "]";
	}

	
}
