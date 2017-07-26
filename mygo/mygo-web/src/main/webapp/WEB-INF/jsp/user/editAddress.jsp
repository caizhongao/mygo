<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的地址</title>
<style type="text/css">
	input,select{
		width: 175px;
		border: 1px solid #DFDFDF;
	}
	.selectAddr{
		width:80px;
	}
	.listTable td{
		text-align: center;
	}
	.editTable tr{
		height: 50px;
		color: #666666;
		font-size: 14px;
	}
</style>
<script type="text/javascript">
	function clearForm(){
		$('input[name="uaid"]').val('');
		$('input[name="receiver"]').val('');
		$('input[name="mobilphone"]').val('');
		$('#city').val('');
		$('#area').val('');
		$('textarea[name="addr"]').val('');
		$('input[name="isDefault"]').prop('checked',false);
	}

	function listCitys(){
		$('#city').html('<option value="">请选择</option>')
		$('#area').html('<option value="">请选择</option>')
		var aid=$('#province option:selected').val();
		if(aid!=''){
			$.each(listAreas(aid),function(index,city){
				$('#city').append('<option value="'+city.aid+'">'+city.aname+'</option>');
			});
		}
	}
	
	function listCountys(){
		$('#area').html('<option value="">请选择</option>')
		var aid=$('#city option:selected').val();
		if(aid!=''){
			$.each(listAreas(aid),function(index,area){
				$('#area').append('<option value="'+area.aid+'">'+area.aname+'</option>');
			});
		}
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
			$.each(listAreas(pid),function(index,city){
				if((city.aid+'')==cid){
					$('#city').append('<option selected="selected" value="'+city.aid+'">'+city.aname+'</option>');
				}else{
					$('#city').append('<option value="'+city.aid+'">'+city.aname+'</option>');
				}
			});
			$.each(listAreas(cid),function(index,area){
				if((area.aid+'')==aid){
					$('#area').append('<option selected="selected" value="'+area.aid+'">'+area.aname+'</option>');
				}else{
					$('#area').append('<option value="'+area.aid+'">'+area.aname+'</option>');
				}
			});
		}
	});
</script>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top1.jsp" %>
	<div class="page_middle">
		<div style="margin: 0px auto;width: 80%">
			<form action="${ctx}/login/addr/saveAddr.do" id="addrForm" method="post" enctype="application/x-www-form-urlencoded">
				<table class="editTable" align="center">
					<tr>
						<td colspan="2" style="font-weight:bold">编辑地址信息</td>
					</tr>
					<tr>
						<td width="90px">收件人：</td>
						<td>
							<input type="hidden" name="uaid" value="${addr.uaid }">
							<input type="text" name="receiver" value="${addr.receiver }" style="height: 30px;width: 240px;" id="receiver"><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td>手机：</td>
						<td><input type="text" name="mobilphone" value="${addr.mobilphone }" style="height: 30px;width: 240px;" id="mobilphone"><font color="red">*</font></td>
					</tr>
					<tr>
						<td>收件地址：</td>
						<td>
							<select id="province" name="provinceId" onchange="listCitys()" style="height: 30px;width: 77px;" class="selectAddr">
								<option value="">请选择</option>
								<c:forEach items="${provinces}" var="province">
									<option value="${province.aid}">${province.aname}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="province">
							<select id="city" name="cityId" onchange="listCountys()"  class="selectAddr" style="height: 30px;width: 78px;">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="city">
							<select id="area" name="areaId"  class="selectAddr" style="height: 30px;width: 77px;">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="area"><font color="red">*</font>
						</td>
					</tr>
					<tr style="height: 80px">
						<td valign="top" style="line-height: 40px;">详细地址：</>
						<td id="attrs">
							<textarea rows="4" cols="50"  name="addr">${addr.addr}</textarea><font color="red">*</font>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td>默认地址：</td>
						<td id="attrs">
							<input type="checkbox" name="isDefault" value="1" style="width: 20px;">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" onclick="submitAddr()" class="searchBtn" value="提交">
						</td>
					</tr>
				</table>
			</form>
			<div style="width: 95%;height: 40px;">
				<input type="button" onclick="clearForm()" class="searchBtn" style="float: right" value="新增地址">
			</div>
			<div style="clear: left"></div>
			<table class="listTable" style="width: 95%" cellspacing="1">
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
							<a href="${ctx}/login/addr/editAddr.do?uaid=${addr.uaid}" title="编辑">
								<img alt="" src="${ctx}/img/edit.png" width="25px">
								
							</a>
							<%-- <c:if test='${addr.isDefault==0}'>
								<a href="${ctx}/login/addr/setDefault.do?uaid=${addr.uaid}" class="manager_button">设为默认</a>
							</c:if> --%>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
	<%@ include file="/common/bottom1.jsp" %>
</div>
</body> 
</html>