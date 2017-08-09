
    /**  
    * @Title: TaskAction.java
    * @Package com.cza.web.login
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:05:05
    * @version V1.0  
    */
    
package com.cza.web.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cza.common.Pager;
import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.system.TTask;
import com.cza.service.system.TaskService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;


/**
    * @ClassName: TaskAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:05:05
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("login/task")
public class TaskAction extends CommonAction{
	@Autowired
	private TaskService taskService;
	private static final Logger log = LoggerFactory.getLogger(TaskAction.class); 
	
	@RequestMapping("listTask")
	public String listTask(@ModelAttribute TTask task,HttpServletRequest request,HttpServletResponse response ){
		log.info("listTask 请求参数,task:{}",task);
		request.setAttribute("task", task);
		ServiceResponse<List<TTask>> resp=taskService.listTask(task);
		if(resp.isSuccess()){
			log.info("listTask success,result:{}",resp.getData());
			request.setAttribute("taskList", resp.getData());
			return webPage("listTask");
		}else{
			log.info("listTask has erro,respCode:{}",resp.getCode());
			return erro(request, resp);
		}
	}
	
	@RequestMapping("updateTaskStatus")
	public void updateTaskStatus(@ModelAttribute TTask task,HttpServletRequest request,HttpServletResponse response ) throws IOException{
		log.info("listTask 请求参数,task:{}",task);
		response.setCharacterEncoding("utf-8");
		ServiceResponse<TTask> resp=taskService.updateTask(task);
		if(resp.isSuccess()){
			log.info("updateTask success,result:{}",resp.getData());
			response.getWriter().println(new RespMsg("success", null));
		}else{
			log.info("updateTask has erro,respCode:{}",resp.getCode());
			response.getWriter().println(new RespMsg("success", resp.getCode()));
		}
	}
	
}
