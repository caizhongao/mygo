
    /**  
    * @Title: TaskServiceImpl.java
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
import com.cza.dto.system.TTask;
import com.cza.mapper.system.TaskMapper;
import com.cza.service.system.TaskService;

/**
    * @ClassName: TaskServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:24:31
    *
    */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class); 
	@Autowired
	private TaskMapper taskMapper;
	/* (非 Javadoc)
	* 
	* 
	* @param param
	* @see com.cza.service.system.TaskService#updateTask(com.cza.dto.system.TTask)
	*/

	@Override
	public ServiceResponse<TTask> updateTask(TTask param) {
		
		// TODO Auto-generated method stub
		ServiceResponse<TTask> resp=new ServiceResponse<TTask>();
		try {
			taskMapper.updateTask(param);
			resp.setData(param);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("更新task失败",e);
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
	* @see com.cza.service.system.TaskService#queryTask(com.cza.dto.system.TTask)
	*/

	@Override
	public ServiceResponse<TTask> queryTask(TTask param) {
		// TODO Auto-generated method stub
		ServiceResponse<TTask> resp=new ServiceResponse<TTask>();
		try {
			TTask task=taskMapper.queryTask(param);
			resp.setData(task);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("查询task失败",e);
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
	* @see com.cza.service.system.TaskService#listTask(com.cza.dto.system.TTask)
	*/

	@Override
	public ServiceResponse<List<TTask>> listTask(TTask param) {
		// TODO Auto-generated method stub
		ServiceResponse<List<TTask>> resp=new ServiceResponse<List<TTask>>();
		try {
			List<TTask> taskList=taskMapper.listTask(param);
			resp.setData(taskList);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		} catch (Exception e) {
			log.error("查询task失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		}
		return resp;
	}

}
