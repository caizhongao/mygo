<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <link rel="stylesheet" type="text/css" href="${ctx}/common/search/css/normalize.css" />
<link rel="stylesheet" href="${ctx}/common/search/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/common/search/css/style.css">
 <style>
 	.page_middle{
 	    margin-top: 20px;
 	}
 </style>
<div style="width: 80%; margin: 0px auto;margin-bottom: 20px;">
	<div class="search d1" style="float: right">
		  <form action="${ctx}/unlogin/goods/search.do" method="get">
		  <input type="text" name="searchKey" value="${goods.searchKey}" placeholder="搜索">
		  <button type="submit"></button>
		  </form>
	</div>
	<div style="clear:right; "></div>
</div>
