
    /**  
    * @Title: UserAction.java
    * @Package com.cza.web.login
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月2日下午2:45:22
    * @version V1.0  
    */
    
package com.cza.web.login;

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

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.goods.vo.CategoryVo;
import com.cza.service.order.OrderService;
import com.cza.service.order.vo.OrderVo;
import com.cza.service.user.UserService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: UserAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月2日下午2:45:22
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("login/userManager")
public class UserManagerAction extends CommonAction {
	@Autowired
	private UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserManagerAction.class); 
	
	
	@RequestMapping("listUser")
	public String listUser(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response ){
		log.info("listUser 请求参数,user:{}",user);
		request.setAttribute("user", user);
		user.setType(ShoppingContants.USER_TYPE_CONSUMER);
		ServiceResponse<Pager<UserVo>> resp=userService.listUser(user);
		if(resp.isSuccess()){
			log.info("listUser success,result:{}",resp.getData());
			request.setAttribute("pager", resp.getData());
			return webPage("listUser");
		}else{
			log.info("closeOrder has erro,respCode:{}",resp.getCode());
			return erro(request, resp);
		}
	}
	
/*	@RequestMapping("editUser")
	public String editUser(@ModelAttribute UserVo user,HttpServletRequest request,HttpServletResponse response){
		ServiceResponse<UserVo> resp=userService.queryUser(user);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			request.setAttribute("user", resp.getData());
			return webPage("editUser");
		}else{
			return erro(request, resp);
		}
	}*/
	
}
