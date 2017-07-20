<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${ctx}/js/uploadify/uploadify.css">
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.min.js"></script>
<title>用户注册</title>
<script type="text/javascript">
	function submitForm(){
		var category=new Object();
		category.cname=$('#cname').val();
		if(category.cname==''){
			alert('类目名称不能为空!');
			return;
		}
		category.orderId=$('#orderId').val();
		if(category.orderId==''){
			alert('类目排序不能为空!');
			return;
		}
		if(!isInt(category.orderId)){
			alert("类目排序必须是整数");
			return;
		}
		category.status=$('#status option:selected').val();
		if(category.status==''){
			alert('请选择状态!');
			return;
		}
		category.cid=$('#cid').val();
		var attrs=[];
		var attrIndex=0;
		$("input[name='attrName']").each(function(){
			if($(this).val()!=''){
				var attr=new Object();
				attr.attrName=$(this).val();
				
				attr.caid=$(this).next("input[name='caid']").eq(0).val();
	
				attrs[attrIndex]=attr;
				attrIndex++;
			}
		});
		category.attrList=attrs;
		var categoryStr=JSON.stringify(category);
		alert(categoryStr);
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
			var ahtml='<div>'+
							'<span style="display: inline-block;width: 215px;">'+
								'<input type="text" class="searchInput" name="attrName" value="">'+
							'</span>'+
							'<span style="width: 25px;display: inline-block;vertical-align: middle;cursor: pointer;" onclick="editAttrRow(1,this)" title="删除">'+
								'<img alt="删除" src="${ctx}/img/delete.png" width="20px">'+
							'</span>'+
						'</div>';
			$('#attrTd').append(ahtml);
		}else{
			$(obj).parent().remove();
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
</style>
</head>
<body>
	<div style="margin: 0px auto">
			<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
		<span style="float: left;margin-left: 5px;">
			管理中心 - 添加分类
		</span>
		</div>
	<form action="" enctype="multipart/form-data">
		<table class="categoryTable">
			<tr>
				<td width="60px">名称：</td>
				
				<td>
				<input type="hidden" name="cid" id="cid" value="${category.cid}">
				<input type="text" class="searchInput" name="cname" value="${category.cname}" id="cname"></td>
			</tr>
			<tr>
				<td>排序：</td>
				<td><input type="text" class="searchInput" name="orderId" value="${category.orderId}" id="orderId"></td>
			</tr>
			<tr>
				<td>状态：</td>
				<td>
					<select name="status" id="status" class="searchInput" style="text-align: center;">
						<option value="">请选择</option>
						<option value="0" <c:if test="${category.status==0}">selected="selected"</c:if>>正常</option>
						<option value="1" <c:if test="${category.status==1}">selected="selected"</c:if>>失效</option>
					</select>
				</td>
			</tr>
			<tr>
				<td valign="top" style="padding-top: 5px;">属性：</td>
				<td id="attrTd">
				<c:if test="${category.attrList==null || empty category.attrList}">
					<div>
						<span style="display: inline-block;width: 215px;">
							<input type="text" class="searchInput" name="attrName" value="">
						</span><span style="width: 25px;display: inline-block;vertical-align: middle;cursor: pointer;" onclick="editAttrRow(0)" title="添加一行">
							<img alt="添加" src="${ctx}/img/add.png" width="20px">
						</span>
					</div>
					</c:if>
					<c:forEach items="${category.attrList}" var="attr" varStatus="status">
					<div>
						<span style="display: inline-block;width: 215px;">
							<input type="text" class="searchInput" name="attrName" value="${attr.attrName}">
							<input type="hidden" name="caid" value="${attr.caid}">
						</span><c:if test="${status.index==0 }"><span style="width: 25px;display: inline-block;vertical-align: middle;cursor: pointer;" onclick="editAttrRow(0)" title="添加一行">
							<img alt="添加" src="${ctx}/img/add.png" width="20px">
						</span>
						</c:if><c:if test="${status.index>0 }"><span style="width: 25px;display: inline-block;vertical-align: middle;cursor: pointer;" onclick="editAttrRow(1,this)" title="删除">
								<img alt="删除" src="${ctx}/img/delete.png" width="20px"></span></c:if>
					</div>
			</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="button" class="manager_button" onclick="submitForm()" value=" 提交 ">
					<span style="width: 40px;visibility:hidden;" >
						<img alt="添加" src="${ctx}/img/add.png" width="30px">
					</span>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body> 
</html>