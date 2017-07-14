
    /**  
    * @Title: RespMsg.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月13日上午10:28:04
    * @version V1.0  
    */
    
package com.cza.common;

import com.alibaba.fastjson.JSON;

/**
    * @ClassName: RespMsg
    * @Description: 页面ajax请求，返回消息类
    * @author mufeng
    * @date 2017年7月13日上午10:28:04
    *
    */

public class RespMsg {
	//success / fail / forbidden
	private String message;
	private Object data;
	
	
	
	/**
	 * 创建一个新的实例 RespMsg.
	 *
	 */

	public RespMsg() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	    /**
	     * 创建一个新的实例 RespMsg.
	     *
	     * @param message
	     * @param data
	     */
	    
	public RespMsg(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}



	/**
	* @return message
	*/
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	* @return data
	*/
	
	public Object getData() {
		return data;
	}
	
	/**
	 * @param data the data to set
	 */
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public String toJson(){
		return JSON.toJSONString(this);
	}
	
}
