
    /**  
    * @Title: BaseTask.java
    * @Package com.cza.task
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:39:53
    * @version V1.0  
    */
    
package com.cza.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.common.log.LogUtil;
import com.cza.dto.system.TTask;
import com.cza.service.system.TaskService;

/**
    * @ClassName: BaseTask
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:39:53
    *
    */

public abstract class BaseTask {
	protected final Logger log = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private TaskService taskService;
	
	public void execute(){
		LogUtil.makeLogHeader("system");
		long startTime=System.currentTimeMillis();
		log.info("task start!");
		TTask queryParam=new TTask();
		queryParam.setTaskName(getClass().getSimpleName()+"Task");
		log.info("queryTask param:{}",queryParam);
		ServiceResponse<TTask> resp=taskService.queryTask(queryParam);
		if(resp.isSuccess()){
			log.info("queryTask success,result:{}",resp.getData());
			TTask task=resp.getData();
			if(task==null){
				log.info("queryTask is null,task stop!");
				return ;
			}
			if(ShoppingContants.TASK_STATUS_OFF.equals(task.getStatus())){
				log.info("task is off,task stop!");
				return;
			}
			task.setTimes(task.getTimes()+1);
			task.setLastExecuteTime(startTime/1000);//数据库里面的时间戳都是存的秒级别
			Long number=invoke();
			task.setNumber(number);
			task.setLastCostTime(System.currentTimeMillis()-startTime);//耗时，毫秒级别
			taskService.updateTask(task);
		}else{
			log.info("queryTask has erro,task stop,respCode:{}",resp.getCode());
			return;
		}
		log.info("task execute cost time:{}",System.currentTimeMillis()-startTime);
	}
	
	public abstract Long invoke();
	
}
