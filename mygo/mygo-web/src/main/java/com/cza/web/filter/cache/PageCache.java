
    /**  
    * @Title: PageCache.java
    * @Package com.cza.web.filter
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月3日下午2:54:00
    * @version V1.0  
    */
    
package com.cza.web.filter.cache;

import java.util.HashMap;
import java.util.Map;

/**
    * @ClassName: PageCache
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年7月3日下午2:54:00
    *
    */

public class PageCache {
	private static Map<String,byte[]> cache=new HashMap<>();
	private static Map<String,Long>cacheTime=new HashMap<>();
	private static long expireTime=5*60*1000;
	public static byte[] getContent(String uri){
		if(isExpire(uri)){
			return null;
		}else{
			return cache.get(uri);
		}
	}
	
	public static boolean isExpire(String uri){
		Long time=cacheTime.get(uri+"_expire");
		if(time==null){
			return true;
		}
		long now=System.currentTimeMillis();
		if((now-time)>expireTime){
			return true;
		}else{
			return false;
		}
	}
	
	public static void putContent(String uri,byte[] content){
		cache.put(uri,content);
		Long now=System.currentTimeMillis();
		cacheTime.put(uri+"_expire", now);
	}
}
