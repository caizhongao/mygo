
    /**  
    * @Title: MygoUtil.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月10日下午3:15:02
    * @version V1.0  
    */
    
package com.cza.common;


    /**
    * @ClassName: MygoUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年7月10日下午3:15:02
    *
    */

public class MygoUtil {
	public static String makeToken(Long uid){
		return uid+"_"+System.currentTimeMillis();
	}
}
