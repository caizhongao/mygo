<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="${ctx}/common/home/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="${ctx}/common/home/css/form.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/common/home/css/fwslider.css" media="all">
<link href="${ctx}/common/home/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<!-- start menu -->

<%-- <script type="text/javascript" src="${ctx}/common/home/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script> --%>
<!--start slider -->
    
    <script src="${ctx}/common/home/js/jquery-ui.min.js"></script>
    <script src="${ctx}/common/home/js/css3-mediaqueries.js"></script>
    <script src="${ctx}/common/home/js/fwslider.js"></script>
<style>
	.category{text-align: center;font-weight: bold;}
	.category:hover{background-color: #E92250;cursor: pointer;}
	.active{background-color: #E92250;cursor: pointer;}
	.ct-icon{display: inline-block; width: 24px; height: 24px;vertical-align: middle;position: relative; top: -2px;background: url(${ctx}/img/bg_icon.png) no-repeat 0 0;}
	.ct-icon-1{background-position: -216px 0;} /*食品**/
	.ct-icon-2{background-position: 0 0;} /*服装**/
	.ct-icon-3{background-position: -144px 0;} /*家具**/
	.ct-icon-4{background-position: -264px 0;} /*电器**/
	.activeC{
	color: #7db122!important;
	font-weight: bolder!important;
	font-size: 16px!important;}
</style>
<script>
	function gotoPage(cid){
		location.href="${ctx}/unlogin/goods/listCategoryGoods.do?cid="+cid;
	}
	function toIndex(){
		location.href="${ctx}/";
	}
 	
	$(function(){
		initCategory();
	});
	
/* 	function initCategory(){
		var cid='${cid}';
		$.ajax({
			url:'${ctx}/unlogin/goods/listCategory.do',
			type:'post',
			dataType:'json',
			success:function(categoryList){
				var categoryHtml='';
				$.each(categoryList,function(index,category){
					var active='';
					if(cid==category.cid){
						active='active';
					}
					categoryHtml+='<td width="20px">&nbsp;</td>'+
									'<td width="80px" class="category '+active+'" onclick="gotoPage('+category.cid+')">'+
									'<i class="ct-icon ct-icon-'+category.cid+'"></i>'+
									category.cname+
								   '</td>';
				});
				categoryHtml+='<td>&nbsp;</td>';
				$('#firstTd').after(categoryHtml);
			}
		});
	} */
	
	function initCategory(){
		var cid='${cid}';
		$.ajax({
			url:'${ctx}/unlogin/goods/listCategory.do',
			type:'post',
			dataType:'json',
			success:function(categoryList){
				var categoryHtml='<li class="active grid"><a href="javascript:void(0)" onclick="toIndex()">首页</a></li>';
				$.each(categoryList,function(index,category){
				 	var active='';
					if(cid==category.cid){
						active='activeC';
					}
					categoryHtml+='<li class="active grid"><a href="javascript:void(0)" class="'+active+'" onclick="gotoPage('+category.cid+')"">'+category.cname+'</a></li>';
				});
				$('#category').html(categoryHtml);
			}
		});
	}
</script>
	
<style>
	.title_a{
		font-weight: bold;
		font-size: 13px;
		color: white;
	}
</style>
<div style="width:100%;height: 35px;line-height: 35px;background-color: #4cb1ca;border-bottom: 1px solid #eee;color: white">
	<div style="width:80%;margin: 0px auto;">
		<table style="width: 100%;height: 27px">
			<tr>
				<td style="font-size: 12px;color: white;">
					<c:choose>
						<c:when test="${sessionScope.user_session==null}">
							您还没有登录,请&nbsp;<a href="${ctx}/unlogin/user/toLogin.do"  class="title_a">登录</a>
						</c:when>
						<c:otherwise>
							你好，${sessionScope.user_session.userName}&nbsp;<a href="${ctx}/unlogin/user/logout.do"  class="title_a">退出</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					&nbsp;
				</td>
				<td  style="width: 80px;" align="right">
					<a href="${ctx}/" class="title_a">首页</a> &nbsp;
				</td>
				<td  style="width: 100px;" align="right">
					<a href="${ctx}/login/order/listOrder.do" class="title_a">我的订单</a>&nbsp;
				</td>
				<td  style="font-size: 14px;width: 100px;" align="right">
					<a href="${ctx}/login/addr/editAddr.do" class="title_a">我的地址</a>&nbsp;
				</td>
			</tr>
		</table>
	</div>
	
</div>


<div class="header-bottom">
	    <div class="wrap">
			<div class="header-bottom-left">
				<div class="logo">
					<a href="index.html"><img src="${ctx}/common/home/images/logo.png" alt=""/></a>
				</div>
				<div class="menu">
	            <ul class="megamenu skyblue" id="category">
				</ul>
			</div>
		</div>
	   <div class="header-bottom-right">
         <div class="search">	  
	         <form action="${ctx}/unlogin/goods/search.do" method="get">
					<input  type="text" name="searchKey" value="${goods.searchKey}" placeholder="搜索" class="textbox" >
					<input type="submit" value="Subscribe" id="submit" name="submit">
			</form>
			<div id="response"> </div>
		 </div>
	  <div class="tag-list">
		<ul class="icon1 sub-icon1 profile_img">
			<li><a class="active-icon c2" href="#"> </a>
				<ul class="sub-icon1 list">
					<li><h3>没有商品</h3><a href=""></a></li>
					<li><p>您的购物车是空的,您可以添加商品到购物车</p></li>
				</ul>
			</li>
		</ul>
	    <ul class="last"><li><a href="${ctx}/login/cart/listCart.do">购物车(0)</a></li></ul>
	  </div>
    </div>
     <div class="clear"></div>
     </div>
	</div>
