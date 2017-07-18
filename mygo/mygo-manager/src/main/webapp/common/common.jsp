<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page isELIgnored="false" %> 
        <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <%@ taglib uri="http://www.mygo.cn/prop"  prefix="prop"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="goodsDetail" value='${prop:get("request.goodsdetail.path")}'></c:set>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
