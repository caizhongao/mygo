<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
	.title_a{
		font-weight: bold;
		font-size: 13px;
		color: #777;
	}
</style>

<div style="width:100%;height: 28px;line-height: 26px;background-color: #f5f5f5;">
	<div style="width:80%;margin: 0px auto;">
		<table style="width: 100%;">
			<tr>
				<td style="font-size: 12px;color: #777;">
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