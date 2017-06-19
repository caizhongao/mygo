
    /**  
    * @Title: ServiceVo.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午5:23:22
    * @version V1.0  
    */
    
package com.cza.common;

import java.io.Serializable;

/**
    * @ClassName: ServiceVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午5:23:22
    *
    */

public class ServiceResponse<T> implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;
	private T data;
	private Integer code;
	private String msg;
	
	
	public boolean isSuccess(){
		if(ShoppingContants.RESP_CODE_SUCESS.equals(code)){
			return true;
		}else return false;
	}
	
	/**
	* @return data
	*/
	
	public T getData() {
		return data;
	}
	
	/**
	 * @param data the data to set
	 */
	
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	* @return code
	*/
	
	public Integer getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	* @return msg
	*/
	
	public String getMsg() {
		return msg;
	}
	
	/**
	 * @param msg the msg to set
	 */
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
