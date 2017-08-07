
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

	
	/**
	 * 作用：判断Long数组中，是否存在空或者0
	 *@param ids
	 *@return
	 *@return boolean
	 */
	public static boolean hasZeroNull(Long ... ids){
		if(ids==null||ids.length<=0){
			return true;
		}
		for(Long id:ids){
			if(id==null||id==0){
				return true;
			}
		}
		return false;
	}
	
	public static boolean containStr(String target,String ...strs){
		for(String str:strs){
			if(target.indexOf(str)>=0){
				return true;
			}
		}
		return false;
	}
	
	
	public static String makeOrderNo(){
		return "mygo"+System.currentTimeMillis();
	}

	/**作用：
	 * @param <T>
	 *@param status
	 *@return
	 *@return boolean
	 */
	public static <T> boolean batchEquals(T target,T ... status) {
		// TODO Auto-generated method stub
		for(T statu:status){
			if(!statu.equals(target)){
				return false;
			}
		}
		return true;
	}
	
	
	
}
