<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${ctx}/js/uploadify/uploadify.css">
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.min.js"></script>
<title>类目管理</title>
<script type="text/javascript">

	function submitForm(){
		var category=new Object();
		category.cname=$('#cname').val();
		category.cid=$('#cid').val();
		category.status=$('#status option:selected').val();
		var attrs=[];
		var attrIndex=0;
		$(".attrTr").each(function(){
			var attr=new Object();
			attr.attrName=$(this).find("input[name='attrName']").eq(0).val();
			if(attr.attrName==''){
				return;
			}
			attr.caid=$(this).find("input[name='caid']").eq(0).val();
			attrs[attrIndex]=attr;
			attrIndex++;
		});
		category.attrList=attrs;
		var categoryStr=JSON.stringify(category);
		$.ajax({
			url:'${ctx}/login/category/updateCategory.do',
			type:'post',
			data:{'category':categoryStr},
			success:function(data){
				if('success'==data){
					alert('保存类目成功');
				}else{
					alert('保存类目失败');
				}
			}
		});
		
	}
	
	
	function editAttrRow(type,obj){
		if(type==0){
			var ahtml='<tr class="attrTr"  height="25px">'+$('.attrTr:last').html()+'</tr>';
			$('#attrTable').append(ahtml);
			$('.attrTr :last').find('input[name="attrName"]').val('');
			$('.attrTr :last').find('input[name="caid"]').val('');
		}else{
			if($('.attrTr').size()>1){
				$(obj).parent().parent().remove();
			}
		}
		
	}
	
	
	
</script>
<style type="text/css">
	table{
		font-size: 14px;
		color: #666666;
	}
	table tr{
		height: 40px;
	}
	input,select{
		width: 175px;
		border: 1px solid #DFDFDF;
	}
</style>
</head>
<body>
	<div style="width: 97%;margin: 0px auto">
	<form action="" enctype="multipart/form-data">
		<table class="categoryTable">
			<tr>
				<td colspan="2" style="font-weight:bold">分类信息</td>
			</tr>
			<tr>
				<td width="90px">分类名称：</td>
				<td>
					<input type="hidden" name="cid" id="cid" value="${category.cid}">
					<input type="text" name="cname" value="${category.cname}" id="cname">
				</td>
			</tr>
			<tr>
				<td width="90px">状态：</td>
				<td>
					<select name="status" id="status">
						<option value="0" <c:if test="${category.status==0}">selected="selected"</c:if>>正常<option>
						<option value="1" <c:if test="${category.status==1}">selected="selected"</c:if>>失效<option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="font-weight:bold">分类属性</td>
			</tr>
		</table>
		<input type="button" class="manager_button" onclick="editAttrRow(0)" value=" 添加一行 ">
		<br>
		<table id="attrTable" cellpadding="0" cellspacing="0">
			<tr>
				<td width="190px" align="center">属性名称</td>
				<td  width="80px" align="center" class="optTh">操作</td>
			</tr>
			<c:if test="${category.attrList==null || empty category.attrList}">
				<tr class="attrTr" height="25px">
					<td align="center"><input type="text" name="attrName" value=""></td>
					<td align="center" class="optTr"><input type="button" class="manager_button" onclick="editAttrRow(1,this)" value=" 删除 "></td>
				</tr>
			</c:if>
			<c:forEach items="${category.attrList}" var="attr">
				<tr class="attrTr" height="25px">
					<td align="center">
						<input type="text" name="attrName" value="${attr.attrName}">
						<input type="hidden" name="caid" value="${attr.caid}">
					</td>
					<td align="center" class="optTr"><input type="button" class="manager_button" onclick="editAttrRow(1,this)" value=" 删除 "></td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<td><input type="button" class="manager_button" onclick="submitForm()" value=" 提交 "></td>
			</tr>
		</table>
	</form>
	</div>
</body> 
</html>