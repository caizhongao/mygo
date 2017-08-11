
    /**  
    * @Title: ParamAction.java
    * @Package com.cza.web.login
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:05:05
    * @version V1.0  
    */
    
package com.cza.web.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.system.TParam;
import com.cza.service.system.ParamService;
import com.cza.web.CommonAction;


/**
    * @ClassName: ParamAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:05:05
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("login/param")
public class ParamAction extends CommonAction{
	@Autowired
	private ParamService paramService;
	private static final Logger log = LoggerFactory.getLogger(ParamAction.class); 
	
	@RequestMapping("listParam")
	public String listParam(@ModelAttribute TParam param,HttpServletRequest request,HttpServletResponse response ){
		log.info("listParam 请求参数,param:{}",param);
		ServiceResponse<List<TParam>> resp=paramService.listParam(param);
		if(resp.isSuccess()){
			log.info("listParam success,result:{}",resp.getData());
			request.setAttribute("paramList", resp.getData());
			return webPage("listParam");
		}else{
			log.info("listParam has erro,respCode:{}",resp.getCode());
			return erro(request, resp);
		}
	}
	
	
	@RequestMapping("saveParam")
	public void saveParam(@ModelAttribute TParam param,HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			log.info("saveParam 请求参数,param:{}",param);
			response.setCharacterEncoding("utf-8");
			ServiceResponse<TParam> resp=paramService.saveOrUpdateParam(param);
			if(resp.isSuccess()){
				log.info("saveParam success,result:{}",resp.getData());
				response.getWriter().println(new RespMsg("success", null));
			}else{
				log.info("saveParam has erro,respCode:{}",resp.getCode());
				response.getWriter().println(new RespMsg("fail", resp.getCode()));
			}
		} catch (Exception e) {
			log.info("system erro:",e);
			response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_CODE_SYSTEM_ERRO));
		} 
	}
	
	@RequestMapping("deleteParam")
	public void deleteParam(@ModelAttribute TParam param,HttpServletRequest request,HttpServletResponse response)throws IOException{
		try {
			log.info("saveParam 请求参数,param:{}",param);
			response.setCharacterEncoding("utf-8");
			ServiceResponse<TParam> resp=paramService.deleteParam(param);
			if(resp.isSuccess()){
				log.info("deleteParam success,result:{}",resp.getData());
				response.getWriter().println(new RespMsg("success", null));
			}else{
				log.info("deleteParam has erro,respCode:{}",resp.getCode());
				response.getWriter().println(new RespMsg("fail", resp.getCode()));
			}
		} catch (Exception e) {
			log.info("system erro:",e);
			response.getWriter().println(new RespMsg("fail", ShoppingContants.RESP_CODE_SYSTEM_ERRO));
		} 
	}
	
}
