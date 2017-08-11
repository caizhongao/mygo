
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
import com.cza.common.ParamUtil;
import com.cza.common.PropertyUtil;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.user.vo.UserVo;

/**
    * @ClassName: CommonAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月28日下午3:37:36
    *
    */

public class CommonAction {
	
	
	public String getGoodsUploadPath(String fileName){
		String basePath=(String)ParamUtil.getParam(ShoppingContants.FILE_UPLOAD_PREFIX);
		return basePath+File.separator+"goods"+File.separator+fileName;
	}
	
	public String getGoodsRequestPath(String fileName){
		String basePath=(String) ParamUtil.getParam(ShoppingContants.FILE_REQUEST_PREFIX);
		return basePath+"goods"+"/"+fileName;
	}
	
	public String erro(HttpServletRequest request,ServiceResponse resp){
		request.setAttribute("erroCode", resp.getCode());
		request.setAttribute("message", resp.getMsg());
		return "/common/erro";
	}
	
	public String webPage(String url){
		return "/WEB-INF/jsp/"+url;
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
	
	public UserVo getUser(HttpServletRequest request){
		return (UserVo) request.getSession().getAttribute(ShoppingContants.USER_SESSION_KEY);
	}
}
