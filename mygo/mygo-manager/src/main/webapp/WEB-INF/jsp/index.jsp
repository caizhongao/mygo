<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" href="${ctx}/css/sccl.css"> 
<link rel="stylesheet" type="text/css" href="${ctx}/css/skin/qingxin/skin.css" id="layout-skin"/>
    <style type="text/css">
    	#goback:HOVER{
    		cursor: pointer;
    	}
    	#goback{
    		font-weight: normal;
    	}
    </style>
    <script type="text/javascript">
     	function goback(){
     		window.history.go(-1);
     	}
    </script>
</head>
<body>
	<div class="layout-admin">
		<header class="layout-header">
			<span class="header-logo">mygo管理后台</span> 
			<a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
			<ul class="header-bar">
				<li class="header-bar-role"><a href="javascript:;">管理员</a></li>
				<li class="header-bar-nav">
					<a href="javascript:;">admin<i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
					<ul class="header-dropdown-menu">
	<!-- 					<li><a href="javascript:;">个人信息</a></li>
						<li><a href="javascript:;">切换账户</a></li> -->
						<li><a href="${ctx}/login/home/logout.do">退出</a></li>
					</ul>
				</li>
				<li class="header-bar-nav"> 
					<a href="javascript:;" title="换肤"><i class="icon-font">&#xe608;</i></a>
					<ul class="header-dropdown-menu right dropdown-skin">
						<li><a href="javascript:;" data-val="qingxin" title="清新">清新</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="molv" title="墨绿">墨绿</a></li>
						
					</ul>
				</li>
			</ul>
		</header>
		<aside class="layout-side">
			<ul class="side-menu">
			  
			</ul>
		</aside>
		<div class="layout-side-arrow"><div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div></div>
		
		<section class="layout-main">
			<div class="layout-main-tab">
				<button class="tab-btn btn-left" id="goback" onclick="goback()"><i class="icon-font">&#xe60e;</i></button>
                <nav class="tab-nav">
                    <div class="tab-nav-content">
                        <a href="javascript:;" class="content-tab active" data-id="${ctx}/login/home/home.do">首页</a>
                    </div>
                </nav>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
			</div>
			<div class="layout-main-body">
				<iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="${ctx}/manager/page/home.html" frameborder="0" data-id="${ctx}/manager/page/home.html" seamless></iframe>
			</div>
		</section>
		<div class="layout-footer">@2016 0.1 www.mycodes.net</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/sccl.js"></script>
	<script type="text/javascript" src="${ctx}/js/sccl-util.js"></script>
</body> 
</html>