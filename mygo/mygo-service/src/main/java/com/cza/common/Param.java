/*****************************************************************************
 * Copyright (c) 2017 george
 *
 *****************************************************************************/
package com.cza.common;

/**
 * 作用：
 * @author zhongao
 * @date 2017年7月12日
 */
public class Param {
	String key;
	Object value;
	
	/**
	 * 
	 */
	public Param() {
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Param(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Param [key=" + key + ", value=" + value + "]";
	}
	
	
	
	
	
}
