
    /**  
    * @Title: UserVo.java
    * @Package com.cza.web.user.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:50:13
    * @version V1.0  
    */
    
package com.cza.service.user.vo;

import java.io.Serializable;

import com.cza.dto.user.TUser;

/**
    * @ClassName: UserVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:50:13
    *
    */

public class UserVo extends TUser implements Serializable{
	 
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -408038276779482497L;
	 
	 private String picCode;
	 private String ref;
	 private Integer rememberMe;
	 private Integer pageNum=1;
	 private Integer pageSize=15;
	 private Integer start=0;
	
	/**
	* @return picCode
	*/
	
	public String getPicCode() {
		return picCode;
	}





	
	/**
	 * @param picCode the picCode to set
	 */
	
	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}





	
	/**
	* @return ref
	*/
	
	public String getRef() {
		return ref;
	}





	
	/**
	 * @param ref the ref to set
	 */
	
	public void setRef(String ref) {
		this.ref = ref;
	}





	
	/**
	* @return rememberMe
	*/
	
	public Integer getRememberMe() {
		return rememberMe;
	}





	
	/**
	 * @param rememberMe the rememberMe to set
	 */
	
	public void setRememberMe(Integer rememberMe) {
		this.rememberMe = rememberMe;
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






	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return super.toString()+"UserVo [picCode=" + picCode + ", ref=" + ref + ", rememberMe=" + rememberMe + ", pageNum=" + pageNum
				+ ", pageSize=" + pageSize + ", start=" + start + "]";
	}





	
	 
}
