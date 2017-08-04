
    /**  
    * @Title: IndexAction.java
    * @Package com.cza.web.manager
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月1日下午1:52:27
    * @version V1.0  
    */
    
package com.cza.web.login;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.web.CommonAction;

/**
    * @ClassName: IndexAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月1日下午1:52:27
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("login/home")
public class HomeAction extends CommonAction{
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return webPage("index");
	}
	
	@RequestMapping("home")
	public String home(HttpServletRequest request,HttpServletResponse response){
		return webPage("home");
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().invalidate();
		return webPage("login");
	}
}
