<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page isELIgnored="false" %> 
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script>
	function listAreas(paid){
		var result;
		$.ajax({
			url:'${ctx}/login/addr/listAreas.do',
			type:'post',
			async:false,
			dataType:'json',
			data:{'paid':paid},
			success:function(data){
				if(data.message=='success'){
					result=data.data;
				}else{
					alert('查询区域失败!');
					result=null;
				}
			}
		});
		return result;
	}
	
</script>
