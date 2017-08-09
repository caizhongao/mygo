<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>

<style type="text/css">
	.edit_class{
		width: 60px;
		height: 25px;
		font-size: 14px;
		line-height: 24px;
	    padding:0;
	    border-radius: 2px;
	}
</style>
<script type="text/javascript">
	function closeTask(taskName){
		$.ajax({
			url:"${ctx}/login/task/updateTaskStatus.do",
			type:'post',
			data:{"taskName":taskName,"status":"1"},
			dataType:"json",
			success:function(data){
				if(data.message=='success'){
					location.reload();
				}else{
					alert("关闭定时任务失败,"+data.data);
				}
			}
		});
	}
	
	
	function openTask(taskName){
		$.ajax({
			url:"${ctx}/login/task/updateTaskStatus.do",
			type:'post',
			data:{"taskName":taskName,"status":"0"},
			dataType:"json",
			success:function(data){
				if(data.message=='success'){
					location.reload();
				}else{
					alert("关闭定时任务失败,"+data.data);
				}
			}
		});
	}
</script>
</head>
<body>
	<div>
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
		<span style="float: left;margin-left: 5px;">
			管理中心 - 定时任务列表
		</span>
		</div>
	<table class="listTable" width="100%" cellspacing="1">
		<tr>
			<th>
				序号
			</th>
			<th>
				任务名称
			</th>
			<th>
				已执行次数
			</th>
			<th>	
				上次执行时间
			</th>
			<th>
				上次执行耗时(ms)
			</th>
			<th>
				上次更新数目
			</th>
			<th>
				状态
			</th>
			<th>
				描述
			</th>
			<th>
				操作
			</th>
		</tr>
		<c:forEach items="${taskList}" var="task" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${task.taskName}
				</td>
				<td>
					${task.times}
				</td>
				<td>
					<jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>
					<jsp:setProperty property="time" name="dateObject" value="${task.lastExecuteTime*1000}"/>
					<fmt:formatDate value="${dateObject}" pattern="yyyy-MM-dd HH:mm:ss"/></font>
				</td>
				<td>
					${task.lastCostTime}
				</td>
				<td>
					${task.number}
				</td>
				<td>
					<c:if test="${task.status==0}">
						正常
					</c:if>
					<c:if test="${task.status==1}">
						关闭
					</c:if>
				</td>
				<td>
					${task.desc}
				</td>
				<td>
					<c:if test="${task.status==0}">
						<input type="button" class="manager_button edit_class" onclick="closeTask('${task.taskName}')"  value="关闭 ">
					</c:if>
					<c:if test="${task.status==1}">
						<input type="button" class="manager_button edit_class" onclick="openTask('${task.taskName}')"  value="开启">
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body> 
</html>