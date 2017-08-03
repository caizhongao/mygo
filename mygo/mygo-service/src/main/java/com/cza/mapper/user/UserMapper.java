
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.user;

import java.util.List;

import com.cza.dto.user.TUser;
import com.cza.service.user.vo.UserVo;

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface UserMapper {
	
	public void saveUser(TUser user);
	
	public void updateUser(TUser user);
	
	public TUser queryUser(TUser queryParam);
	
	public List<TUser> listUser(UserVo param);

	
	    /**
	    * @Title: countUser
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param param
	    * @param @return    参数
	    * @return Long    返回类型
	    * @throws
	    */
	    
	public Long countUser(UserVo param);
}
