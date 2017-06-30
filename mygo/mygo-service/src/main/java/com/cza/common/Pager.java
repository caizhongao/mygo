
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
	
	private Long count;
	private List<T> result;
	
	//用于scroll分页
	private Integer scrollPage=0;
	private String scrollId;
	
	
	
	
	
	    





	
	
	/**
	* @return scrollPage
	*/
	
	public Integer getScrollPage() {
		return scrollPage;
	}





	
	/**
	 * @param scrollPage the scrollPage to set
	 */
	
	public void setScrollPage(Integer scrollPage) {
		this.scrollPage = scrollPage;
	}





	/**
	* @return scrollId
	*/
	
	public String getScrollId() {
		return scrollId;
	}




	
	/**
	 * @param scrollId the scrollId to set
	 */
	
	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}




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
		    
		public Pager(Integer pageSize, Integer pageNum, Long count, List<T> result) {
			super();
			this.pageSize = pageSize;
			this.pageNum = pageNum;
			this.count = count;
			this.result = result;
		}




	
	
			/**
			* @return count
			*/
			
			public Long getCount() {
				return count;
			}






			
			/**
			 * @param count the count to set
			 */
			
			public void setCount(Long count) {
				this.count = count;
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
