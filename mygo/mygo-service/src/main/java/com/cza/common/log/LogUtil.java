
    /**  
    * @Title: TraceLogUtil.java
    * @Package com.cza.common.traceLog
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月7日下午2:59:44
    * @version V1.0  
    */
    
package com.cza.common.log;

/**
    * @ClassName: TraceLogUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月7日下午2:59:44
    *
    */

public class LogUtil {
	//logheader包含traceId和userid
	public static ThreadLocal<String> logHeader=new ThreadLocal<String>();
	public static String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String getLogHeader(){
		return logHeader.get()==null?"":logHeader.get();
	}
	
	public static void makeLogHeader(String uid){
		Long time=System.currentTimeMillis();
		String uuid="";
		for(int i=0;i<7;i++){
			uuid+=str.charAt((int)(Math.random()*25));
		}
		String threadLog="["+Thread.currentThread().getName()+"] ";
		String traceLog="["+time+uuid+(int)(Math.random()*10001)+"] ";
		String uidLog="["+uid+"] ";
		logHeader.set(threadLog+uidLog+traceLog);
	}
	
	
	public static void main(String[] args) {
		LogUtil.makeLogHeader(null);
		System.out.println(LogUtil.getLogHeader());
	}
}
