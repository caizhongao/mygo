<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
</head>
<body>
	<div>
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
		<span style="float: left;margin-left: 5px;">
			管理中心 - 用户列表
		</span>
		</div>
		<form action="${ctx}/login/userManager/listUser.do" method="post">
			<div class="query_div">
				<span style="display: inline-block;width: 300px">
					登录名：<input class="searchInput" type="text" name="userName" value="${user.userName}">
				</span>
				<span style="display: inline-block;width: 300px">
					性别：<select class="searchInput" name="sex">
							<option value="">请选择</option>
							
					    	<option value="M" <c:if test='${user.sex=="M" }'>selected="selected"</c:if>>男</option>
					    	<option value="F" <c:if test='${user.sex=="F" }'>selected="selected"</c:if>>女</option>
					    </select>
				</span>
				<span style="display: inline-block;width: 150px;text-align: center">
					<input type="button" class="searchBtn" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
				</span>
			</div>
		</form>
	<table class="listTable" width="100%" cellspacing="1">
		<tr>
			<th>
				序号
			</th>
			<th>
				账号
			</th>
			<th>
				密码
			</th>
			<th>	
				性别
			</th>
			<th>
				姓名
			</th>
			<th>
				年龄
			</th>
			<th>
				注册时间
			</th>
		</tr>
		<c:forEach items="${pager.result}" var="user" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${user.userName}
				</td>
				<td>
					${user.password}
				</td>
				<td>
					<c:if test='${user.sex== "M"}'>
						男
					</c:if>
					<c:if test='${user.sex== "F"}'>
						女
					</c:if>
				</td>
				<td>
					${user.realName}
				</td>
				<td>
					${user.age}
				</td>
				<td>
					<jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>
					<jsp:setProperty property="time" name="dateObject" value="${user.createTime*1000}"/>
					<fmt:formatDate value="${dateObject}" pattern="yyyy-MM-dd"/></font>
				</td>
			</tr>
		</c:forEach>
		
	</table>
			<br>
		<%@ include file="/common/page.jsp" %>
	</div>
</body> 
</html>