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
		width: 50px;
		height: 23px;
		font-size: 12px;
		line-height: 23px;
	    padding:0;
	    border-radius: 2px;
	}
</style>
<script type="text/javascript">

	function updateTask(taskName,index){
		var expression=$('input[name="expression"]').eq(index).val();
		var status=$('input[name="status"]').eq(index).val();
		if(confirm("确定更新定时任务["+taskName+"]?")){
			$.ajax({
				url:"${ctx}/login/task/updateTask.do",
				type:'post',
				data:{"taskName":taskName,"expression":expression,"status":status},
				dataType:"json",
				success:function(data){
					if(data.message=='success'){
						alert("更新成功!");
						location.reload();
					}else{
						alert("更新定时任务失败,"+data.data);
					}
				}
			});
		}
		
	}

	function closeTask(index){
		$('.crollImg').eq(index).parent().html(
				'<img src="${ctx}/img/off.png" class="crollImg" width="64px" title="关" onclick="openTask('+index+')" style="cursor: pointer;">'+
				'<input type="hidden"  name="status" value="1">');
	}
	
	
	function openTask(index){
		$('.crollImg').eq(index).parent().html(
				'<img src="${ctx}/img/on.png" class="crollImg" width="64px" title="开" onclick="closeTask('+index+')" style="cursor: pointer;">'+
				'<input type="hidden"  name="status" value="0">');
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
				描述
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
				周期(分钟/次)
			</th>
			<th>
				状态
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
					${task.desc}
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
					<input type="text" class="searchInput" name="expression" value="${task.expression}" style="text-align: center;border: none;border-bottom: 1px solid #ddd;min-width: 120px;width:120px">
				</td>
				<td>
					<c:if test="${task.status==0}">
						<img src="${ctx}/img/on.png"  class="crollImg" width="64px" title="开" onclick="closeTask(${status.index})" style="cursor: pointer;">
					</c:if>
					<c:if test="${task.status==1}">
						<img src="${ctx}/img/off.png" class="crollImg" width="64px" title="关" onclick="openTask(${status.index})" style="cursor: pointer;">
					</c:if>
					<input type="hidden"  name="status" value="${task.status}">
				</td>
				
				
				<td>
					<input type="button" class="manager_button edit_class" onclick="updateTask('${task.taskName}',${status.index})"  value="提交">
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body> 
</html>