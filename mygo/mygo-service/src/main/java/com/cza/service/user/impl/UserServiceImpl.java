
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.user.TUser;
import com.cza.mapper.user.UserMapper;
import com.cza.service.order.vo.OrderVo;
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
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			log.info("saveUser success,TUser:{}",user);
		} catch (Exception e) {
			log.error("保存用戶失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
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
	public ServiceResponse<Pager<UserVo>> listUser(UserVo param) {
		ServiceResponse<Pager<UserVo>> resp=new ServiceResponse<Pager<UserVo>>();
		List<UserVo> voList=new ArrayList<UserVo>();
		try {
			Long count=userMapper.countUser(param);
			param.setStart((param.getPageNum()-1)*param.getPageSize());
			List<TUser>users=userMapper.listUser(param);
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
			resp.setData(new Pager<UserVo>(param.getPageSize(),param.getPageNum(),count,voList));
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			log.info("listUser success,param:{},result:{}",param,voList);
		} catch (Exception e) {
			log.error("查询user失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}
	
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param listUserParam
	    * @return
	    * @see com.cza.service.user.UserService#queryUser(com.cza.service.user.vo.UserVo)
	    */
	    
	@Override
	public ServiceResponse<UserVo> queryUser(UserVo listUserParam) {
		ServiceResponse<UserVo> resp=new ServiceResponse<UserVo>();
		try{
			if(StringUtils.isEmpty(listUserParam.getUserName())&&listUserParam.getUid()==null){
				resp.setData(null);
				resp.setCode(ShoppingContants.RESP_CODE_PARAM_ERRO);
				resp.setMsg(ShoppingContants.RESP_MSG_PARAM_ERRO);
			}else{
				TUser queryParam=new TUser();
				queryParam.setUserName(listUserParam.getUserName());
				queryParam.setUid(listUserParam.getUid());
				TUser user=userMapper.queryUser(queryParam);
				UserVo vo=new UserVo();
				BeanUtils.copyProperties(user, vo);
				resp.setData(vo);
				resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
				resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			}
		} catch (Exception e) {
			log.error("查询user失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}
	
}
