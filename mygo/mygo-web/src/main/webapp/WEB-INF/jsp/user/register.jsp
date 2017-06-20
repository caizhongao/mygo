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
		if(isOk){
			showErroInfo(sexObj,erroMsg,0);
		}else{
			showErroInfo(sexObj,erroMsg,1);
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
		if(isOk){
			showErroInfo(passwordObj,erroMsg,0);
		}else{
			showErroInfo(passwordObj,erroMsg,1);
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
		if(isOk){
			showErroInfo(ageObj,erroMsg,0);
		}else{
			showErroInfo(ageObj,erroMsg,1);
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
		if(isOk){
			showErroInfo(realNameObj,erroMsg,0);
		}else{
			showErroInfo(realNameObj,erroMsg,1);
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
		if(isOk){
			showErroInfo(userNameObj,erroMsg,0);
		}else{
			showErroInfo(userNameObj,erroMsg,1);
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
						showErroInfo($("input[name='userName']"), "登录名已存在!",1)
						isExist=true;
					}else{
						isExist=false;
					}
				}
			});
		}
	}
	function showErroInfo(obj,msg,flag){
		if(flag==0){
			obj.parent().next().html("&nbsp;✔");
			obj.css("border","1px solid #b2b2b2");
			obj.parent().next().removeClass("erroClass");
			obj.parent().next().addClass("rightClass");
		}else{
			obj.parent().next().html(msg);
			obj.css("border","1px solid #f20266");
			obj.parent().next().removeClass("rightClass");
			obj.parent().next().addClass("erroClass");
		}
		
	}
</script>
<style type="text/css">
	#mytable{
		font-size: 14px;
		color: #666;
	}
	#mytable input{
		border: 1px solid #b2b2b2;
		width: 250px;
		height: 30px;
	}
	.rightClass{
		color: #92D412;font-size: 22px;
		font-weight: bold;
	}
	.erroClass{
		color: red;font-size: 13px;
	}
</style>
</head>
<body class="_body">
<div class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<form action="${ctx}/unlogin/user/register.do" method="post" id="registerForm" enctype="application/x-www-form-urlencoded">
			<table width="1000" align="center" id="mytable">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="center" style="font-weight: bold;font-size: 20px">注册中心</td>
					<td style="width: 300px"></td>
				</tr>
				<tr height="50px;" valign="middle">
					<td rowspan="6">
						<img alt="" src="${ctx}/img/login/login-bj-ad.png">
					</td>
					<td width="70px" align="left">登录名:</td>
					<td style="width:180px"><input name="userName" type="text" value="${user.userName}" onblur="checkExist()"></td>
					<c:if test="${erroList.userName==null}">
						<td title="登录名由20个以内的英文字符和数字组成">
							登录名由20个以内的英文字符和数字组成
						</td>
					</c:if>
					<c:if test="${erroList.userName!=null}">
						<td title="登录名由20个以内的英文字符和数字组成" class="erroClass">
							${erroList.userName}
						</td>
					</c:if>
				</tr>
				<tr height="50px;" valign="middle">
					<td>密码:</td>
					<td><input name="password" type="text" value="${user.password}" onblur="checkPassword()"></td>
					<c:if test="${erroList.password==null}">
						<td title="请输入6~20位之间的密码">
							请输入6~20位之间的密码
						</td>
					</c:if>
					<c:if test="${erroList.password!=null}">
						<td title="请输入6~20位之间的密码" class="erroClass">
							${erroList.password}
						</td>
					</c:if>
				</tr>
				<tr height="50px;" valign="middle">
					<td width="70px">真实姓名:</td>
					<td><input name="realName" type="text" value="${user.realName}" onblur="checkRealName()"></td>
					<c:if test="${erroList.realName==null}">
						<td title="真实姓名由20个以内的字符组成">
							真实姓名由20个以内的字符组成
						</td>
					</c:if>
					<c:if test="${erroList.realName!=null}">
						<td title="真实姓名由20个以内的字符组成" class="erroClass">
							${erroList.realName}
						</td>
					</c:if>
				</tr>
				<tr height="50px;" valign="middle">
					<td>年龄:</td>
					<td><input name="age" type="text" value="${user.age }" onblur="checkAge()"></td>
					<c:if test="${erroList.age==null}">
						<td title="请填写0-200之间的年龄">
							请填写0-200之间的年龄
						</td>
					</c:if>
					<c:if test="${erroList.age!=null}">
						<td title="请填写0-200之间的年龄" class="erroClass">
							${erroList.age}
						</td>
					</c:if>
				</tr>
				<tr height="50px;" valign="middle">
					<td>性别:</td>
					<td valign="middle">
						男：<input type="radio" style="width: 15px;height: 15px;" onClick="checkSex()" name="sex" value="M" <c:if test='user.sex=="M"'>checked="checked"</c:if>>
	 				            女：<input type="radio" style="width: 15px;height: 15px;" onClick="checkSex()" name="sex" value="F" <c:if test='user.sex=="F"'>checked="checked"</c:if>>
					</td>
					<c:if test="${erroList.sex==null}">
						<td title="请选择性别">
							请选择性别
						</td>
					</c:if>
					<c:if test="${erroList.sex!=null}">
						<td title="请选择性别" class="erroClass">
							${erroList.sex}
						</td>
					</c:if>
				</tr>
				<tr height="30px;" valign="middle">
					<td></td>
					<td>
						<input type="button" value="立即注册" onclick="submitForm()" style="width: 250;height: auto" class="register_button">
					</td>
					<td></td>
				</tr>		
			</table>
		</form>
	</div>
</div>
	
</body> 
</html>