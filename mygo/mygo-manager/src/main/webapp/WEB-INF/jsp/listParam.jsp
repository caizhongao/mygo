<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>

<style type="text/css">
	.edit_class{
		width: 50px;
		height: 23px;
		font-size: 12px;
		line-height: 23px;
	    padding:0;
	    border-radius: 2px;
	}
</style>
<script type="text/javascript">
function submitCf(){
	var cfKey=$('input[name="cfKey"]').val();
	if(cfKey==''){
		alert('配置名称不能为空!');
		return;
	}
	var cfValue=$('input[name="cfValue"]').val();
	if(cfValue==''){
		alert('配置值不能为空!');
		return;
	}
	var cfDesc=$('input[name="cfDesc"]').val();
	var cfType=$('select[name="cfType"]').val();
	if(cfType==''){
		alert('所属应用不能为空!');
		return;
	}
	$.ajax({
		url:'${ctx}/login/param/saveParam.do',
		type:'post',
		data:{
			"cfKey":cfKey,
			"cfValue":cfValue,
			"cfDesc":cfDesc,
			"cfType":cfType
		},
		dataType:'json',
		success:function(data){
			if(data.message=='success'){
				alert("保存成功!");
				location.reload();
			}else{
				alert("保存失败,"+data.data);
			}
		}
	});
}

function deleteParam(index){
	$.ajax({
		url:'${ctx}/login/param/deleteParam.do',
		type:'post',
		data:{
			"cfKey":$.trim($('.cfKey').eq(index).html()),
			"cfType":$.trim($('.cfType').eq(index).html())
		},
		dataType:'json',
		success:function(data){
			if(data.message=='success'){
				alert("保存成功!");
				location.reload();
			}else{
				alert("保存失败,"+data.data);
			}
		}
	});
}

function editParam(index){
	$('#editAddrInfo').css('display','block');
	var bh = $("body").height(); 
	var bw = $("body").width(); 
	$("#fullbg").css({ 
		height:bh, 
		width:bw, 
		display:"block" 
	}); 
	if(index>0){//编辑
		$('.cfKeyT').val($.trim($('.cfKey').eq(index).html()));
		$('.cfValueT').val($.trim($('.cfValue').eq(index).html()));
		$('.cfDescT').val($.trim($('.cfDesc').eq(index).html()));
		$('.cfTypeT').val($.trim($('.cfType').eq(index).html()));
	}else{
		$('input[name="cfKey"]').val('');
		$('input[name="cfValue"]').val('');
		$('input[name="cfDesc"]').val('');
		$('select[name="cfType"]').val('');
	}
}

function closeBg() { 
	$("#fullbg,#editAddrInfo").hide(); 
} 
</script>
</head>
<body>
	<div>
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
			<span style="float: left;margin-left: 5px;">
				管理中心 - 定时任务列表
			</span>
			<span style="float: right;padding-right: 10px;">
				<input type="button" class="addBtn" onclick="editParam(0)" style="width: 100px" value="新增配置">
			</span>
		</div>
			<div id="fullbg" style="background-color:gray; left:0; opacity:0.5; position:absolute; top:0; z-index:3; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity:0.5; "></div> 
			<div style="display:none;position: absolute;border-radius:5px;width:500px;height: 320px;z-index: 100;background-color: #EFEEF0;left: 25%;top: 100px" id="editAddrInfo">
				<div onclick="closeBg()" style="width: 480px;height: 20px;margin: 0px auto;line-height: 25px;color: #666666;font-size: 20px;font-weight: bold;text-align: right;cursor: pointer;">
					<a style="text-decoration: none;">×</a>
				</div>
				<table class="editTable" style="width:420px;" align="center">
					<tr style="height: 35px;">
						<td colspan="2" style="font-weight:bold"><img src="${ctx}/img/edit.png" style="width: 20px;height: 20px;vertical-align: middle;">编辑配置信息</td>
					</tr>
					<tr style="height: 35px;">
						<td width="90px" align="right">配置名：</td>
						<td>
							<input type="text" name="cfKey" class="searchInput cfKeyT" style="height: 30px;width: 240px;"><font color="red">*</font>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td align="right">配置值：</td>
						<td><input type="text" name="cfValue" class="searchInput cfValueT" style="height: 30px;width: 240px;"><font color="red">*</font></td>
					</tr>
					<tr style="height: 35px;">
						<td align="right">应用名：</td>
						<td align="left">
							<select name="cfType" onchange="listCountys()" class="searchInput cfTypeT" style="height: 30px;width: 240px;">
								<option value="">请选择</option>
								<option value="mygo-web">mygo-web</option>
								<option value="mygo-manager">mygo-manager</option>
							</select>
							<font color="red">*</font>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td align="right">描　述：</td>
						<td align="left">
							<input type="text" name="cfDesc" class="searchInput cfDescT" style="height: 30px;width: 240px;">
						</td>
					</tr>
					
					<tr style="height: 55px;">
						<td colspan="2" style="padding-left: 25px">
							<input type="button" value="提交" class="searchBtn" onclick="submitCf()"></td>
						</td>
					</tr>
				</table>
			</div>
				<!-- 搜索区域 -->
		<form action="${ctx}/login/param/listParam.do" method="post">
			<div class="query_div">
				<span class="query_span">
					配置名称：<input type="text" class="searchInput" name="cfKey" value="${param.cfKey}">
				</span>
				<span class="query_span">
					所属应用：
					<select name="cfType" class="searchInput">
						<option value="">所有</option>
						<option value="mygo-web" <c:if test='${param.cfType=="mygo-web"}'>selected="selected"</c:if>>mygo-web</option>
						<option value="mygo-manager" <c:if test='${param.cfType=="mygo-manager"}'>selected="selected"</c:if>>mygo-manager</option>
					</select>
				</span>
				<span style="display: inline-block;width: 150px;text-align: center">
					<input type="button" class="searchBtn" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
				</span>
			</div>
		</form>
		<!-- 搜索区域  end-->
	<table class="listTable" width="100%" cellspacing="1">
		<tr>
			<th>
				序号
			</th>
			<th>
				配置名称(key)
			</th>
			<th>
				配置值(value)
			</th>
			<th>
				配置描述
			</th>
			<th>	
				所属应用
			</th>
			<th>
				操作
			</th>
		</tr>
		<c:forEach items="${paramList}" var="config" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td class="cfKey">
					${config.cfKey}
				</td>
				<td style="width: 400px;">
					<div style="width: 400px;">
						<p class="cfValue" style="display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="${config.cfValue}">
							${config.cfValue}
						</p>
					</div>
				</td>
				<td class="cfDesc">
					${config.cfDesc}
				</td>
				<td class="cfType">
					${config.cfType}
				</td>
				<td>
					<img alt="" width="23px" src="${ctx}/img/edit.png" title="修改" onclick="editParam(${status.index+1})" style="cursor: pointer;">
					&nbsp;
					<img alt="" width="23px" src="${ctx}/img/closeOrder.png" title="删除" onclick="deleteParam(${status.index+1})" style="cursor: pointer;">
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body> 
</html>