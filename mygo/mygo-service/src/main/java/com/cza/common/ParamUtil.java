
    /**  
    * @Title: ParamUtil.java
    * @Package com.cza.util
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月11日下午2:52:50
    * @version V1.0  
    */
    
package com.cza.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cza.dto.system.TParam;
import com.cza.service.system.ParamService;

/**
    * @ClassName: ParamUtil
    * @Description: 获取系统常量的util工具类
    * @author mufeng
    * @date 2017年8月11日下午2:52:50
    *
    */
public class ParamUtil {
	private static final Logger log = LoggerFactory.getLogger(ParamUtil.class); 
	private static Map<String,String> params=null;
	private static Object lock=new Object();
	private static String app_type;	
	//缓存过期时间    now+cache过期
	private static Long expire_time;
	//默认的过期时间
	private static Long default_expire_time=5*1000*60l;
	private ParamUtil(){
	}
	public static void init(String type){
		if(params==null){
			synchronized(lock){
				if(params==null){
					ParamService paramService=SpringContextUtil.getBean(ParamService.class);
					TParam param=new TParam();
					param.setCfType(type);
					ServiceResponse<List<TParam>> resp=paramService.listParam(param);
					if(resp.isSuccess()){
						log.info("listParam success,result:{}",resp.getData());
						List<TParam> results=resp.getData();
						if(results!=null&&results.size()>0){
							params=new HashMap<>();
							app_type=type;
							for(TParam result:results){
								params.put(result.getCfKey(), result.getCfValue());
							}
							//初始化缓存过期时间
							String cache_expire_time=params.get("cache_expire_time");
							if(!StringUtils.isEmpty(cache_expire_time)){
								expire_time=System.currentTimeMillis()+Long.valueOf(cache_expire_time);
							}else{
								expire_time=System.currentTimeMillis()+default_expire_time;
							}
						}
						log.info("init param success,app_type:{},expire_time:{}",app_type,expire_time);
					}else{
						log.info("listParam has erro,respCode:{}",resp.getCode());
						log.info("init param failed!");
					}
				}
			}
		}else{
			log.info("param has be inited!");
		}
	}
	
	public static String getParam(String key){
		if(params!=null){
			Long now=System.currentTimeMillis();
			if(expire_time==null){
				log.warn("expire_time is null!");
				return null;
			}
			if(expire_time>now){//缓存未过期
				return params.get(key);
			}else{//缓存过期
				log.warn("param expire time,init again!");
				params=null;
				init(app_type);
				return params.get(key);
			}
		}else{
			log.info("params is null");
		}
		return null;
	}
	
	
}
