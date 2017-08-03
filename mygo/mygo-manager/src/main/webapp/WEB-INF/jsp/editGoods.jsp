<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${ctx}/js/uploadify/uploadify.css">
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.min.js"></script>
<title>编辑商品</title>
<script type="text/javascript">
	
	function editAttrTableCell(obj,caid,attrName){
		if($(obj).prop('checked')){
			$('.optTh').before('<td class="'+caid+'" width="210px" align="center">'+attrName+'</td>');
			$('.optTr').before('<td class="'+caid+' skuAttr"><input type="hidden" name="attrId" value="'+caid+'"><input class="searchInput" type="text" name="attrValue" align="center" value=""></td>');
		}else{
			$('.'+caid).remove();
		}
	}

	function editAttrTableRow(type,obj){
		if(type==0){
			var ahtml='<tr class="skuTr" height="25px">'+$('.skuTr:last').html()+'</tr>';
			$('#skuTable').append(ahtml);
			$('.skuTr :last').find('input[name="barcode"]').val('');
			$('.skuTr :last').find('input[name="barcode"]').attr('before','');
			$('.skuTr :last').find('input[name="number"]').val('');
			$('.skuTr :last').find('input[name="number"]').attr('before','0');
			$('.skuTr :last').find('input[name="stock"]').val('');
			$('.skuTr :last').find('input[name="stock"]').attr('before','0');
			$('.skuTr :last').find('input[name="sid"]').val('');
			$('.skuTr :last').find('input[name="price"]').val('');
			$('.skuTr :last').find('input[name="attrValue"]').val('');
			$('.skuTr:last').find('.optTr').html('<img alt="删除" title="删除" style="cursor: pointer;" src="${ctx}/img/delete.png" onclick="editAttrTableRow(1,this)" width="20px">')
		}else{
			if($('.skuTr').size()>1){
				$(obj).parent().parent().remove();
			}
		}
		
	}
	
	
	function syncStock(obj){
		if($(obj).val()==''){
			return;
		}
		var newNumber=parseInt($(obj).val());
		var oldNumber=parseInt($(obj).attr("before"));
		var optObj=$(obj).parent().parent().find('input[name="stock"]');
		var newStock=parseInt(optObj.attr('before'))+(newNumber-oldNumber);
		optObj.val(newStock);
		
	}
	function submitForm(){
		var isOk=true;
		var goods=new Object();
		goods.gid=$('#gid').val();
		goods.goodsCode=$('#goodsCode').val();
		if(goods.goodsCode==''){
			alert("商品编码不能为空!");
			isOk=false;
			return;
		}
		goods.goodsName=$('#goodsName').val();
		if(goods.goodsName==''){
			alert("商品名称不能为空!");
			isOk=false;
			return;
		}
		goods.cid=$('#cid option:selected').val();
		var skus=[];
		var skuIndex=0;
		$('.skuTr').each(function(){
			var sku=new Object();
			sku.barcode=$(this).find("input[name='barcode']").eq(0).val();
			if(sku.barcode==''){
				alert("sku条码不能为空!");
				isOk=false;
				return false;
			}
			sku.skuPic=$(this).find('input[name="skuPic"]').eq(0).val();
			if(sku.skuPic==''){
				alert("sku:{"+sku.barcode+"}图片未上传!");
				isOk=false;
				return false;
			}
			var isup=false;
			$('.finishSku').each(function(){
				if($(this).val()==sku.barcode){
					isup=true;
				}
			});
			if(!isup){
				alert("sku:{"+sku.barcode+"}图片未上传!");
				isOk=false;
				return false;
			}
			sku.price=$(this).find("input[name='price']").eq(0).val();
			if(sku.price==''){
				alert("sku:{"+sku.barcode+"}价格不能为空!");
				isOk=false;
				return false;
			}
			
			var regp = /^\d+(\.\d+)?$/;
			if(!regp.test(sku.price))
			{
				alert("sku:{"+sku.barcode+"}价格只能为正数值!");
				isOk=false;
				return false;
			}
			
			sku.number=$(this).find("input[name='number']").eq(0).val();
			if(sku.number==''){
				alert("sku:{"+sku.barcode+"}初始库存不能为空!");
				isOk=false;
				return false;
			}
			var reg = /^\d+$/;
			if(!reg.test(sku.number)){
				alert("sku:{"+sku.barcode+"}初始库存只能为正整数!");
				isOk=false;
				return false;
			}
			sku.stock=$(this).find("input[name='stock']").eq(0).val();
			sku.sid=$(this).find("input[name='sid']").eq(0).val();
			var attrs=[];
			var attrIndex=0;
			$(this).find(".skuAttr").each(function(){
				var attr=new Object();
				attr.attrId=$(this).find("input[name='attrId']").eq(0).val();
				attr.attrValue=$(this).find("input[name='attrValue']").eq(0).val();
				
				if(attr.attrValue==''){
					alert("sku:{"+sku.barcode+"}属性不能为空!");
					isOk=false;
					return false;
				}
				
				attrs[attrIndex]=attr;
				attrIndex++;
			})
			if(!isOk){
				isOk=false;
				return false;
			}
			sku.attrs=attrs;
			skus[skuIndex]=sku;
			skuIndex++;
			
		});
		if(!isOk){
			return;
		}
		goods.skus=skus;
		goods.goodsPic=$('#goodsPic').val();
		var goodsStr=JSON.stringify(goods);
		$.ajax({
			url:'${ctx}/login/goods/updateGoods.do',
			type:'post',
			data:{'goods':goodsStr},
			dataType:'json',
			success:function(data){
				if(data.message=="success"){
					alert('保存商品成功');
					location.reload();
				}else{
					alert(data.data);
				}
			}
		});
		
	}
	function updateBarcode(obj){
		if($(obj).val()!=''){
			var count=0;
			$('input[name="barcode"]').each(function(){
				if($(obj).val()==$(this).val()){
					count++;
				}
			});
			if(count>1){
				alert("条码不能重复!");
				$(obj).val($(obj).attr('before'));
				return;
			}
		}
		if($(obj).attr('before')!=''){
			$('.skuCode option').each(function(){
				if($(this).html()==$(obj).attr('before')){
					if($(obj).val()==''){
						$(this).remove();
					}else{
						$(this).val($(obj).val());
						$(this).html($(obj).val());
					}
				}
			});
		}else{
			if($(obj).val()!=''){
				$('.skuCode').append('<option value="'+$(obj).val()+'">'+$(obj).val()+'</option>');
			}
		}
		$(obj).attr('before',$(obj).val());
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
<body style="margin: 8px">
	<div>
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
			<span style="float: left;margin-left: 5px;">
				管理中心 - 编辑商品
			</span>
		</div>
	<form action="" enctype="multipart/form-data">

		<div style="width: 100%;height: 50px;line-height: 50px;font-weight: bold;">
			【商品信息】
		</div>
		<table>
			<tr>
				<td width="90px">商品编码：</td>
				<td>
					<input type="hidden" name="gid" value="${goods.gid}" id="gid" readonly="readonly">
					<input class="searchInput" type="text" name="goodsCode" value="${goods.goodsCode}" id="goodsCode" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td><input class="searchInput" type="text" id="goodsName" value="${goods.goodsName}"></td>
			</tr>
			<tr>
				<td>商品分类：</td>
				<td>
					<select class="searchInput" id="cid" name="cid" onchange="listAttrs()" disabled="disabled">
						<option value="1" <c:if test="${goods.cid==1 }">selected="selected"</c:if>>食品</option>
						<option value="2" <c:if test="${goods.cid==2 }">selected="selected"</c:if>>服装</option>
						<option value="3" <c:if test="${goods.cid==3 }">selected="selected"</c:if>>家具</option>
						<option value="4" <c:if test="${goods.cid==4 }">selected="selected"</c:if>>电器</option>
					</select>
					<input type="hidden" name="cid" value="${goods.cid}">
				</td>
			</tr>
			<tr id="attrs">
				<c:if test="${attrs!=null && !empty attrs}">
					<td>商品规格：</>
					<td>
						<c:forEach items="${attrs}" var="attr">
							<c:forEach items="${goods.skus[0].attrs}" var="sattr">
								<c:if test="${attr.caid==sattr.attrId}">
									<c:set var="flag" value="1"></c:set>
								</c:if>
							</c:forEach>
							${attr.attrName} <input type="checkbox" style="width:20px;" disabled="disabled" name="attr" <c:if test="${flag==1}">checked="checked"</c:if> onchange="editAttrTableCell(this,${attr.caid},'${attr.attrName}')" value="${content.caid }">
						</c:forEach>
					</td>
				</c:if>   
			</tr>
		</table>
		<div style="width: 100%;height: 30px;line-height: 30px;font-weight: bold;margin-top: 20px;">
			【sku信息】
		</div>
		<table id="skuTable" cellpadding="0" cellspacing="0">
			<tr>
				<td width="210px" align="center">条码</td>
				<td width="210px" align="center">价格</td>
				<td width="210px" align="center">总库存</td>
				<td width="210px" align="center">剩余库存</td>
				<c:forEach items="${goods.skus[0].attrs}" var="attr">
					<td width="210px" align="center">${attr.attrName}</td>
				</c:forEach>
				<td  width="50px" align="center" class="optTh">操作</td>
			</tr>
			<c:forEach items="${goods.skus}" var="sku" varStatus="status">
				<tr class="skuTr" height="25px">
					<td align="center">
						<input type="hidden" name="sid" value="${sku.sid}">
						<input class="searchInput" type="text" name="barcode" before="${sku.barcode}" onblur="updateBarcode(this)" value="${sku.barcode}">
						<input type="hidden" name="skuPic" value="${sku.skuPic}">
					</td>
					<td align="center"><input class="searchInput" type="text" name="price" value="${sku.price}"></td>
					<td align="center"><input class="searchInput" type="text" name="number" onblur="syncStock(this)" before="${sku.number}" value="${sku.number}"></td>
					<td align="center"><input class="searchInput" type="text" name="stock" value="${sku.stock}"  before="${sku.stock}" readonly="readonly"></td>
					<c:forEach items="${sku.attrs}" var="attr">
						<td width="190px" align="center" class="${attr.attrId} skuAttr">
							<input type="hidden" name="attrId" value="${attr.attrId}">
							<input class="searchInput" type="text" name="attrValue" value="${attr.attrValue}">
						</td>
					</c:forEach>
					<c:if test="${status.index==0}">
						<td align="center" class="optTr"><img alt="添加一行" title="添加" style="cursor: pointer;" src="${ctx}/img/add.png" onclick="editAttrTableRow(0)" width="20px"></td>
					</c:if>
					<c:if test="${status.index!=0}">
						<td align="center" class="optTr"><img alt="添加" title="添加" style="cursor: pointer;" src="${ctx}/img/delete.png" onclick="editAttrTableRow(1,this)" width="20px"></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<br>
		<%@ include file="/common/upload/upload.jsp" %>
		<br>
		<table>
			<tr>
				<td>
					<c:choose>
						<c:when test='${goods.status=="O"}'>
						</c:when>
						<c:otherwise>
							<input type="button" class="manager_button" onclick="submitForm()" value=" 提交 ">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body> 
</html>