
    /**  
    * @Title: ParamServiceImpl.java
    * @Package com.cza.service.system.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:24:31
    * @version V1.0  
    */
    
package com.cza.service.system.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.system.TParam;
import com.cza.mapper.system.ParamMapper;
import com.cza.service.system.ParamService;

/**
    * @ClassName: ParamServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:24:31
    *
    */
@Service("ParamService")
public class ParamServiceImpl implements ParamService {
	private static final Logger log = LoggerFactory.getLogger(ParamServiceImpl.class); 
	@Autowired
	private ParamMapper paramMapper;
	/* (非 Javadoc)
	* 
	* 
	* @param param
	* @see com.cza.service.system.ParamService#updateParam(com.cza.dto.system.TParam)
	*/

	@Override
	public ServiceResponse<TParam> updateParam(TParam param) {
		
		// TODO Auto-generated method stub
		ServiceResponse<TParam> resp=new ServiceResponse<TParam>();
		try {
			paramMapper.updateParam(param);
			resp.setData(param);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("更新Param失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}

	
	/* (非 Javadoc)
	* 
	* 
	* @param param
	* @return
	* @see com.cza.service.system.ParamService#queryParam(com.cza.dto.system.TParam)
	*/

	@Override
	public ServiceResponse<TParam> queryParam(TParam param) {
		// TODO Auto-generated method stub
		ServiceResponse<TParam> resp=new ServiceResponse<TParam>();
		try {
			TParam Param=paramMapper.queryParam(param);
			resp.setData(Param);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("查询Param失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}

	/* (非 Javadoc)
	* 
	* 
	* @param param
	* @return
	* @see com.cza.service.system.ParamService#listParam(com.cza.dto.system.TParam)
	*/

	@Override
	public ServiceResponse<List<TParam>> listParam(TParam param) {
		// TODO Auto-generated method stub
		ServiceResponse<List<TParam>> resp=new ServiceResponse<List<TParam>>();
		try {
			List<TParam> ParamList=paramMapper.listParam(param);
			resp.setData(ParamList);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("查询Param失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param param
	    * @return
	    * @see com.cza.service.system.ParamService#saveParam(com.cza.dto.system.TParam)
	    */
	    
	@Override
	public ServiceResponse<TParam> saveOrUpdateParam(TParam param) {
		ServiceResponse<TParam> resp=new ServiceResponse<TParam>();
		try {
			TParam queryParam=paramMapper.queryParam(param);
			if(queryParam!=null){
				paramMapper.updateParam(param);
			}else{
				paramMapper.saveParam(param);
			}
			resp.setData(param);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("更新Param失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param param
	    * @return
	    * @see com.cza.service.system.ParamService#deleteParam(com.cza.dto.system.TParam)
	    */
	    
	@Override
	public ServiceResponse<TParam> deleteParam(TParam param) {
		ServiceResponse<TParam> resp=new ServiceResponse<TParam>();
		try {
			paramMapper.deleteParam(param);
			resp.setData(param);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("删除Param失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}
	
}
