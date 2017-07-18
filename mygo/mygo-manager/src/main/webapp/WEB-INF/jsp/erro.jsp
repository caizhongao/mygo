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
			<div id="doc_main">
				<section class="bd clearfix">
					<div class="module-error">
						<div class="error-main clearfix">
							<div class="label"></div>
							<div class="info" style="padding-top:30px ">
								<h3 class="title">抱歉，系统发生错误</h3>
								<div class="reason">
									<p>错误码：${erroCode}</p>
									<p>错误原因：${message}</p>
								</div>
								<div class="oper">
									<p>您可以重试或者联系系统管理员&gt;</p>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>


</body></html>
