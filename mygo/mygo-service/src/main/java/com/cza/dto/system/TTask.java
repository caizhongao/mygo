
    /**  
    * @Title: Ttask.java
    * @Package com.cza.dto.system
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:06:40
    * @version V1.0  
    */
    
package com.cza.dto.system;


    /**
    * @ClassName: Ttask
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:06:40
    *
    */

public class TTask {
	private String taskName;
	private Long times;
	private Long lastExecuteTime;
	private Long lastCostTime;
	private Integer status;
	private String desc;
	private Long number;
	
	
	
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
	* @return taskName
	*/
	
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * @param taskName the taskName to set
	 */
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	/**
	* @return times
	*/
	
	public Long getTimes() {
		return times;
	}
	
	/**
	 * @param times the times to set
	 */
	
	public void setTimes(Long times) {
		this.times = times;
	}
	
	/**
	* @return lastExecuteTime
	*/
	
	public Long getLastExecuteTime() {
		return lastExecuteTime;
	}
	
	/**
	 * @param lastExecuteTime the lastExecuteTime to set
	 */
	
	public void setLastExecuteTime(Long lastExecuteTime) {
		this.lastExecuteTime = lastExecuteTime;
	}
	
	/**
	* @return lastCostTime
	*/
	
	public Long getLastCostTime() {
		return lastCostTime;
	}
	
	/**
	 * @param lastCostTime the lastCostTime to set
	 */
	
	public void setLastCostTime(Long lastCostTime) {
		this.lastCostTime = lastCostTime;
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
	* @return desc
	*/
	
	public String getDesc() {
		return desc;
	}
	
	/**
	 * @param desc the desc to set
	 */
	
	public void setDesc(String desc) {
		this.desc = desc;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TTask [taskName=" + taskName + ", times=" + times + ", lastExecuteTime=" + lastExecuteTime
				+ ", lastCostTime=" + lastCostTime + ", status=" + status + ", desc=" + desc + ", number=" + number
				+ "]";
	}

	
	
	
	
	

}
