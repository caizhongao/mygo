
    /**  
    * @Title: TraceLogUtil.java
    * @Package com.cza.common.traceLog
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月7日下午2:59:44
    * @version V1.0  
    */
    
package com.cza.common.traceLog;

/**
    * @ClassName: TraceLogUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月7日下午2:59:44
    *
    */

public class TraceIdUtil {
	public static ThreadLocal<String> traceIds=new ThreadLocal<String>();
	public static String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String getTraceId(){
		return traceIds.get();
	}
	
	public static void makeTraceId(){
		Long time=System.currentTimeMillis();
		String uid="";
		for(int i=0;i<7;i++){
			uid+=str.charAt((int)(Math.random()*25));
		}
		traceIds.set(time+uid+(int)(Math.random()*10001));
	}
	
	
	public static void main(String[] args) {
		TraceIdUtil.makeTraceId();
		System.out.println(TraceIdUtil.getTraceId());
	}
}
