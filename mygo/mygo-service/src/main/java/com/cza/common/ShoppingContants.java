
    /**  
    * @Title: UserContants.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午5:03:24
    * @version V1.0  
    */
    
package com.cza.common;


    /**
    * @ClassName: UserContants
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午5:03:24
    *
    */

public interface ShoppingContants {
	
	//用户常量
	public static final String USER_SESSION_KEY="user_session";
	
	public static final String ADMIN_SESSION_KEY="admin_session";
	
	public static final String CODE_SESSION_KEY="pic_code";
	
	public static final String USER_TYPE_CONSUMER="c";
	
	public static final String USER_TYPE_ADMIN="a";
	
	
	//商品常量
	public static final String GOODS_STATUS_WAIT="W";
	
	public static final String GOODS_STATUS_ON="O";
	
	public static final String GOODS_STATUS_OFF="F";
	
	
	public static final Integer SKU_STATUS_NORMAL=0;
	
	public static final Integer SKU_STATUS_DELETE=1;
	
	
	//类目常量
	public static final Integer CATEGORY_STATUS_NORMAL=0;
	public static final Integer CATEGORY_STATUS_DELETE=1;
	
	//类目属性常量
	public static final Integer CATEGORY_ATTR_STATUS_NORMAL=0;
	public static final Integer CATEGORY_ATTR_STATUS_DELETE=1;
	
	//service常量
	public static final String RESP_MSG_SUC="success";
	
	public static final String RESP_MSG_FAIL="failed";
	
	public static final Integer RESP_CODE_SUCESS=0;
	
	public static final Integer RESP_CODE_ERRO=1;
	
	public static final String FILE_UPLOAD_PREFIX="upload.file.path.prefix";
	
	public static final String FILE_REQUEST_PREFIX="request.file.path.prefix";
	
	//地址常量
	public static final Integer ADDR_IS_DEFAULT=1;
	public static final Integer ADDR_NOT_DEFAULT=0;
	
	
	//订单常量
	public static final Integer ORDER_NOT_PAY=0;
	public static final Integer ORDER_HAS_PAY=1;
	
	public static final Integer ORDER_STATUS_NORMAL=0;
	public static final Integer ORDER_STATUS_DELETE=1;
	
}
