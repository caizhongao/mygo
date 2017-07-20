
    /**  
    * @Title: CategoryVo.java
    * @Package com.cza.service.goods.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月4日下午4:36:50
    * @version V1.0  
    */
    
package com.cza.service.goods.vo;

import java.util.List;

/**
    * @ClassName: CategoryVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月4日下午4:36:50
    *
    */

public class CategoryVo {
	private Long cid;
	private String cname;
	private Long pid;
	private Integer orderId;
	private Integer status;
	private List<CategoryAttrVo> attrList;
	
	
	/**
	* @return orderId
	*/
	
	public Integer getOrderId() {
		return orderId;
	}

	
	/**
	 * @param orderId the orderId to set
	 */
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	* @return cname
	*/
	
	public String getCname() {
		return cname;
	}
	
	/**
	 * @param cname the cname to set
	 */
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	/**
	* @return pid
	*/
	
	public Long getPid() {
		return pid;
	}
	
	/**
	 * @param pid the pid to set
	 */
	
	public void setPid(Long pid) {
		this.pid = pid;
	}
	
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
	* @return attrList
	*/
	
	public List<CategoryAttrVo> getAttrList() {
		return attrList;
	}

	
	/**
	 * @param attrList the attrList to set
	 */
	
	public void setAttrList(List<CategoryAttrVo> attrList) {
		this.attrList = attrList;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "CategoryVo [cid=" + cid + ", cname=" + cname + ", pid=" + pid + ", orderId=" + orderId + ", status="
				+ status + ", attrList=" + attrList + "]";
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */

	
	
	
	
}
