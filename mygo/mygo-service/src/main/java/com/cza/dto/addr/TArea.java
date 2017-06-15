
    /**  
    * @Title: TArea.java
    * @Package com.cza.dto.addr
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月8日下午5:23:07
    * @version V1.0  
    */
    
package com.cza.dto.addr;


    /**
    * @ClassName: TArea
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月8日下午5:23:07
    *
    */

public class TArea {
	private Long aid;
	private Long paid;
	private String aname;
	
	/**
	* @return aid
	*/
	
	public Long getAid() {
		return aid;
	}
	
	/**
	 * @param aid the aid to set
	 */
	
	public void setAid(Long aid) {
		this.aid = aid;
	}
	
	/**
	* @return paid
	*/
	
	public Long getPaid() {
		return paid;
	}
	
	/**
	 * @param paid the paid to set
	 */
	
	public void setPaid(Long paid) {
		this.paid = paid;
	}
	
	/**
	* @return aname
	*/
	
	public String getAname() {
		return aname;
	}
	
	/**
	 * @param aname the aname to set
	 */
	
	public void setAname(String aname) {
		this.aname = aname;
	}

	@Override
	public String toString() {
		return "TArea [aid=" + aid + ", paid=" + paid + ", aname=" + aname + "]";
	}
	
}
