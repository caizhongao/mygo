<%@page import="com.cza.common.ShoppingContants"%>
<%@page import="com.cza.common.ParamUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://www.mygo.cn/prop"  prefix="prop"%>  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />


<c:set var="goodsDetail" value='<%=ParamUtil.getParam("goodsdetail.path") %>'></c:set>