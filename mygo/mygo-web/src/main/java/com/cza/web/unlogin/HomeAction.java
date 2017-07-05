
    /**  
    * @Title: HomeAction.java
    * @Package com.cza.web.home.action
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    * @version V1.0  
    */
    
package com.cza.web.unlogin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.service.goods.CategoryService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: HomeAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("/unlogin/home")
public class HomeAction extends CommonAction{
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("index")
	public String index(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		return webPage("home/index");
	}
	
}
