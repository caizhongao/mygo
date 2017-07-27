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
$(function(){
	listAttrs();})
	function listAttrs(){
		var cid=$('#cid option:selected').val();
		$.ajax({
			url:'${ctx}/login/category/listAttrs.do',
			type:'post',
			dataType:'json',
			data:{'cid':cid},
			success:function(data){
				var attrHtml="";
				if(data!=null&&data.length>0){
					attrHtml+="<td>商品规格：</><td>";
					$.each(data,function(index,content){
						if(index!=0&&index%3==0){
							attrHtml+="<br/>";
						}
						attrHtml+=content.attrName+'<input type="checkbox" style="width:20px;" name="attr" onchange="editAttrTableCell(this,'+content.caid+',\''+content.attrName+'\')" value="'+content.caid+'">';
					});
					attrHtml+="</td>";
				}
				$('#attrs').html(attrHtml);
				initAttrTable();
			}
		});
	}
	
	function editAttrTableCell(obj,caid,attrName){
		if($(obj).prop('checked')){
			$('.optTh').before('<td class="'+caid+'" width="210px" align="center">'+attrName+'</td>');
			$('.optTr').before('<td class="'+caid+' skuAttr"><input type="hidden" name="attrId" value="'+caid+'"><input type="text" class="searchInput" name="attrValue" align="center" value=""></td>');
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
			$('.skuTr :last').find('input[name="price"]').val('');
			$('.skuTr :last').find('input[name="number"]').val('');
			$('.skuTr :last').find('input[name="stock"]').val('');
			$('.skuTr :last').find('input[name="attrValue"]').val('');
			$('.skuTr:last').find('.optTr').html('<img alt="删除" title="删除" style="cursor: pointer;" src="${ctx}/img/delete.png" onclick="editAttrTableRow(1,this)" width="20px">')
			
		}else{
			if($('.skuTr').size()>1){
				$(obj).parent().parent().remove();
			}
		}
		
	}
	
	function initAttrTable(){
		$('#skuTable').html('<tr>'+
								'<td width="210px" align="center">条码</td>'+
								'<td width="210px" align="center">价格</td>'+
								'<td width="210px" align="center">库存</td>'+
								'<td width="210px" align="center">剩余库存</td>'+
								'<td  width="50px" align="center" class="optTh">操作</td>'+
							'</tr>'+
							'<tr class="skuTr" height="25px">'+
								'<td align="center"><input type="text" class="searchInput" name="barcode" before="" onblur="updateBarcode(this)" value=""><input type="hidden" name="skuPic" value=""></td>'+
								'<td align="center"><input type="text" class="searchInput" name="price" value=""></td>'+
								'<td align="center"><input type="text" class="searchInput" name="number" onblur="syncStock(this)" value=""></td>'+
								'<td align="center"><input type="text" class="searchInput" name="stock" value=""  readonly="readonly" ></td>'+
								'<td align="center" class="optTr"><img alt="添加一行" title="添加一行" style="cursor: pointer;" src="${ctx}/img/add.png" onclick="editAttrTableRow(0)" width="20px"></td>'+
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
				alert("条码不能为空!");
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
			url:'${ctx}/login/goods/saveGoods.do',
			type:'post',
			data:{'goods':goodsStr},
			dataType:"json",
			success:function(data){
				if('success'==data.message){
					alert('保存商品成功');
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
				管理中心 - 添加商品
			</span>
		</div>
	<form action="" enctype="multipart/form-data">

		<div style="width: 100%;height: 50px;line-height: 50px;font-weight: bold;">
			【商品信息】
		</div>
		<table>
			<tr>
				<td width="90px">商品编码：</td>
				<td><input type="text" name="goodsCode" id="goodsCode" class="searchInput"></td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td><input type="goodsName" id="goodsName" class="searchInput"></td>
			</tr>
			<tr>
				<td>商品分类：</td>
				<td>
					<select id="cid" name="cid" onchange="listAttrs()" class="searchInput">
						<option value="1">食品</option>
						<option value="2">服装</option>
						<option value="3">家具</option>
						<option value="4">电器</option>
					</select>
				</td>
			</tr>
			<tr id="attrs">
			</tr>
		</table>
		<div style="width: 100%;height: 30px;line-height: 30px;font-weight: bold;margin-top: 20px;">
			【sku信息】
		</div>
<!-- 		<input type="button" class="manager_button" onclick="editAttrTableRow(0)" value=" 添加一行 ">
		<input type="button" class="manager_button" onclick="editAttrTableRow(1,this)" value=" 删除 ">
		<br> -->
		<table id="skuTable" cellpadding="0" cellspacing="0">
			<tr>
				<td width="210px" align="center">条码</td>
				<td width="210px" align="center">价格</td>
				<td width="210px" align="center">总库存</td>
				<td width="210px" align="center">剩余库存</td>
				<td  width="50px" align="center" class="optTh">操作</td>
			</tr>
			<tr class="skuTr" height="25px">
				<td align="center"><input type="text"  class="searchInput" name="barcode" before="" onblur="updateBarcode(this)" value=""><input type="hidden" name="skuPic"></td>
				<td align="center"><input type="text"  class="searchInput" name="price" value=""></td>
				<td align="center"><input type="text"  class="searchInput" name="number" onblur="syncStock(this)" value=""></td>
				<td align="center"><input type="text"  class="searchInput" name="stock" readonly="readonly"></td>
				<td align="center" class="optTr"><img alt="添加一行" title="添加一行" style="cursor: pointer;" src="${ctx}/img/add.png" onclick="editAttrTableRow(0)" width="20px"></td>
			</tr>
		</table>
		<br>
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