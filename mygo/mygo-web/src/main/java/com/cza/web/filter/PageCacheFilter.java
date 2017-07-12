
    /**  
    * @Title: LoginFilter.java
    * @Package com.cza.web.auth
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月9日上午10:41:20
    * @version V1.0  
    */
    
package com.cza.web.filter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cza.common.ShoppingContants;
import com.cza.service.user.vo.UserVo;
import com.cza.web.filter.cache.PageCache;
import com.cza.web.filter.cache.WrapperResponse;


/**
    * @ClassName: LoginFilter
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月9日上午10:41:20
    *
    */

public class PageCacheFilter implements  Filter{

	private static final Logger log = LoggerFactory.getLogger(PageCacheFilter.class); 
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		String uri=req.getRequestURI();
		//目前仅对首页缓存
		if(uri.indexOf("/listCategory.do")>=0||uri.indexOf("/listNewGoods.do")>=0||uri.indexOf("/listHotGoods.do")>=0){
			long startTime=System.currentTimeMillis();
			byte[]content=PageCache.getContent(uri);
			if(content==null||content.length<=0){
				WrapperResponse wrapperResponse = new WrapperResponse((HttpServletResponse) response);
				chain.doFilter(request, wrapperResponse);
				content=wrapperResponse.getContent();
				PageCache.putContent(uri, content);
				log.info("init pagecache content,uri:{},content:{}",uri,content);
			}
			ServletOutputStream out = response.getOutputStream();
	        out.write(content);
	        out.flush();
	        log.info("PageCacheFilter request uri is:{},cost time:{}",uri,System.currentTimeMillis()-startTime);
		}else{
			chain.doFilter(request, response);
		}
		
	}

	    
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
