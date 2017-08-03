
    /**  
    * @Title: UserService.java
    * @Package com.cza.service.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:44:14
    * @version V1.0  
    */
    
package com.cza.service.user;

import java.util.List;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.service.user.vo.UserVo;

/**
    * @ClassName: UserService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:44:14
    *
    */

public interface UserService {
	
	    /**
	    * @Title: saveUser
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param user    参数
	    * @return void    返回类型
	    * @throws
	    */
	ServiceResponse<UserVo> saveUser(UserVo user);

		
		    /**
		    * @Title: listUser
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param user    参数
		    * @return void    返回类型
		    * @throws
		    */
		    
	ServiceResponse<Pager<UserVo>> listUser(UserVo user);


			
			    /**
			    * @Title: queryUser
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param listUserParam
			    * @param @return    参数
			    * @return ServiceResponse<List<UserVo>>    返回类型
			    * @throws
			    */
			    
			ServiceResponse<UserVo> queryUser(UserVo listUserParam);
	
	
}
