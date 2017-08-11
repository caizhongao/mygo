
    /**  
    * @Title: TParam.java
    * @Package com.cza.dto.system
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月11日下午2:35:54
    * @version V1.0  
    */
    
package com.cza.dto.system;


    /**
    * @ClassName: TParam
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月11日下午2:35:54
    *
    */

public class TParam {
	private String cfKey;
	private String cfValue;
	private String cfDesc;
	private String cfType;
	
	/**
	* @return cfKey
	*/
	
	public String getCfKey() {
		return cfKey;
	}
	
	/**
	 * @param cfKey the cfKey to set
	 */
	
	public void setCfKey(String cfKey) {
		this.cfKey = cfKey;
	}
	
	/**
	* @return cfValue
	*/
	
	public String getCfValue() {
		return cfValue;
	}
	
	/**
	 * @param cfValue the cfValue to set
	 */
	
	public void setCfValue(String cfValue) {
		this.cfValue = cfValue;
	}
	
	/**
	* @return cfDesc
	*/
	
	public String getCfDesc() {
		return cfDesc;
	}
	
	/**
	 * @param cfDesc the cfDesc to set
	 */
	
	public void setCfDesc(String cfDesc) {
		this.cfDesc = cfDesc;
	}
	
	/**
	* @return cfType
	*/
	
	public String getCfType() {
		return cfType;
	}
	
	/**
	 * @param cfType the cfType to set
	 */
	
	public void setCfType(String cfType) {
		this.cfType = cfType;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TParam [cfKey=" + cfKey + ", cfValue=" + cfValue + ", cfDesc=" + cfDesc + ", cfType=" + cfType + "]";
	}
	
	
}
