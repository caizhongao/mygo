
    /**  
    * @Title: UserInfoValidate.java
    * @Package com.cza.web.user.validate
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日上午9:26:44
    * @version V1.0  
    */
    
package com.cza.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.cza.common.ShoppingContants;
import com.cza.service.user.vo.UserVo;

/**
    * @ClassName: UserInfoValidate
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日上午9:26:44
    *
    */

public class UserInfoValidate {
	  /** @Title: checkParam
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param user
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	    **/
		public static  Map<String,String> checkRegisterParam(UserVo user) {
			Map<String,String> erroList=new HashMap<String,String>();
			if(StringUtils.isEmpty(user.getUserName())){
				erroList.put("userName",".登录名不能为空");
			}
			if(StringUtils.isEmpty(user.getPassword())){
				erroList.put("password",".密码不能为空");
			}
			if(StringUtils.isEmpty(user.getRealName())){
				erroList.put("realName",".真实姓名不能为空");
			}
			if(user.getAge()==null){
				erroList.put("age",".年龄不能为空");
			}
			if(user.getSex()==null){
				erroList.put("sex",".性别不能为空");
			}
			return erroList;
		}
		
		
		
		  /**
		 * @param request  @Title: checkLoginParam
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param user
		    * @param @return    参数
		    * @return boolean    返回类型
		    * @throws
		    **/
			public static Map<String,String> checkLoginParam(UserVo user, HttpServletRequest request) {
				Map<String,String> erroList=new HashMap<String,String>();
				if(StringUtils.isEmpty(user.getUserName())){
					erroList.put("userName",".登录名不能为空");
				}
				if(StringUtils.isEmpty(user.getPassword())){
					erroList.put("password",".密码不能为空");
				}
				if(StringUtils.isEmpty(user.getPicCode())){
					erroList.put("picCode",".验证码不能为空");
				}else{
					String picCode=(String) request.getSession().getAttribute(ShoppingContants.CODE_SESSION_KEY);
					if(!picCode.equals(user.getPicCode().toUpperCase())){
						erroList.put("picCode",".验证码有误");
					}
				}
				return erroList;
			}
			
			
			/**
			 * @param request  @Title: checkLoginParam
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param user
			    * @param @return    参数
			    * @return boolean    返回类型
			    * @throws
			    **/
				public static List<String> checkManagerLoginParam(UserVo user, HttpServletRequest request) {
					List<String> erroList=new ArrayList<String>();
					if(user.getUserName()==null){
						erroList.add("登录名不能为空");
					}
					if(user.getPassword()==null){
						erroList.add("密码不能为空");
					}
					return erroList;
				}
}
