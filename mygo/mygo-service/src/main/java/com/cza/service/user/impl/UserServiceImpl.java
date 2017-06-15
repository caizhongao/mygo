
    /**  
    * @Title: UserServiceImpl.java
    * @Package UserService
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:59:45
    * @version V1.0  
    */
    
package com.cza.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.user.TUser;
import com.cza.mapper.user.UserMapper;
import com.cza.service.user.UserService;
import com.cza.service.user.vo.UserVo;

/**
    * @ClassName: UserServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:59:45
    *
    */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class); 
	@Autowired
	private UserMapper userMapper;

	@Override
	public ServiceResponse<UserVo> saveUser(UserVo param) {
		ServiceResponse<UserVo> resp=new ServiceResponse<UserVo>();
		try {
			TUser user=new TUser();
			user.setAge(param.getAge());
			user.setPassword(param.getPassword());
			user.setRealName(param.getRealName());
			user.setSex(param.getSex());
			user.setUserName(param.getUserName());
			user.setCreateTime(System.currentTimeMillis()/1000);
			user.setUpdateTime(System.currentTimeMillis()/1000);
			userMapper.saveUser(user);
			param.setUid(user.getUid());
			resp.setData(param);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			log.info("saveUser success,TUser:{}",user);
		} catch (Exception e) {
			log.error("保存用戶失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
		}
		return resp;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param user
	    * @return
	    * @see com.cza.service.user.UserService#listUser(com.cza.web.user.vo.UserVo)
	    */
	    
	@Override
	public ServiceResponse<List<UserVo>> listUser(UserVo param) {
		ServiceResponse<List<UserVo>> resp=new ServiceResponse<List<UserVo>>();
		List<UserVo> voList=new ArrayList<UserVo>();
		try {
			TUser user0=new TUser();
			user0.setAge(param.getAge());
			user0.setPassword(param.getPassword());
			user0.setRealName(param.getRealName());
			user0.setSex(param.getSex());
			user0.setUserName(param.getUserName());
			List<TUser>users=userMapper.listUser(user0);
			if(users!=null&&users.size()>0){
				for(TUser user:users){
					UserVo vo=new UserVo();
					vo.setAge(user.getAge());
					vo.setPassword(user.getPassword());
					vo.setRealName(user.getRealName());
					vo.setSex(user.getSex());
					vo.setUid(user.getUid());
					vo.setUserName(user.getUserName());
					vo.setType(user.getType());
					voList.add(vo);
				}
			}
			resp.setData(voList);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			log.info("listUser success,param:{},result:{}",param,voList);
		} catch (Exception e) {
			log.error("查询user失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
		}
		return resp;
	}
	
	
}
