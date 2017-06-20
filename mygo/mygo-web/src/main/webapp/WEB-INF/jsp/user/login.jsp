<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$(".login_input").click(function(){
	  $(this).parent(".input_div").css("border"," 1px solid #f20266");
	});
});
	
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
		showErroInfo(userNameObj,"","1px solid #b2b2b2");
		if(userNameObj.val()==''){
			showErroInfo(userNameObj,".请输入登录名","1px solid #f20266");
			return false;
		}
		return true;
	}
	function checkPassword(){
		var passwordObj=$("input[name='password']").eq(0);
		showErroInfo(passwordObj,"","1px solid #b2b2b2");
		if(passwordObj.val()==''){
			showErroInfo(passwordObj,".请输入密码","1px solid #f20266");
			return false;
		}
		return true;
	}
	function checkCode(){
		var picCodeObj=$("input[name='picCode']").eq(0);
		showErroInfo(picCodeObj,"","1px solid #b2b2b2");
		if(picCodeObj.val()==''){
			showErroInfo(picCodeObj,".请输入验证码","1px solid #f20266");
			return false;
		}
		return true;
	}
	
	function showErroInfo(obj,msg,borderCss){
		obj.parent().parent().next().html(msg);
		obj.parent(".input_div").css("border",borderCss);
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
	.input_div{
		border: 1px solid #b2b2b2;
		width: 280px;
	}
	.login_input{
		width:200px;
		outline:none;
		border: 0px;padding: 12px 0 12px 5px;
	}
	#imagecode:HOVER {
		cursor: pointer;
	}
</style>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<form action="${ctx}/unlogin/user/login.do" method="post" id="loginForm" enctype="application/x-www-form-urlencoded">
			<table width="900" align="center" id="mytable">
				<tr>
					<td colspan="4" height="40px"></td>
				</tr>
				<tr height="50px" valign="middle">
					<td rowspan="5">
						<img alt="" src="${ctx}/img/login/login-bj-ad.png">
					</td>
					<td rowspan="5" width="100px">
						&nbsp;
					</td>
					<td width="275px">
						<input name="ref" type="hidden" value="${ref}">
						<div class="input_div">
							<div style="background: url(${ctx}/img/login/name2.jpg?150907) no-repeat #f1f1f1;width: 39px;height: 38px;float: left;"></div>
							<input name="userName" type="text" value="${user.userName}" class="login_input" placeholder="登陆名" onblur="checkName()" autocomplete="off">
						</div>
					</td>
					<td style="color: ff464e;font-size: 13px;" width="455px">${erroList.userName}</td>
				</tr>
				<tr height="60px;" valign="middle">
					<td width="275px">
						<div class="input_div">
							<div style="background: url(${ctx}/img/login/code2.jpg?150907) no-repeat #f1f1f1;width: 39px;height: 38px;float: left;"></div>
							<input type="password" name="password" value="${user.password}" class="login_input" placeholder="密码" onblur="checkPassword()" autocomplete="off">
						</div>
					</td>
					
					<td style="color: ff464e;font-size: 13px;">${erroList.password}</td>
				</tr>
				<tr height="60px;" valign="middle">
					<td>
						<div class="input_div" style="width: 175px;vertical-align: middle;display: inline-block;" >
							<div style="background: url(${ctx}/img/login/code2.jpg?150907) no-repeat #f1f1f1;width: 39px;height: 38px;float: left;"></div>
							<input name="picCode" type="text" value="${user.picCode}" class="login_input" placeholder="验证码" onblur="checkCode()" style="width: 120px" autocomplete="off">
						</div>
						<img alt="验证码" width="100px" height="40px" id="imagecode" src="${ctx}/servlet/ImageServlet" onclick="reloadCode()" style="vertical-align:middle">
					</td>
					<td style="color: ff464e;font-size: 13px;">${erroList.picCode}</td>
				</tr>
				
				<tr height="60px;" valign="middle">
					<td colspan="2">
						<input type="button" value="登录" onclick="submitForm()" class="login_button">
					</td>
				</tr>	
				<tr>
					<td style="color: #333;font-size: 13px">
						<div style="display: inline-block;width: 220px;">
							<input type="checkbox" style="vertical-align:middle" name="rememberMe" value="1" <c:if test="${user.rememberMe==1}"> checked="checked"</c:if>/>&nbsp;记住密码
						</div>
						<a href="${ctx}/unlogin/user/toRegister.do" style="color: #21B1E3">免费注册</a>
					</td>
					<td style="color: #666666;font-size: 13px">
					</td>
				</tr>	
			</table>
		</form>
	</div>
</div>
	
</body> 
</html>