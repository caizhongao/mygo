
    /**  
    * @Title: IndexAction.java
    * @Package com.cza.web.manager
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月1日下午1:52:27
    * @version V1.0  
    */
    
package com.cza.web.manager;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.user.UserService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;
import com.cza.web.UserInfoValidate;

/**
    * @ClassName: IndexAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月1日下午1:52:27
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("manager/home")
public class IndexAction extends CommonAction{
	@Autowired
	private UserService userService;
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return managerPage("index");
	}
	
	@RequestMapping("toLogin")
	public String toLogin(HttpServletRequest request,HttpServletResponse response){
		return managerPage("login");
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		//校验登录请求参数
		List<String> erroList=UserInfoValidate.checkManagerLoginParam(user, request);
		if(erroList.isEmpty()){
			UserVo listUserParam=new UserVo();
			listUserParam.setUserName(user.getUserName());
			ServiceResponse<List<UserVo>> resp=userService.listUser(listUserParam);
			if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
				List<UserVo>voList=resp.getData();
				if(voList!=null&&voList.size()>0){
					UserVo queryUser=voList.get(0);
					if(queryUser.getPassword().equals(user.getPassword())&&ShoppingContants.USER_TYPE_ADMIN.equals(queryUser.getType())){
						request.getSession().setAttribute(ShoppingContants.ADMIN_SESSION_KEY, queryUser);
						return managerAction("home/index.do");
					}else{
						erroList.add("登录密码错误");
					}
				}else{
					erroList.add("登录名不存在");
				}
			}
		}
		//登录失败
		request.setAttribute("erroList", erroList);
		request.setAttribute("user", user);
		return managerPage("login");
	}
}
