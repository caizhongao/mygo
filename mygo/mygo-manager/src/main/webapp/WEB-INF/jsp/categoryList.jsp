<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<script type="text/javascript">
	function toAddCategory(){
		location.href="${ctx}/login/category/addCategory.do";
	}
	
	function editCategory(cid){
		location.href="${ctx}/login/category/editCategory.do?cid="+cid;
	}
	
</script>
<style type="text/css">
	table td{
	 text-align: center;
	}
</style>
</head>
<body>
	<div>
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
		<span style="float: left;margin-left: 5px;">
			管理中心 - 类目列表
		</span>
		<span style="float: right;padding-right: 10px;">
			<input type="button" class="addBtn" onclick="toAddCategory()" style="width: 100px" value="添加分类">
		</span>
		</div>
	<table class="listTable" width="100%">
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
					<span style="width: 30px;padding: 5px;cursor: pointer;" onclick="editCategory(${category.cid})" title="编辑">
						<img alt="" width="25px" src="${ctx}/img/edit.png">
					</span>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	</div>
</body> 
</html>