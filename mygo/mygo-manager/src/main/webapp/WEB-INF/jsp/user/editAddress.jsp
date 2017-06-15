<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		font-size: 14px;
		color: #666666;
	} 
	
		#mytable{
		font-size: 14px;
	}
	.tableData td{
		border-right: 1px solid #F2F2F2;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		color: #666666;
		font-size: 14px;
	}
	.tableData th{
		border-right: 1px solid #DFDFDF;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		background-color: #EFEEF0;
		color: #666666;
	}
	.editTable tr{
		height: 40px;
	}
	.tableData tr{
		height: 40px;
	}
	input,select{
		width: 175px;
		border: 1px solid #DFDFDF;
	}
	.selectAddr{
		width:80px;
	}
</style>
<script type="text/javascript">
	function clearForm(){
		$(':input','#addrForm') 
		.not(':button, :submit, :reset') 
		.val('') 
		.removeAttr('checked') 
		.removeAttr('selected');
	}

	function listCitys(){
		$('#city').html('<option value="">请选择</option>')
		$('#area').html('<option value="">请选择</option>')
		var aid=$('#province option:selected').val();
		if(aid==''){
			return;
		}
		$.ajax({
			url:'${ctx}/login/addr/listAreas.do',
			type:'post',
			dataType:'json',
			data:{'paid':aid},
			success:function(data){
				$.each(data,function(index,city){
					$('#city').append('<option value="'+city.aid+'">'+city.aname+'</option>');
				});
			}
		});
	}
	
	function listAreas(){
		$('#area').html('<option value="">请选择</option>')
		
		var aid=$('#city option:selected').val();
		if(aid==''){
			return;
		}
		$.ajax({
			url:'${ctx}/login/addr/listAreas.do',
			type:'post',
			dataType:'json',
			data:{'paid':aid},
			success:function(data){
				
				$.each(data,function(index,area){
					$('#area').append('<option value="'+area.aid+'">'+area.aname+'</option>');
				});
			}
		});
	}
	function submitAddr(){
		if($('#province option:selected').val()==''){
			alert("请选择省份!");
			return;
		}else{
			$('input[name="province"]').val($('#province option:selected').html());
		}
		if($('#city option:selected').val()==''){
			alert("请选择城市!");
			return;
		}else{
			$('input[name="city"]').val($('#city option:selected').html());
		}
		if($('#area option:selected').val()==''){
			alert("请选择区县!");
			return;
		}else{
			$('input[name="area"]').val($('#area option:selected').html());
		}
		$('#addrForm').submit();
	}
	$(function(){
		var pid='${addr.provinceId}';
		var cid='${addr.cityId}';
		var aid='${addr.areaId}';
		if(pid!=''){
			$.ajax({
				url:'${ctx}/login/addr/listAreas.do',
				type:'post',
				dataType:'json',
				data:{'paid':pid},
				success:function(data){
					$.each(data,function(index,city){
						if((city.aid+'')==cid){
							$('#city').append('<option selected="selected" value="'+city.aid+'">'+city.aname+'</option>');
						}else{
							$('#city').append('<option value="'+city.aid+'">'+city.aname+'</option>');
						}
						
					});
				}
			});
			$.ajax({
				url:'${ctx}/login/addr/listAreas.do',
				type:'post',
				dataType:'json',
				data:{'paid':cid},
				success:function(data){
					$.each(data,function(index,area){
						if((area.aid+'')==aid){
							$('#area').append('<option selected="selected" value="'+area.aid+'">'+area.aname+'</option>');
						}else{
							$('#area').append('<option value="'+area.aid+'">'+area.aname+'</option>');
						}
						
					});
				}
			});
		}
		
	})
</script>
</head>
<body>
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
	<div style="margin: 0px auto;width: 80%">
	<form action="${ctx}/login/addr/saveAddr.do" id="addrForm" method="post" enctype="application/x-www-form-urlencoded">
		<table class="editTable">
			<tr>
				<td colspan="2" style="font-weight:bold">编辑地址信息</td>
			</tr>
			<tr>
				<td width="90px">收件人：</td>
				<td>
					<input type="hidden" name="uaid" value="${addr.uaid }">
					<input type="text" name="receiver" value="${addr.receiver }" id="receiver"><font color="red">*</font></td>
			</tr>
			<tr>
				<td>手机：</td>
				<td><input type="text" name="mobilphone" value="${addr.mobilphone }" id="mobilphone"><font color="red">*</font></td>
			</tr>
			<tr>
				<td>收件地址：</td>
				<td>
					<select id="province" name="provinceId" onchange="listCitys()" class="selectAddr">
						<option value="">请选择</option>
						<c:forEach items="${provinces}" var="province">
							<option value="${province.aid}" <c:if test="${addr.provinceId==province.aid}">selected="selected"</c:if>>${province.aname}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="province">
					<select id="city" name="cityId" onchange="listAreas()"  class="selectAddr">
						<option value="">请选择</option>
					</select>
					<input type="hidden" name="city">
					<select id="area" name="areaId"  class="selectAddr">
						<option value="">请选择</option>
					</select>
					<input type="hidden" name="area"><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td valign="top">详细地址：</>
				<td id="attrs">
					<textarea rows="4" cols="50" name="addr">${addr.addr}</textarea><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>默认地址：</>
				<td id="attrs">
					<input type="checkbox" name="isDefault" <c:if test="${addr.isDefault==1}">checked="checked"</c:if>   value="1" style="width: 20px;">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="javascript:submitAddr()"  class="manager_button">提交</a>
				</td>
			</tr>
		</table>
	</form>
	
	<table class="tableData" style="width: 95%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="8" style="border-right: none;text-align: right;height: 50px;"> 
				<a href="javascript:clearForm()" class="manager_button">新增地址</a>
			</td>
		</tr>
		<tr>
			<th>
				收件人
			</th>
			<th>
				电话
			</th>
			<th>
				省份
			</th>
			<th>
				城市
			</th>
			<th>
				区县
			</th>
			<th>
				详细地址
			</th>
			<th>
				是否默认
			</th>
			<th>
				操作	
			</th>
		</tr>
		<c:forEach items="${addrs}" var="addr">
			<tr>
				<td>
					${addr.receiver}
				</td>
				<td>
					${addr.mobilphone}
				</td>
				<td>
					${addr.province }
				</td>
				<td>
					${addr.city }
				</td>
				<td>
					${addr.area}
				</td>
				<td>
					${addr.addr}
				</td>
				<td>
					<c:choose>
						<c:when test='${addr.isDefault==0}'>
							否
						</c:when>
						<c:otherwise>
							是
						</c:otherwise>
					</c:choose>
				</td>
				<td style="width: 180px;">
					<a href="${ctx}/login/addr/editAddr.do?uaid=${addr.uaid}" class="manager_button">编辑</a>
					<%-- <c:if test='${addr.isDefault==0}'>
						<a href="${ctx}/login/addr/setDefault.do?uaid=${addr.uaid}" class="manager_button">设为默认</a>
					</c:if> --%>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	</div></div>
	</div>
</body> 
</html>