
    /**  
    * @Title: MyContextLoaderListener.java
    * @Package com.juanpi.web.listener
    * @Description: TODO(用一句话描述该文件做什么)
    * @author Administrator
    * @date 2016年8月23日下午4:45:56
    * @version V1.0  
    */
    
package com.cza.task.init;

import java.util.List;

import javax.servlet.ServletContextEvent;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.common.SpringContextUtil;
import com.cza.dto.system.TTask;
import com.cza.service.system.TaskService;


/**
    * @ClassName: MyContextLoaderListener
    * @Description: 重写spring ContextLoaderListener
    * @author Administrator
    * @date 2016年8月23日下午4:45:56
    *
    */
public class InitTaskExpression extends ContextLoaderListener{
	static final Logger log = LoggerFactory.getLogger(InitTaskExpression.class);
	  public InitTaskExpression(){
	  }

	  public InitTaskExpression(WebApplicationContext context){
	    super(context);
	  }
	   
	  @Override 
	  public void contextInitialized(ServletContextEvent event){
			try {
				super.contextInitialized(event);
				log.info("spring start");
				Scheduler scheduler=SpringContextUtil.getBean(Scheduler.class);
				TaskService taskService=SpringContextUtil.getBean("taskService");
				TTask param=new TTask();
				param.setStatus(ShoppingContants.TASK_STATUS_ON);
				ServiceResponse<List<TTask>> resp=taskService.listTask(param);
				if(resp.isSuccess()){
					List<TTask> taskList=resp.getData();
					if(taskList!=null&&taskList.size()>0){
						for(TTask task:taskList){
							log.info("init task:{},Expression:{}",task.getTaskName(),task.getExpression());
							String triggerName=task.getTaskName().substring(0, task.getTaskName().length()-3)+"Trigger";
							log.info("triggerName is:{}",triggerName);
							CronTriggerBean trigger  =(CronTriggerBean) scheduler.getTrigger(triggerName, Scheduler.DEFAULT_GROUP);
							trigger.setCronExpression(task.getExpression());
							scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, trigger);
						}
					}
				}
				
			} catch (Exception e) {
				log.error("spring start erro",e);
			}
	  }
	
}
