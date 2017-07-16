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
								<h3 class="title">对不起，无法购买，请重新选择商品进行购买！</h3>
								<div class="reason">
									<p>订单确认页面不能刷新！</p>
									<p>订单确认数据不完整！</p>
								</div>
								<div class="oper">
									<p><a href="javascript:history.go(-1);">返回上一级页面&gt;</a></p>
									<p><a href="${ctx}/">回到网站首页&gt;</a></p>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>


</body></html>
