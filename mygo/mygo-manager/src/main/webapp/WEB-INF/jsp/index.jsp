<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>mygo后台管理</title>
<link rel="stylesheet" href="${ctx}/css/sccl.css"> 
<link rel="stylesheet" type="text/css" href="${ctx}/css/skin/qingxin/skin.css" id="layout-skin"/>
    <style type="text/css">
    	#goback:HOVER{
    		cursor: pointer;
    	}
    	#goback{
    		font-weight: normal;
    	}
    	.tab-nav-content a{
    		text-decoration: none;
    	}
    </style>
    <script type="text/javascript">
     	function goback(){
     		window.history.go(-1);
     	}

     	/*
     	  初始化加载
     	*/
     	$(function(){
     		/*获取皮肤*/
     		//getSkinByCookie();
     		/*菜单json*/
     		var menu = [{"id":"1","name":"主菜单","parentId":"0","url":"","icon":"","order":"1","isHeader":"1","childMenus":[
							{"id":"11","name":"用户管理","parentId":"1","url":"","icon":"&#xe604;","order":"1","isHeader":"0","childMenus":[
       							{"id":"12","name":"用户管理","parentId":"11","url":"${ctx}/login/userManager/listUser.do","icon":"","order":"1","isHeader":"0","childMenus":""}
       						]},
     						{"id":"3","name":"商品管理","parentId":"1","url":"","icon":"&#xe604;","order":"2","isHeader":"0","childMenus":[
     							{"id":"4","name":"商品管理","parentId":"3","url":"${ctx}/login/goods/listGoods.do","icon":"","order":"1","isHeader":"0","childMenus":""},
     							{"id":"5","name":"分类管理","parentId":"3","url":"${ctx}/login/category/listCategory.do","icon":"","order":"1","isHeader":"0","childMenus":""}
     						]},
     						{"id":"6","name":"订单管理","parentId":"1","url":"","icon":"&#xe602;","order":"3","isHeader":"0","childMenus":[
								{"id":"7","name":"未付款","parentId":"6","url":"${ctx}/login/order/listNotPayOrder.do","icon":"","order":"1","isHeader":"0","childMenus":""},
     							{"id":"8","name":"已付款","parentId":"6","url":"${ctx}/login/order/listPayOrder.do","icon":"","order":"1","isHeader":"0","childMenus":""},
     							{"id":"9","name":"已退款","parentId":"6","url":"${ctx}/login/order/listRefundOrder.do","icon":"","order":"1","isHeader":"0","childMenus":""},
     							{"id":"10","name":"已关闭","parentId":"6","url":"${ctx}/login/order/listCloseOrder.do","icon":"","order":"1","isHeader":"0","childMenus":""}
     						]},
     						{"id":"13","name":"系统管理","parentId":"1","url":"","icon":"&#xe604;","order":"1","isHeader":"0","childMenus":[
								{"id":"14","name":"定时任务管理","parentId":"13","url":"${ctx}/login/task/listTask.do","icon":"","order":"1","isHeader":"0","childMenus":""}
							]}
     					 ]}
     				    ];
     		initMenu(menu,$(".side-menu"));
     		$(".side-menu > li").addClass("menu-item");
     		
     		/*获取菜单icon随机色*/
     		//getMathColor();
     	}); 
    </script>
</head>
<body>
	<div class="layout-admin">
		<header class="layout-header">
			<span class="header-logo">MYGO管理后台</span> 
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
				<iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="${ctx}/login/home/home.do" frameborder="0" data-id="${ctx}/login/home/home.do" seamless></iframe>
			</div>
		</section>
		<div class="layout-footer">@2016 0.1 www.mycodes.net</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/sccl.js"></script>
	<script type="text/javascript" src="${ctx}/js/sccl-util.js"></script>
</body> 
</html>