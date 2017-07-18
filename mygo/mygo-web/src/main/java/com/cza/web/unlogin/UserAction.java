
    /**  
    * @Title: UserAction.java
    * @Package com.cza.web.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日上午9:46:09
    * @version V1.0  
    */
    
package com.cza.web.unlogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.order.impl.OrderServiceImpl;
import com.cza.service.user.UserService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;
import com.cza.web.UserInfoValidate;

/**
    * @ClassName: UserAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日上午9:46:09
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("/unlogin/user")
public class UserAction extends CommonAction{
	@Autowired
	private UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);
	@RequestMapping("register")
	public String register(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		log.info("UserAction.register 参数:{}",user);
		Map<String,String> erroList=UserInfoValidate.checkRegisterParam(user);
		if(erroList.isEmpty()){
			ServiceResponse<UserVo> resp=userService.saveUser(user);
			if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){//成功，返回到主頁
				request.getSession().setAttribute(ShoppingContants.USER_SESSION_KEY, user);
				return webAction("/unlogin/home/index");
			}
		}
		//失败，返回到注册页
		request.setAttribute("erroList", erroList);
		request.setAttribute("user", user);
		return webPage("user/register");
	}
	
	@RequestMapping("existName")
	public void existName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userName=request.getParameter("userName");
		log.info("UserAction.existName 参数:{}",userName);
		if(!StringUtils.isEmpty(userName)){
			UserVo listUserParam=new UserVo();
			listUserParam.setUserName(userName);
			ServiceResponse<List<UserVo>> resp=userService.listUser(listUserParam);
			if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
				if(resp.getData()!=null&&resp.getData().size()>0){
					log.info("UserAction.existName 用户名已经存在");
					response.getWriter().println("1");
					return;
				}
			}
		}
		response.getWriter().println("0");
	}
	

	@RequestMapping("toRegister")
	public String toRegister(){
		return webPage("user/register");
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		log.info("login param is:{}",user);
		//校验登录请求参数
		try {
			Map<String,String> erroList=UserInfoValidate.checkLoginParam(user,request);
			if(erroList.isEmpty()){
				UserVo listUserParam=new UserVo();
				listUserParam.setUserName(user.getUserName());
				ServiceResponse<List<UserVo>> resp=userService.listUser(listUserParam);
				if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
					List<UserVo>voList=resp.getData();
					if(voList!=null&&voList.size()>0){
						UserVo queryUser=voList.get(0);
						if(queryUser.getPassword().equals(user.getPassword())){
							//登录成功
							if(user.getRememberMe()!=null&&user.getRememberMe()==1){
								Cookie nameCookie = new Cookie("username",user.getUserName());
								nameCookie.setMaxAge(60*60*24*7);
								Cookie passCookie = new Cookie("password",user.getPassword());
								passCookie.setMaxAge(60*60*24*7);
								response.addCookie(nameCookie);
								response.addCookie(passCookie);
							}else{
								Cookie nameCookie = new Cookie("username",null);
								nameCookie.setMaxAge(0);
								Cookie passCookie = new Cookie("password",null);
								passCookie.setMaxAge(0);
								response.addCookie(nameCookie);
								response.addCookie(passCookie);
							}
							request.getSession().setAttribute(ShoppingContants.USER_SESSION_KEY, queryUser);
							if(!StringUtils.isEmpty(user.getRef())&&!"null".equals(user.getRef())){
								return "redirect:"+user.getRef();
							}
							return webAction("/unlogin/home/index");
						}else{
							erroList.put("password","登录密码错误");
						}
					}else{
						erroList.put("userName","登录名不存在");
					}
				}
			}
			//登录失败
			request.setAttribute("erroList", erroList);
			request.setAttribute("ref", user.getRef());
			request.setAttribute("user", user);
			return webPage("user/login");
		} catch (Exception e) {
			log.info("login exception:",e);
			return erroPage(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().invalidate();
		if(request.getHeader("Referer")==null||request.getHeader("Referer").indexOf("/login/")>=0){
			return webAction("/unlogin/home/index");
		}
		return referPage(request);
	}
	
	@RequestMapping("toLogin")
	public String toLogin(HttpServletRequest request,HttpServletResponse response){
		UserVo userVo=new UserVo();
		Cookie[] cookies = request.getCookies();
		for(Cookie c :cookies ){
			if("username".equals(c.getName())){
				userVo.setUserName(c.getValue());
				userVo.setRememberMe(1);
			}
			if("password".equals(c.getName())){
				userVo.setPassword(c.getValue());
				userVo.setRememberMe(1);
			}
		}
		String referer=request.getParameter("ref");
		request.setAttribute("user", userVo);
		request.setAttribute("ref", referer);
		return webPage("user/login");
	}
}
