
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
import java.util.List;

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
		public static List<String> checkRegisterParam(UserVo user) {
			List<String> erroList=new ArrayList<String>();
			if(StringUtils.isEmpty(user.getUserName())){
				erroList.add("登录名不能为空");
			}
			if(StringUtils.isEmpty(user.getPassword())){
				erroList.add("密码不能为空");
			}
			if(StringUtils.isEmpty(user.getRealName())){
				erroList.add("真实姓名不能为空");
			}
			if(user.getAge()==null){
				erroList.add("年龄不能为空");
			}
			if(user.getSex()==null){
				erroList.add("性别不能为空");
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
			public static List<String> checkLoginParam(UserVo user, HttpServletRequest request) {
				List<String> erroList=new ArrayList<String>();
				if(user.getUserName()==null){
					erroList.add("登录名不能为空");
				}
				if(user.getPassword()==null){
					erroList.add("密码不能为空");
				}
				if(user.getPicCode()==null){
					erroList.add("验证码不能为空");
				}else{
					String picCode=(String) request.getSession().getAttribute(ShoppingContants.CODE_SESSION_KEY);
					if(!picCode.equals(user.getPicCode().toUpperCase())){
						erroList.add("验证码有误");
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
