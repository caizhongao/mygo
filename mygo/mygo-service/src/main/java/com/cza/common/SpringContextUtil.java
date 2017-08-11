
    /**  
    * @Title: SpringContextUtils.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月11日上午10:22:40
    * @version V1.0  
    */
    
package com.cza.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
    * @ClassName: SpringContextUtils
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月11日上午10:22:40
    *
    */

public class SpringContextUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		SpringContextUtil.applicationContext =applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException{
		return (T)applicationContext.getBean(name);
	}
	
	public static <T> T getBean(Class<T> classname) throws BeansException{
		return (T)applicationContext.getBean(classname);
	}
}
