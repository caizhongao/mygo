<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<script type="text/javascript">
	var isExist=false;
	function submitForm(){
		if(isExist){
			return;
		}
	 	var isOk=checkName();
		isOk=checkPassword()&&isOk;
		isOk=checkRealName()&&isOk;
		isOk=checkAge()&&isOk;
		isOk=checkSex()&&isOk;
		if(isOk){ 
			$('#registerForm').submit();
 		} 
	}
	
	function checkSex(){
		var isOk=true;
		var sexObj=$("input[name='sex']").eq(0);
		var sex=$("input[name='sex']:checked").val();
		var erroMsg="";
		if(sex==null){
			erroMsg+=".请选择性别";
			isOk=false;
		}
		sexObj.parent().next().next().html(erroMsg);
		if(isOk){
			sexObj.parent().next().css("display",'table-cell');
		}else{
			sexObj.parent().next().css("display",'none');
		}
		return isOk;
	}
	
	function checkPassword(){
		var isOk=true;
		var passwordObj=$("input[name='password']").eq(0);
		var erroMsg="";
		if(passwordObj.val()==''){
			erroMsg+=".密码不能为空";
			isOk=false;
		}else{
			if(passwordObj.val().length>20||passwordObj.val().length<6){
				erroMsg+=".密码长度必须在6~20个字符之间";
				isOk=false;
			}
		}
		passwordObj.parent().next().next().html(erroMsg);
		if(isOk){
			passwordObj.parent().next().css("display",'table-cell');
		}else{
			passwordObj.parent().next().css("display",'none');
		}
		return isOk;
	}
	
	
	function checkAge(){
		var isOk=true;
		var ageObj=$("input[name='age']").eq(0);
		var erroMsg="";
		if(ageObj.val()==''){
			erroMsg+=".年龄不能为空";
			isOk=false;
		}else{
			var reg=/^(\d)+$/;
			if(!reg.test(ageObj.val())){
				erroMsg+=".请输入正确的年龄";
				isOk=false;
			}
			if(parseInt(ageObj.val())>200||parseInt(ageObj.val())<0){
				if(erroMsg!=""){
					erroMsg+="<br>";
				}
				erroMsg+=".年龄必须在0~200之间";
				isOk=false;
			}
		}
		ageObj.parent().next().next().html(erroMsg);
		if(isOk){
			ageObj.parent().next().css("display",'table-cell');
		}else{
			ageObj.parent().next().css("display",'none');
		}
		return isOk;
	}
	function checkRealName(){
		var isOk=true;
		var realNameObj=$("input[name='realName']").eq(0);
		var erroMsg="";
		if(realNameObj.val()==''){
			erroMsg+=".用户名不能为空";
			isOk=false;
		}else{
			if(realNameObj.val().length>20){
				erroMsg+=".姓名长度不能超过20个字符";
				isOk=false;
			}
		}
		realNameObj.parent().next().next().html(erroMsg);
		if(isOk){
			realNameObj.parent().next().css("display",'table-cell');
		}else{
			realNameObj.parent().next().css("display",'none');
		}
		return isOk;
	}
	function checkName(){
		var isOk=true;
		var userNameObj=$("input[name='userName']").eq(0);
		var erroMsg="";
		if(userNameObj.val()==''){
			erroMsg+=".用户名不能为空";
			isOk=false;
		}else{
			var reg=/^[A-Za-z0-9]+$/;
			if(!reg.test(userNameObj.val())){
				erroMsg+=".用户名只能由数字和字母组成";
				isOk=false;
			}
			if(userNameObj.val().length>20||userNameObj.val().length<6){
				if(erroMsg!=""){
					erroMsg+="<br>";
				}
				erroMsg+=".用户名长度不能超过20个字符";
				isOk=false;
			}
		}
		userNameObj.parent().next().next().html(erroMsg);
		if(isOk){
			userNameObj.parent().next().css("display",'table-cell');
		}else{
			userNameObj.parent().next().css("display",'none');
		}
		return isOk;
	}
	function checkExist(){
		if(checkName()){
			$.ajax({
				type:'post',
				url:'${ctx}/unlogin/user/existName.do',
				data:{'userName':$("input[name='userName']").val()},
				success:function(data){
					if(data==1){
						$("input[name='userName']").parent().next().css("display",'none');
						$("input[name='userName']").parent().next().next().html("登录名已存在!");
						isExist=true;
					}else{
						isExist=false;
					}
				}
			});
		}
	}
</script>
<style type="text/css">
	#mytable{
		font-size: 14px;
	}
</style>
</head>
<body>
<div class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<form action="${ctx}/unlogin/user/register.do" method="post" id="registerForm" enctype="application/x-www-form-urlencoded">
			<table width="700" align="center" id="mytable">
				<tr>
					<td colspan="2" align="right" style="font-weight: bold;font-size: 20px">注册中心</td>
					<td colspan="2"></td>
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
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr height="50px;" valign="middle">
					<td width="70px" align="left">登录名:</td>
					<td style="width:180px"><input name="userName" type="text" value="${user.userName}" onblur="checkExist()"></td>
					<td style="color: #666666">登录名由20个以内的英文字符和数字组成</td>
					<td style="color: red">&nbsp;</td>
				</tr>
				<tr height="50px;" valign="middle">
					<td>密码:</td>
					<td><input name="password" type="text" value="${user.password}" ></td>
					<td style="color: #666666">请输入6~20位之间的密码</td>
					<td style="color: red"></td>
				</tr>
				<tr height="50px;" valign="middle">
					<td width="70px">真实姓名:</td>
					<td><input name="realName" type="text" value="${user.realName}"></td>
					<td style="color: #666666">真实姓名由20个以内的字符组成</td>
					<td style="color: red"></td>
				</tr>
				<tr height="50px;" valign="middle">
					<td>年龄:</td>
					<td><input name="age" type="text" value="${user.age }" ></td>
					<td style="color: #666666">请填写0-200之间的年龄</td>
					<td style="color: red"></td>
				</tr>
				<tr height="50px;" valign="middle">
					<td>性别:</td>
					<td>
						男：<input type="radio" name="sex" value="M" <c:if test='user.sex=="M"'>checked="checked"</c:if>>
	 				            女：<input type="radio" name="sex" value="F" <c:if test='user.sex=="F"'>checked="checked"</c:if>>
					</td>
					<td></td>
					<td style="color: red"></td>
				</tr>
				<tr height="30px;" valign="middle">
					<td colspan="2" align="right"><input type="button" value="提交" onclick="submitForm()" class="submitBtn"></td>
					<td colspan="2" align="right"></td>
				</tr>		
			</table>
		</form>
	</div>
</div>
	
</body> 
</html>