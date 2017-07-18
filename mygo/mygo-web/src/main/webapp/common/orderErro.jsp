<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx }/common/404/error_all.css?t=201303212934">
<title>Insert title here</title>
</head>
<body class="_body">

<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
			<div id="doc_main">
				<section class="bd clearfix">
					<div class="module-error">
						<div class="error-main clearfix">
							<div class="label"></div>
							<div class="info" style="padding-top:30px ">
								<c:choose>
									<c:when test='${erroCode=="3001"}'>
										<h3 class="title">请勿重复生成订单!</h3>
										<div class="reason">
											<p>可进入我的订单页查看订单</p>
										</div>
										<div class="oper">
											<p><a href="${ctx}/login/order/listOrder.do">我的订单&gt;</a></p>
											<p><a href="${ctx}/">回到网站首页&gt;</a></p>
										</div>
									</c:when>
									<c:when test='${erroCode=="3000"}'>
										<h3 class="title">订单超时，请重新购买!</h3>
										<div class="reason">
											<p>可回到商品详情页重新购买</p>
										</div>
										<div class="oper">
											<p><a href="${ctx}/login/order/listOrder.do">我的订单&gt;</a></p>
											<p><a href="${ctx}/">回到网站首页&gt;</a></p>
										</div>
									</c:when>
									<c:when test='${erroCode=="3003"}'>
										<h3 class="title">商品库存不足!</h3>
										<div class="reason">
											<p>请重新选择商品进行购买</p>
										</div>
										<div class="oper">
											<p><a href="${ctx}/login/order/listOrder.do">我的订单&gt;</a></p>
											<p><a href="${ctx}/">回到网站首页&gt;</a></p>
										</div>
									</c:when>
								</c:choose>
								
							</div>
						</div>
					</div>
				</section>
			</div>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>


</body></html>
