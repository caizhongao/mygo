
    /**  
    * @Title: CommonAction.java
    * @Package com.cza.web
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月28日下午3:37:36
    * @version V1.0  
    */
    
package com.cza.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.Param;
import com.cza.common.PropertyUtil;
import com.cza.common.ShoppingContants;
import com.cza.service.order.vo.PreOrderVo;
import com.cza.service.user.vo.UserVo;

/**
    * @ClassName: CommonAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月28日下午3:37:36
    *
    */

public class CommonAction {
	
	public String erroPage(Integer erroCode){
		return webAction("/unlogin/home/erro",new Param("erroCode", erroCode));
	}
	
	public String orderExistPage(){
		return webAction("/unlogin/home/orderExist");
	}
	
	public String orderErroPage(){
		return webAction("/unlogin/home/orderErro");
	}
	
	public String getGoodsUploadPath(String fileName){
		String basePath=(String) PropertyUtil.getProperty(ShoppingContants.FILE_UPLOAD_PREFIX);
		return basePath+File.separator+"goods"+File.separator+fileName;
	}
	
	public String getGoodsRequestPath(String fileName){
		String basePath=(String) PropertyUtil.getProperty(ShoppingContants.FILE_REQUEST_PREFIX);
		return basePath+File.separator+"goods"+File.separator+fileName;
	}
	
	public String referPage(HttpServletRequest request){
		String referer=request.getHeader("Referer");
		return "redirect:"+referer;
	}
	
	public String webPage(String url){
		return "/WEB-INF/jsp/"+url;
	}
	
	
	public String managerPage(String url){
		return "/manager/page/"+url;
	}
	
	public String webAction(String url,Param ... params){
		StringBuilder sb=new StringBuilder("");
		sb.append("redirect:").append(url).append(".do");
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				Param param=params[i];
				if(i==0){
					sb.append("?");
				}else{
					sb.append("&");
				}
				sb.append(param.getKey()).append("=").append(param.getValue());
			}
		}
		return sb.toString();
	}
	
	public String managerAction(String url){
		return "redirect:/manager/"+url;
	}
	
	public UserVo getUser(HttpServletRequest request){
		return (UserVo) request.getSession().getAttribute(ShoppingContants.USER_SESSION_KEY);
	}
}
