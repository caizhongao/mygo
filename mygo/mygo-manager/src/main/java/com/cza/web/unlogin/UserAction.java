
    /**  
    * @Title: IndexAction.java
    * @Package com.cza.web.manager
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月1日下午1:52:27
    * @version V1.0  
    */
    
package com.cza.web.unlogin;


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
@RequestMapping("unlogin/user")
public class UserAction extends CommonAction{
	@Autowired
	private UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserAction.class); 
	@RequestMapping("toLoginRedirect")
	public String toLoginRedirect(HttpServletRequest request,HttpServletResponse response){
		return webPage("loginRedirect");
	}
	
	@RequestMapping("toLogin")
	public String toLogin(HttpServletRequest request,HttpServletResponse response){
		return webPage("login");
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		log.info("login request param:{}",user);
		//校验登录请求参数
		List<String> erroList=UserInfoValidate.checkManagerLoginParam(user, request);
		if(erroList.isEmpty()){
			UserVo listUserParam=new UserVo();
			listUserParam.setUserName(user.getUserName());
			ServiceResponse<UserVo> resp=userService.queryUser(listUserParam);
			if(resp.isSuccess()){
				log.info("listUser success,result:{}",resp.getData());
				if(resp.getData()!=null){
					UserVo queryUser=resp.getData();
					if(queryUser.getPassword().equals(user.getPassword())&&ShoppingContants.USER_TYPE_ADMIN.equals(queryUser.getType())){
						log.warn("login valide success!");
						request.getSession().setAttribute(ShoppingContants.ADMIN_SESSION_KEY, queryUser);
						return webAction("/login/home/index");
					}else{
						log.warn("login password erro!");
						erroList.add("登录密码错误");
					}
				}else{
					log.warn("login username erro!");
					erroList.add("登录名不存在");
				}
			}else{
				log.info("listUser has erro,respCode:{}",resp.getCode());
				return erro(request, resp);
			}
		}
		log.warn("login erro,message:{}",erroList);
		//登录失败
		request.setAttribute("erroList", erroList);
		request.setAttribute("user", user);
		return webPage("login");
	}
}
