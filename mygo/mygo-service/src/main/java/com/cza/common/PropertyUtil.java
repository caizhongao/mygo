
    /**  
    * @Title: PropertyUtil.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月3日下午6:01:30
    * @version V1.0  
    */
    
package com.cza.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.service.goods.impl.GoodsServiceImpl;

/**
    * @ClassName: PropertyUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月3日下午6:01:30
    *
    */

public class PropertyUtil {
	private static Properties properties;
	private static Object lock=new Object();
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class); 
	private static Object getProperty(String key){
		if(properties==null){
			initProperties();
		}
		if(properties!=null){
			return properties.get(key);
		}else{
			return null;
		}
	}
	
	private static void initProperties(){
		if(properties==null){
			InputStream is=null;
			try {
				synchronized (lock) {
					if(properties==null){
						properties=new Properties();
						is=PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
						properties.load(is);
						is.close();
					}
				}
			} catch (IOException e) {
				log.error("初始化properties报错!",e);
				try {
					if(is!=null){
						is.close();
					}
				} catch (IOException e1) {
					log.error("关闭流报错!",e);
				}
			}
		}
	}
}
