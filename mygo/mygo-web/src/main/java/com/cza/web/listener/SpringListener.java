
    /**  
    * @Title: MyContextLoaderListener.java
    * @Package com.juanpi.web.listener
    * @Description: TODO(用一句话描述该文件做什么)
    * @author Administrator
    * @date 2016年8月23日下午4:45:56
    * @version V1.0  
    */
    
package com.cza.web.listener;


import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.cza.common.ParamUtil;


/**
    * @ClassName: MyContextLoaderListener
    * @Description: 重写spring ContextLoaderListener
    * @author Administrator
    * @date 2016年8月23日下午4:45:56
    *
    */
public class SpringListener extends ContextLoaderListener{
	static final Logger log = LoggerFactory.getLogger(SpringListener.class);
	  public SpringListener(){
	  }

	  public SpringListener(WebApplicationContext context){
	    super(context);
	  }
	   
	  @Override 
	  public void contextInitialized(ServletContextEvent event){
			try {
				super.contextInitialized(event);
				log.info("spring start");
				//初始化常量缓存
				log.info("init param start!");
				ParamUtil.init("mygo-web");
			} catch (Exception e) {
				log.error("spring start erro",e);
			}
	  }
	
}
