
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
import com.cza.common.log.LogUtil;
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
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)throws IOException, ServletException {
		Long startTime=System.currentTimeMillis();
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		UserVo user=(UserVo) req.getSession().getAttribute(ShoppingContants.ADMIN_SESSION_KEY);
		LogUtil.makeLogHeader(user==null?"unlogin":user.getUid().toString());
		String uri=req.getRequestURI();
		log.info("LoginFilter request uri:{}",uri);
		if(uri.indexOf("/login/")>=0){
			if(user==null||!ShoppingContants.USER_TYPE_ADMIN.equals(user.getType())){
				resp.sendRedirect(req.getContextPath()+"/unlogin/user/toLoginRedirect.do");
				log.info("LoginFilter need admin auth,please login");
				return;
			}
		}
		arg2.doFilter(arg0, arg1);
		log.info("LoginFilter request uri:{},cost time:{}",uri,System.currentTimeMillis()-startTime);
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
