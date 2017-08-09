
    /**  
    * @Title: TaskService.java
    * @Package com.cza.service.system
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:06:13
    * @version V1.0  
    */
    
package com.cza.service.system;

import java.util.List;

import com.cza.common.ServiceResponse;
import com.cza.dto.system.TTask;

/**
    * @ClassName: TaskService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:06:13
    *
    */

public interface TaskService {
	public ServiceResponse<TTask> updateTask(TTask param);
	
	public ServiceResponse<TTask> queryTask(TTask param);
	
	public ServiceResponse<List<TTask>> listTask(TTask param);
}
