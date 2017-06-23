
    /**  
    * @Title: Pager.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月23日下午3:56:25
    * @version V1.0  
    */
    
package com.cza.common;

import java.util.List;

/**
    * @ClassName: Pager
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月23日下午3:56:25
    *
    */

public class Pager<T> {
	private Integer pageSize;
	private Integer pageNum;
	
	private Long total;
	private List<T> result;
	
	
	
	
	
	    
	    /**
	     * 创建一个新的实例 Pager.
	     *
	     */
	    
	public Pager() {
		super();
		// TODO Auto-generated constructor stub
	}



	
		    /**
		     * 创建一个新的实例 Pager.
		     *
		     * @param pageSize
		     * @param pageNum
		     * @param total
		     * @param result
		     */
		    
		public Pager(Integer pageSize, Integer pageNum, Long total, List<T> result) {
			super();
			this.pageSize = pageSize;
			this.pageNum = pageNum;
			this.total = total;
			this.result = result;
		}



	/**
	* @return total
	*/
	
	public Long getTotal() {
		return total;
	}
	
	/**
	 * @param total the total to set
	 */
	
	public void setTotal(Long total) {
		this.total = total;
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
	* @return result
	*/
	
	public List<T> getResult() {
		return result;
	}

	
	/**
	 * @param result the result to set
	 */
	
	public void setResult(List<T> result) {
		this.result = result;
	}
	

	
}
