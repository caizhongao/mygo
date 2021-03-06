
    /**  
    * @Title: LoginFilter.java
    * @Package com.cza.web.auth
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月9日上午10:41:20
    * @version V1.0  
    */
    
package com.cza.web.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.common.ShoppingContants;
import com.cza.service.user.vo.UserVo;


/**
    * @ClassName: LoginFilter
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月9日上午10:41:20
    *
    */

public class LoginFilter implements  Filter{

	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class); 
	    /* (非 Javadoc)
	    * 
	    * 
	    * @see javax.servlet.Filter#destroy()
	    */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @param arg1
	    * @param arg2
	    * @throws IOException
	    * @throws ServletException
	    * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	    */
	    
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		String uri=req.getRequestURI();
		if(uri.indexOf("/login/")>=0){
			//支付宝异步通知，不需要登录
			if(uri.indexOf("/notifyPayResult.do")>=0){
				log.info("请求uri:{},无需登录权限！",uri);
			}else{
				log.info("请求uri:{},需要登录权限！",uri);
				UserVo user=(UserVo) req.getSession().getAttribute(ShoppingContants.USER_SESSION_KEY);
				if(user==null){
					if(uri.indexOf("/toMakeOrderPage")>00){
						String referer=req.getHeader("Referer");
						resp.sendRedirect(req.getContextPath()+"/unlogin/user/toLogin.do?ref="+referer);
					}else{
						resp.sendRedirect(req.getContextPath()+"/unlogin/user/toLogin.do?ref="+req.getRequestURL());
					}
					return;
				}
			}
		}else{
			log.info("请求uri:{},无需登录权限！",uri);
		}
		arg2.doFilter(arg0, arg1);
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @throws ServletException
	    * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	    */
	    
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
