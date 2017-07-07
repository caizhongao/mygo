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
	function listAttrs(){
		var cid=$('#cid option:selected').val();
		$.ajax({
			url:'/mygo-manager/manager/category/listAttrs.do',
			type:'post',
			dataType:'json',
			data:{'cid':cid},
			success:function(data){
				var attrHtml="";
				$.each(data,function(index,content){
					if(index!=0&&index%3==0){
						attrHtml+="<br/>";
					}
					attrHtml+=content.attrName+'<input type="checkbox" style="width:20px;" name="attr" onchange="editAttrTableCell(this,'+content.caid+',\''+content.attrName+'\')" value="'+content.caid+'">';
				});
				$('#attrs').html(attrHtml);
				initAttrTable();
			}
		});
	}
	
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
								'<td width="190px" align="center">库存</td>'+
								'<td width="190px" align="center">剩余库存</td>'+
								'<td  width="80px" align="center" class="optTh">操作</td>'+
							'</tr>'+
							'<tr class="skuTr" height="25px">'+
								'<td align="center"><input type="text" name="barcode" before="" onblur="updateSkuSelect(this)" value=""><input type="hidden" name="skuPic" value="123"></td>'+
								'<td align="center"><input type="text" name="price" value=""></td>'+
								'<td align="center"><input type="text" name="number" onblur="syncStock(this)" value=""></td>'+
								'<td align="center"><input type="text" name="stock" value=""  readonly="readonly" ></td>'+
								'<td align="center" class="optTr"><input type="button" class="manager_button" onclick="editAttrTableRow(1,this)" value=" 删除 "></td>'+
							'</tr>'
							);
	}

	
	function syncStock(obj){
		$(obj).parent().parent().find('input[name="stock"]').val($(obj).val());
	}
	
	function submitForm(){
		var isOk=true;
		var goods=new Object();
		goods.goodsCode=$('#goodsCode').val();
		goods.goodsName=$('#goodsName').val();
		goods.cid=$('#cid option:selected').val();
		var skus=[];
		var skuIndex=0;
		
		
		if($('.skuPicInfo').length<$('.skuTr').length){
			alert("每个sku都需要上传图片!");
			return;
		}
		$('.skuTr').each(function(){
			var sku=new Object();
			sku.barcode=$(this).find("input[name='barcode']").eq(0).val();
			if(sku.barcode==''){
				alert("条码不能为空!");
				isOk=false;
				return false;
			}
			sku.pic=$(this).find('input[name="skuPic"]');
			if(sku.pic==''){
				alert("sku:{"+sku.barcode+"}图片不能为空!");
				isOk=false;
				return false;
			}
			sku.price=$(this).find("input[name='price']").eq(0).val();
			if(sku.price==''){
				alert("sku:{"+sku.barcode+"}价格不能为空!");
				isOk=false;
				return false;
			}
			sku.number=$(this).find("input[name='number']").eq(0).val();
			if(sku.price==''){
				alert("sku:{"+sku.number+"}初始库存不能为空!");
				isOk=false;
				return false;
			}
			sku.stock=$(this).find("input[name='stock']").eq(0).val();
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
		if(!isOk){
			return;
		}
		goods.skus=skus;
		goods.goodsPic=$('#goodsPic').val();
		var goodsStr=JSON.stringify(goods);
		$.ajax({
			url:'${ctx}/manager/goods/saveGoods.do',
			type:'post',
			data:{'goods':goodsStr},
			success:function(data){
				if('success'==data){
					alert('保存商品成功');
				}else{
					alert('保存商品失败');
				}
			}
		});
		
	}
	
	function updateSkuSelect(obj){
		if($(obj).attr('before')!=''){
			$('.skuCode option').each(function(){
				if($(this).html()==$(obj).attr('before')){
					$(this).val($(obj).val());
					$(this).html($(obj).val());
				}
			})	
		}else{
			$('.skuCode').append('<option value="'+$(obj).val()+'">'+$(obj).val()+'</option>');
		}
		$(obj).attr('before',$(obj).val());
	}
	
</script>
<style type="text/css">
	.manager_button1{
		width: 50px;
		height: 30px;
	}
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
				<td><input type="text" name="goodsCode" id="goodsCode"></td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td><input type="goodsName" id="goodsName"></td>
			</tr>
			<tr>
				<td>商品分类：</td>
				<td>
					<select id="cid" name="cid" onchange="listAttrs()">
						<option value="1">食品</option>
						<option value="2">服装</option>
						<option value="3">家具</option>
						<option value="4">电器</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>商品规格：</>
				<td id="attrs">
					<!-- 根据商品类别动态获取 -->
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
				<td  width="80px" align="center" class="optTh">操作</td>
			</tr>
			<tr class="skuTr" height="25px">
				<td align="center"><input type="text" name="barcode" before="" onblur="updateSkuSelect(this)" value=""><input type="hidden" name="skuPic"></td>
				<td align="center"><input type="text" name="price" value=""></td>
				<td align="center"><input type="text" name="number" onblur="syncStock(this)" value=""></td>
				<td align="center"><input type="text" name="stock" readonly="readonly"></td>
				<td align="center" class="optTr"><input type="button" class="manager_button" onclick="editAttrTableRow(1,this)" value=" 删除 "></td>
			</tr>
		</table>
		<br>
<%-- 		<p>
		  <table cellpadding="0" cellspacing="0">
		  	<tr>
		  		<td style="font-weight:bold">
		  			商品图片信息<br>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>
		  			<div>
		  				<img src="${ctx}/img/goods/default.png" id="upImg" alt="上传图片" width="113px"/>
		  				<input type="hidden" name="goodsPic" id="goodsPic">
		  			</div>
		  		</td>
		  	</tr>
		  </table>
		  	<br>
		 	 <input class="text-input small-input" type="file" id="upFile" />
		</p>
		<br> --%>
		
		<%@ include file="/common/upload/upload.jsp" %>
		<table>
			<tr><!-- -->
				<td><input type="button" class="manager_button"  onclick="submitForm()"value=" 提交 "></td>
			</tr>
		</table>
	</form>
	</div>
</body> 
</html>