<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div style="width:100%;height: 40px;background-color: #F7F7F7;color: #666666;">
	<div style="width:80%;margin: 0px auto;">
		<table style="width: 100%">
			<tr>
				<td style="line-height:35px;font-size: 14px;">
					<c:choose>
						<c:when test="${sessionScope.user_session==null}">
							您还没有登录,请&nbsp;<a href="${ctx}/unlogin/user/toLogin.do" >登录</a>
						</c:when>
						<c:otherwise>
							你好，${sessionScope.user_session.userName}&nbsp;<a href="${ctx}/unlogin/user/logout.do" >退出</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					&nbsp;
				</td>
				<td  style="line-height:35px;font-size: 14px;width: 80px;" align="left">
					<a href="${ctx}/">首页</a> &nbsp;
				</td>
				<td  style="line-height:35px;font-size: 14px;width: 100px;" align="left">
					<a href="${ctx}/">我的订单</a>&nbsp;
				</td>
				<td  style="line-height:35px;font-size: 14px;width: 100px;" align="left">
					<a href="${ctx}/login/addr/editAddr.do">我的地址</a>&nbsp;
				</td>
			</tr>
		</table>
	</div>
	
</div>