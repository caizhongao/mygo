<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
	#mytable{
		font-size: 14px;
	}
	table td{
		border-right: 1px solid #F2F2F2;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 100px;
		color: #666666;
		font-size: 14px;
	}
	table th{
		border-right: 1px solid #DFDFDF;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		background-color: #EFEEF0;
		color: #666666;
	}
	
}
</style>
</head>
<body>
	<div style="width:95%;margin: 0px auto;">
	<table style="width: 95%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="7" style="border-right: none;text-align: right;height: 50px;"> 
				<a href="${ctx}/manager/category/addCategory.do" class="manager_button">添加分类</a>
			</td>
		</tr>
		<tr>
			<th>
				序号
			</th>
			<th>
				分类名称
			</th>
			<th>
				分类级别
			</th>
			<th>
				状态
			</th>
			<th>
				操作
			</th>	
		</tr>
		<c:forEach items="${categoryList}" var="category" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${category.cname}
				</td>
				<td>
					一级分类
				</td>
				<td>
					<c:choose>
						<c:when test="${category.status==0}">
							正常
						</c:when>
						<c:when test="${category.status==1}">
							作废
						</c:when>
					</c:choose>
				</td>
				<td style="width: 180px;">
					<a href="${ctx}/manager/category/editCategory.do?cid=${category.cid}" class="manager_button">编辑</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	</div>
</body> 
</html>