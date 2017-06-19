
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
	//成功
	public static final Integer RESP_CODE_SUCESS=0;
	public static final String RESP_MSG_SUCESS="success";
	//系统异常
	public static final Integer RESP_CODE_SYSTEM_ERRO=999;
	public static final String RESP_MSG_SYSTEM_ERRO="system erro!";
	//入参不对
	public static final Integer RESP_CODE_PARAM_ERRO=888;
	public static final String RESP_MSG_PARAM_ERRO="param erro!";
	//商品不存在
	public static final Integer RESP_CODE_GOODS_NOT_EXIST=1000;
	public static final String RESP_MSG_GOODS_NOT_EXIST="goods not exist!";
	//sku不存在
	public static final Integer RESP_CODE_SKU_NOT_EXIST=1001;
	public static final String RESP_MSG_SKU_NOT_EXIST="sku not exist!";
	
	//order不存在
	public static final Integer RESP_CODE_ORDER_NOT_EXIST=2000;
	public static final String RESP_MSG_ORDER_NOT_EXIST="order not exist!";
	
	
	public static final String FILE_UPLOAD_PREFIX="upload.file.path.prefix";
	
	public static final String FILE_REQUEST_PREFIX="request.file.path.prefix";
	
	//地址常量
	public static final Integer ADDR_IS_DEFAULT=1;
	public static final Integer ADDR_NOT_DEFAULT=0;
	
	
	//订单常量
	public static final Integer ORDER_PAY_STATUS_NOT=0;
	public static final Integer ORDER_PAY_STATUS_HAS=1;
	
	public static final Integer ORDER_STATUS_NORMAL=0;
	public static final Integer ORDER_STATUS_DELETE=1;
	
	//alipay 常量
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String ALIPAY_APP_ID = "alipay.appid";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String ALIPAY_PRIVATE_KEY = "alipay.private.key";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "alipay.public.key";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String ALIPAY_NOTIFY_URL = "alipay.notify.url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String ALIPAY_RETURN_URL = "alipay.return.url";

	// 签名方式
	public static String ALIPAY_SIGN_TYPE = "alipay.sign.type";
	
	// 字符编码格式
	public static String ALIPAY_CHARSET = "alipay.charset";
	
	// 支付宝网关
	public static String ALIPAY_GATEWAY_URL = "alipay.gateway.url";
	
	
}
