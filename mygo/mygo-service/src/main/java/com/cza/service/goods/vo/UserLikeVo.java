
    /**  
    * @Title: UserLikeVo.java
    * @Package com.cza.service.goods.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月25日下午12:08:19
    * @version V1.0  
    */
    
package com.cza.service.goods.vo;

import java.math.BigDecimal;

/**
    * @ClassName: UserLikeVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年7月25日下午12:08:19
    *
    */

public class UserLikeVo {
	//喜好的类目
	private Long category;
	private Integer pageSize=15;
	private Integer start=0;
	
	
	
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
	* @return category
	*/
	
	public Long getCategory() {
		return category;
	}

	
	/**
	 * @param category the category to set
	 */
	
	public void setCategory(Long category) {
		this.category = category;
	}

	
	/**
	* @return topLever
	*/
	
	public BigDecimal getTopLever() {
		return topLever;
	}

	
	/**
	 * @param topLever the topLever to set
	 */
	
	public void setTopLever(BigDecimal topLever) {
		this.topLever = topLever;
	}

	
	/**
	* @return lowerLever
	*/
	
	public BigDecimal getLowerLever() {
		return lowerLever;
	}

	
	/**
	 * @param lowerLever the lowerLever to set
	 */
	
	public void setLowerLever(BigDecimal lowerLever) {
		this.lowerLever = lowerLever;
	}

	
	/**
	* @return sex
	*/
	
	public String getSex() {
		return sex;
	}

	
	/**
	 * @param sex the sex to set
	 */
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	//消费水平 高点
	private BigDecimal topLever; 
	
	//消费水平 低点
	private BigDecimal lowerLever; 
	
	private String sex;

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "UserLikeVo [category=" + category + ", topLever=" + topLever + ", lowerLever=" + lowerLever + ", sex="
				+ sex + "]";
	}
	
	
	
}
