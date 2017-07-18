
    /**  
    * @Title: CommonAction.java
    * @Package com.cza.web
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月28日下午3:37:36
    * @version V1.0  
    */
    
package com.cza.web;


import javax.servlet.http.HttpServletRequest;


import com.cza.common.Param;
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
	
	public String erroPage(Integer erroCode){
		return webAction("/unlogin/home/erro",new Param("erroCode", erroCode));
	}
	
	public String orderErro(Integer erroCode){
		return webAction("/unlogin/home/orderErro",new Param("erroCode", erroCode));
	}
	
	public String referPage(HttpServletRequest request){
		String referer=request.getHeader("Referer");
		return "redirect:"+referer;
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
