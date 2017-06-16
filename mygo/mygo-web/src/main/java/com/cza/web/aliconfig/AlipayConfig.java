package com.cza.web.aliconfig;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016080500175262";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDVgug4F5Qf/i28jVvkJ6QPT3+/MMR82ibHCcj1Hhir6hJiEKV1afcWPQ3vFfLz6M7FuOQOekPZs+q8uDEkHzFmNmEaVFrb1m0A7/UYSzZThdwp6G/Y7FjPVqzuOM+oglYxMo22koZMrGqmJSPq39hbaqoEBNfjF1+I3+hjID15scSR3ORD16wosf41EjmvlkCzRYx4vE2yG4XnWmoaNsIdC019bnbbyHqCT1J9txP+X1WqiGS7/4ujif7Fvvmhst36K0gZ5XGCfGDQD/rBqCqJkEbNZ5mD8PwYc05rsmMBjouUizwQsUTyKDYNh4Hq/zyKgsEaAMF1gfrT6EVItlr1AgMBAAECggEBAIrvclj9zt+6sNxfSAqBWRq1arVo8JMmCdG/JkuK2BcWa/vwRRrQZ37LIAQSTlQ1jEfRdmWNXhWOJBPF8ARHYAfZHvrZJX6kW64ed2jlmGZlVszWeeG4eloBVup1gXtvxNKiyOCjHE1MbOHMjUDLhKiIEjYnSKx3iy0wNrI6Soai3FYdhtqJ7qC7OFOPnNaP1k566Ju4Phh8atSov0OLc9BOB0XylNobONNRpho71TRI6I3+UBdTcxrpN3k35+Xbh1sCQCpbEbh7rUNyKJvHkxSW8EWxS4lRc/hLCy7dnWqvb/hlMiIZHUIEZNmWQqA/zwH+8vDDXbUfTG/G9dZKyYECgYEA9czStOwQglp34yMv/fAXo/YVWJ9yC0X3PiU0ITQejOH1HlAQvhJ9WkBeT+yMIh2nrTP6ap5OD8QowrfAMtsZgEsEPZDBvXqCZWGI4M/D8Q4Dill+vHE/I9Piu7tCN8IsJBSmOtS01kIDY1YbiKtn2zSsyhAWBZjMP463UQ/hVaUCgYEA3l8TQWlHGi6ZO8Td5Zrx3/g803yBB/Y0nMDLoRqRU5IqcM0Xo5qaNvgmnpVVi3rAWGD/u0qujUdiX3H4q7pkglKdMgHWqVYvnRhmyAeSqfO9Zw025+9CdYiVZwlfWNsbUF2J9ig9symVslMttc8Frp4u39tz3IbsG8dQWfzfDxECgYEAmhw7l9fSWghi1nR/aJWaHODzbUc697p8U+h7kL3e0bHyP3kvphxtn6Jv/2huTA2PzdajESoEA3qXm84doUUh7gAavob+EdC35NyMN3fMgFyq95k5DulqDwqQQ+gakl8Z0d1og8ZNQ3DMMwIP7VaanSOfmmkPhZ7RoJ/Ya+iq/UkCgYBgbJWq7X25aq9jbXBS2ac8WSX3BqvkyevydN9Xkm+l72Gb3DL97FImt9/3MAYSHz48lIMPsciS1ntfq8oKnR5sLGqhfo6YixjGZAhdgHsmWtysTNycIfGNiZZPQY8otonXhVgHd1eZ0tRoqQzPI1/xYgIta7mHzLGhMgIzoyUOIQKBgQDUU6dMqdPzlyvFOoLDvTYgzcx1XdfayUsPHN05jkYoC+47KNvVTic3SmQECA1w2cGSO35wJlksIEjSK2NwyfUIhV7oCqzRDrPTZnwoDF3pN0gvRslZ6KmAuen5j+dULAhGu36dBSbeu0VcpV8Zs789/VluLumsYU30ZtqKVxEHLg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnxqScELAuPm2TCBezITs2xPg5riOz716g8MBZllGGBdSWy4B/YQRLr+8FBR8Qj03LNXRLbj5lghbrFyXO2wGCACXrPxT2sM50+gO5e3Wzd/fn5zUQhTA5oNnaWmvMald5gww14+ezrEceZ9iMwklgtiL3AM58EiJCxoeMDEgHqx54S9llln+eX81r+68TXK7Joe/zX734qaEqqu120VzFUx3skWmSDnrYvtjvgz0x/NYzna/TwPlgqPU3zuEmruzhraIxKoAnqyTDdtQ5kfxjyxyUU7+UmDMOgBE4vnilmbBnqrY2FuNxBIZHH7Zf37BjXkrsxeXAifJ2Lj2+4tY7QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/mygo-web/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/mygo-web/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "/data/shopping/logs";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".log");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

