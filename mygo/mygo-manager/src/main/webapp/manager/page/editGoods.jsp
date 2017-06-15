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
	
	function editAttrTableCell(obj,caid,attrName){
		if($(obj).prop('checked')){
			$('.optTh').before('<td class="'+caid+'" width="190px" align="center">'+attrName+'</td>');
			$('.optTr').before('<td class="'+caid+' skuAttr"><input type="hidden" name="attrId" value="'+caid+'"><input type="text" name="attrValue" align="center" value=""></td>');
		}else{
			$('.'+caid).remove();
		}
	}

	function editAttrTableRow(type,obj){
		if(type==0){
			var ahtml='<tr class="skuTr"  height="25px">'+$('.skuTr:last').html()+'</tr>';
			$('#skuTable').append(ahtml);
			$('.skuTr :last').find('input[name="barcode"]').val('');
			$('.skuTr :last').find('input[name="sid"]').val('');
			$('.skuTr :last').find('input[name="price"]').val('');
			$('.skuTr :last').find('input[name="attrValue"]').val('');
		}else{
			if($('.skuTr').size()>1){
				$(obj).parent().parent().remove();
			}
		}
		
	}
	
	function initAttrTable(){
		$('#skuTable').html('<tr>'+
								'<td width="190px" align="center">条码</td>'+
								'<td width="190px" align="center">价格</td>'+
								'<td width="190px" align="center">总库存</td>'+
								'<td width="190px" align="center">剩余库存</td>'+
								'<td  width="80px" align="center" class="optTh">操作</td>'+
							'</tr>'+
							'<tr class="skuTr" height="25px">'+
								'<td align="center"><input type="text" name="barcode" value=""></td>'+
								'<td align="center"><input type="text" name="price" value=""></td>'+
								'<td align="center"><input type="text" name="number" onblur="syncStock(this)" value=""></td>'+
								'<td align="center"><input type="text" name="stock" value=""  readonly="readonly" ></td>'+
								'<td align="center" class="optTr"><input type="button" class="manager_button" onclick="editAttrTableRow(1,this)" value=" 删除 "></td>'+
							'</tr>'
							);
	}
	
	
	
	$(function(){
		 $('#upFile').uploadify({
	            'swf'      		:   "${ctx}/js/uploadify/uploadify.swf",
	            'uploader' 		: 	"${ctx}/manager/goods/uploadPic.do",
	            'cancelImg'		:	"${ctx}/js/uploadify/uploadify-cancel.png",
	            'debug'			:	false,
	            'buttonText'	:	'选择照片',
				'method'			:	'post',
				'buttonClass'	:  'upload_button',
				'fileTypeDesc'	:	'图片文件',
				'fileTypeExts'	:	'*.gif;*.jpg;*.png;*.bmp',
				'multi'				:	false,
				'formData'      :  {'goodsCode':$('#goodsCode').val()},
				'onSelect':function(file){
					var goodsCode=$('#goodsCode').val();
					if(goodsCode==""){
						alert("请选填写商品编码!");
						$('#upFile').uploadify('cancel', file.id);
	            		return false;
	            	}
					
				},
	            'onUploadComplete':	function(file){
	        	},
	            'onUploadStart': function (file) { 
	            	$("#upFile").uploadify("settings", "formData", { 'goodsCode': $('#goodsCode').val()});  
	            },

				/**
				 * 上传成功后触发事件
				 */
				'onUploadSuccess' : function(file, data, response) {
					$('#upImg').attr("src",data+"?d="+new Date().getTime());
					$('#goodsPic').val(data);
	    		}
	        });
		
	});
	function syncStock(obj){
		if($(obj).val()==''){
			return;
		}
		var newNumber=parseInt($(obj).val());
		var oldNumber=parseInt($(obj).attr("number"));
		var optObj=$(obj).parent().parent().find('input[name="stock"]');
		var oldStock=parseInt(optObj.val());
		var newStock=oldStock+(newNumber-oldNumber);
		optObj.val(newStock);
		$(obj).attr("number",newNumber);
		
	}
	function submitForm(){
		var goods=new Object();
		goods.gid=$('#gid').val();
		goods.goodsCode=$('#goodsCode').val();
		goods.goodsName=$('#goodsName').val();
		goods.cid=$('#cid option:selected').val();
		var skus=[];
		var skuIndex=0;
		$('.skuTr').each(function(){
			var sku=new Object();
			sku.barcode=$(this).find("input[name='barcode']").eq(0).val();
			sku.price=$(this).find("input[name='price']").eq(0).val();
			sku.number=$(this).find("input[name='number']").eq(0).val();
			sku.stock=$(this).find("input[name='stock']").eq(0).val();
			sku.sid=$(this).find("input[name='sid']").eq(0).val();
			var attrs=[];
			var attrIndex=0;
			$(this).find(".skuAttr").each(function(){
				var attr=new Object();
				attr.attrId=$(this).find("input[name='attrId']").eq(0).val();
				attr.attrValue=$(this).find("input[name='attrValue']").eq(0).val();
				attrs[attrIndex]=attr;
				attrIndex++;
			})
			sku.attrs=attrs;
			skus[skuIndex]=sku;
			skuIndex++;
			
		});
		goods.skus=skus;
		goods.goodsPic=$('#goodsPic').val();
		var goodsStr=JSON.stringify(goods);
		$.ajax({
			url:'${ctx}/manager/goods/updateGoods.do',
			type:'post',
			data:{'goods':goodsStr},
			success:function(data){
				if(data=="success"){
					alert('保存商品成功');
					location.reload();
				}else{
					alert('保存商品失败');
				}
			}
		});
		
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
		<table>
			<tr>
				<td colspan="2" style="font-weight:bold">商品基本信息</td>
			</tr>
			<tr>
				<td width="90px">商品编码：</td>
				<td>
					<input type="hidden" name="gid" value="${goods.gid}" id="gid" readonly="readonly">
					<input type="text" name="goodsCode" value="${goods.goodsCode}" id="goodsCode" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td><input type="goodsName" id="goodsName" value="${goods.goodsName}"></td>
			</tr>
			<tr>
				<td>商品分类：</td>
				<td>
					<select id="cid" name="cid" onchange="listAttrs()" disabled="disabled">
						<option value="1" <c:if test="goods.cid==1">selected="selected"</c:if>>食品</option>
						<option value="2" <c:if test="goods.cid==2">selected="selected"</c:if>>服装</option>
						<option value="3" <c:if test="goods.cid==3">selected="selected"</c:if>>家具</option>
						<option value="4" <c:if test="goods.cid==4">selected="selected"</c:if>>电器</option>
					</select>
					<input type="hidden" name="cid" value="${goods.cid}">
				</td>
			</tr>
			<tr>
				<td>商品规格：</>
				<td id="attrs">
					<c:forEach items="${attrs}" var="attr">
						<c:forEach items="${goods.skus[0].attrs}" var="sattr">
							<c:if test="${attr.caid==sattr.attrId}">
								<c:set var="flag" value="1"></c:set>
							</c:if>
						</c:forEach>
						${attr.attrName} <input type="checkbox" style="width:20px;" disabled="disabled" name="attr" <c:if test="${flag==1}">checked="checked"</c:if> onchange="editAttrTableCell(this,${attr.caid},'${attr.attrName}')" value="${content.caid }">
					</c:forEach>
				</td>   
			</tr>
			<tr style="height: 15px"><td colspan="2"></td></tr>
			<tr>
				<td colspan="2" style="font-weight:bold">商品sku信息</td>
			</tr>
			<tr style="height:5px">
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<input type="button" class="manager_button" onclick="editAttrTableRow(0)" value=" 添加一行 ">
		<br>
		<table id="skuTable" cellpadding="0" cellspacing="0">
			<tr>
				<td width="190px" align="center">条码</td>
				<td width="190px" align="center">价格</td>
				<td width="190px" align="center">总库存</td>
				<td width="190px" align="center">剩余库存</td>
				<c:forEach items="${goods.skus[0].attrs}" var="attr">
					<td width="190px" align="center">${attr.attrName}</td>
				</c:forEach>
				<td  width="80px" align="center" class="optTh">操作</td>
			</tr>
			<c:forEach items="${goods.skus}" var="sku">
				<tr class="skuTr" height="25px">
					<td align="center">
						<input type="hidden" name="sid" value="${sku.sid}">
						<input type="text" name="barcode" value="${sku.barcode}">
					</td>
					<td align="center"><input type="text" name="price" value="${sku.price}"></td>
					<td align="center"><input type="text" name="number" onblur="syncStock(this)"  number="${sku.number}" value="${sku.number}"></td>
					<td align="center"><input type="text" name="stock" value="${sku.stock}" readonly="readonly"></td>
					<c:forEach items="${sku.attrs}" var="attr">
						<td width="190px" align="center" class="${attr.attrId} skuAttr">
							<input type="hidden" name="attrId" value="${attr.attrId}">
							<input type="text" name="attrValue" value="${attr.attrValue}">
						</td>
					</c:forEach>
					<td align="center" class="optTr"><input type="button" class="manager_button" onclick="editAttrTableRow(1,this)" value=" 删除 "></td>
				</tr>
			</c:forEach>
		</table>
		<br><p>
		  <table cellpadding="0" cellspacing="0">
		  	<tr>
		  		<td style="font-weight:bold">
		  			商品图片信息<br>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>
		  			<div>
		  				<img src="${goods.goodsPic}" id="upImg" alt="上传图片" width="107px"/>
		  				<input type="hidden" name="goodsPic" id="goodsPic" value="${goods.goodsPic}">
		  			</div>
		  		</td>
		  	</tr>
		  </table>
		  	<br>
		 	 <input class="text-input small-input" type="file" id="upFile" />
		  </p>
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