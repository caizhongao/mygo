<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<div style="width: 80%;margin: 0px auto;">
			<img src="${ctx}/img/erro.png" usemap="#planetmap">
			<map name="planetmap" id="planetmap">
				<area shape="rectangle" coords="893,665,1073,737" href ="${ctx}/" alt="Venus" />
			</map>
			<div style="display: none">错误码：${erroCode}</div>
			<div style="position: absolute;top: 846px;left: "></div>
		</div>
		
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
	
</body> 
</html>