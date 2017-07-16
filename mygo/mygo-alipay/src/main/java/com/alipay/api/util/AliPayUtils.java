
    /**  
    * @Title: AliPayUtils.java
    * @Package com.alipay.api
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月6日上午10:35:19
    * @version V1.0  
    */
    
package com.alipay.api.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.cza.common.ElasticSearchUitl;
import com.cza.common.PropertyUtil;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.order.vo.OrderVo;

/**
    * @ClassName: AliPayUtils
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年7月6日上午10:35:19
    *
    */

public class AliPayUtils {
	private static Logger log = LoggerFactory.getLogger(ElasticSearchUitl.class); 
	public static ServiceResponse<Boolean> refund(String oid,String payNo,BigDecimal acmount){
		ServiceResponse<Boolean> resp=new ServiceResponse<>();
		boolean result=false;
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_GATEWAY_URL), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_APP_ID), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PRIVATE_KEY), "json",(String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE));
		
		//设置请求参数
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
		
		//商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = oid;
		//支付宝交易号
		String trade_no = payNo;
		//请二选一设置
		//需要退款的金额，该金额不能大于订单金额，必填
		String refund_amount = new String(acmount.toString());
		//退款的原因说明
		String refund_reason = new String("mygo-test");
		//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		String out_request_no = new String(oid.toString());
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"trade_no\":\""+ trade_no +"\"," 
				+ "\"refund_amount\":\""+ refund_amount +"\"," 
				+ "\"refund_reason\":\""+ refund_reason +"\"," 
				+ "\"out_request_no\":\""+ out_request_no +"\"}");
		//请求
		try {
			AlipayTradeRefundResponse alipayResp= alipayClient.execute(alipayRequest);
			if(alipayResp.isSuccess()&&alipayResp.getFundChange().equals("Y")){
				result=true;
			}
			resp.setData(result);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			log.info("OrderAction.toPay erro",e);
			resp.setData(result);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}
	
	public static ServiceResponse<String> aliPayPage(String oid,BigDecimal acmount,String orderName){
		ServiceResponse<String> resp=new ServiceResponse<>();
		try {
			
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_GATEWAY_URL), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_APP_ID), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PRIVATE_KEY), "json",(String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE));
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_RETURN_URL));
			alipayRequest.setNotifyUrl((String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_NOTIFY_URL));
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = oid;
			//付款金额，必填
			String total_amount = acmount.toString();
			//订单名称，必填
			String subject = orderName;
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			//		+ "\"total_amount\":\""+ total_amount +"\"," 
			//		+ "\"subject\":\""+ subject +"\"," 
			//		+ "\"body\":\""+ body +"\"," 
			//		+ "\"timeout_express\":\"10m\"," 
			//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			//请求
		
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			resp.setData(result);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			log.info("OrderAction.toPay erro",e);
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}
	
	
	public static ServiceResponse<Boolean> validatePayResult(Map<String,String[]> requestParams){
		ServiceResponse<Boolean> resp=new ServiceResponse<>();
		boolean result=false;
		try {
			Map<String,String> params = new HashMap<String,String>();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			log.info("OrderAction.updatePayStatus params:{}",params);
			result = AlipaySignature.rsaCheckV1(params, (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), (String)PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE)); //调用SDK验证签名
			resp.setData(result);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			log.info("OrderAction.updatePayStatus public_key:{},charset:{},sign_type:{},signVerified:{}",PropertyUtil.getProperty(ShoppingContants.ALIPAY_PUBLIC_KEY),PropertyUtil.getProperty(ShoppingContants.ALIPAY_CHARSET), PropertyUtil.getProperty(ShoppingContants.ALIPAY_SIGN_TYPE),result);
		} catch (Exception e) {
			log.info("OrderAction.toPay erro",e);
			resp.setData(result);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}
}
