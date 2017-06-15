<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function submitForm(){
		$('#erroMsg').html('');
		var isOk=checkName();
		isOk=checkPassword()&&isOk;
		isOk=checkCode()&&isOk;
		if(isOk){
			$('#loginForm').submit();
		}
	}
	
	
	function checkName(){
		var userNameObj=$("input[name='userName']").eq(0);
		userNameObj.parent().next().html("");
		if(userNameObj.val()==''){
			userNameObj.parent().next().html(".请输入登录名");
			return false;
		}
		return true;
	}
	
	function checkPassword(){
		var passwordObj=$("input[name='password']").eq(0);
		passwordObj.parent().next().html("");
		if(passwordObj.val()==''){
			passwordObj.parent().next().html(".请输入密码");
			return false;
		}
		return true;
	}
	
	function checkCode(){
		var picCodeObj=$("input[name='picCode']").eq(0);
		picCodeObj.parent().next().html("");
		if(picCodeObj.val()==''){
			picCodeObj.parent().next().html(".请输入验证码");
			return false;
		}
		return true;
	}
	
	function reloadCode(){
	    var time=new Date().getTime();
        document.getElementById("imagecode").src="<%= request.getContextPath()%>/servlet/ImageServlet?d="+time;
	}
</script>
<style type="text/css">
	#mytable{
		font-size: 14px;
	}
</style>
</head>
<body>
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<form action="${ctx}/unlogin/user/login.do" method="post" id="loginForm" enctype="application/x-www-form-urlencoded">
			<table width="700" align="center" id="mytable">
				<tr>
				<td>&nbsp;
					</td>
					<td align="center" style="font-weight: bold;font-size: 20px">用户登录</td>
					<td>&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="center">
						<div id="erroMsg" style="text-align: left;width: 140px;margin: 0px auto;color: red;font-size: 13px;">
							<c:forEach items="${erroList}" var="erroStr">
							<br>.${erroStr}
						</c:forEach>
						</div>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr height="50px;" valign="middle">
					<td width="70px">登录名:</td>
					<td width="175px">
					<input name="ref" type="hidden" value="${ref}">
					<input name="userName" type="text" value="${user.userName}"></td>
					<td style="color: red" width="455px"></td>
				</tr>
				<tr height="50px;" valign="middle">
					<td>密码:</td>
					<td><input name="password" type="text" value="${user.password}" ></td>
					<td style="color: red"></td>
				</tr>
				
				<tr height="50px;" valign="middle">
					<td>验证码:</td>
					<td>
						<input  type="text" name="picCode" value="${user.picCode}" style="width: 100px;vertical-align:middle"/>
						<a href="javascript:reloadCode();">
   	 				    	<img alt="验证码" id="imagecode" src="<%= request.getContextPath()%>/servlet/ImageServlet" style="vertical-align:middle;margin-left: 2px"/>
   	 				    </a>
					</td>
					<td style="color: red"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					记住密码<input type="checkbox" name="rememberMe" value="1" <c:if test="${user.rememberMe==1}"> checked="checked"</c:if> />
					</td>
				</tr>
				<tr height="50px;" valign="middle">
					<td colspan="2" align="right">
						<input type="button" value="登录" onclick="submitForm()" class="submitBtn"></td>
					<td style="color: #666666;font-size: 13px">还有没有账号?<a href="${ctx}/unlogin/user/toRegister.do">请注册</a></td>
				</tr>		
			</table>
		</form>
	</div>
</div>
	
</body> 
</html>