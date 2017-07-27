<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品详情</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
	.goodsDetailTable td{
		vertical-align: middle!important;
	}
	.goods_name{
		color: #333333;
		font-size: 13px;
	}
	.goods_name:hover{
		text-decoration: underline;
	}
	.attrObj{
		display: inline-block;
		border: 1px solid #cccccc;
		padding: 4 10 4 10 ;
		font-size: 14px;
		color: #3C3C3C;/* font-weight: bold; */
		cursor: pointer;
	}
	.attrObj_disable{
		background-color:#F8F8F8!important;
		cursor:auto!important;
		color:#EEEEEE!important;
		border: 1px dashed #EEEEEE!important;
	}
	.page_middle table tr{
		min-height: 50px;
	}
	
	 .optNumber_div{
		 height: 30px;
		 display: inline-block;
		 border: 1px solid #CCCCCC;
	 }
 	.optNumber_add,.optNumber_cut{
		font-weight:bolder;
		color:#666666;
		text-align: center;
		font-size: 18px;
		text-decoration: none;
		display: inline-block;
		width: 23px;
		vertical-align: middle;
		line-height:28px;
		height: 28px;
	}
	.optNumber_add:HOVER,.optNumber_cut:HOVER {
		color: red;
		text-decoration: none;
		cursor: pointer;
	}
	.optNumber{
		width: 50px;
		text-align: center;
		border: 1px solid #CCCCCC;
		height: 28px;
		border-bottom:none;
		border-top:none;
		vertical-align: middle;
	}
	.skuPic{
		border: 2px solid #fff;
	}
	.skuPic:HOVER{
		cursor: pointer;
	}
	
	.skuPicSelect{
		border: 2px solid #f40;
	}
}
</style>
<script type="text/javascript">
	function butNow(){
		var sid=0;
		if($('.attrTr').size()<=0){
			sid=$('input[name="skuId"]').eq(0).val();
		}else{
			if(attrs.length<$('.attrTr').size()){
				alert("请选择完整的规格！");
				return;
			}
			sid=getSku(attrs);
		}
		if(sid==''){
			alert("请选择完整的规格!");
			return;
		}
		var number=$("input[name='optNumber']").val();
		if(parseInt(number)<=0){
			alert("购买数量必须大于0！");
			return false;
		}
		var realStock=$('#realStock').html();
		if(parseInt(number)>parseInt(realStock)){
			alert("库存不足！");
			return false;
		}
		//location.href="${ctx}/login/order/toMakeOrderPage.do?detailVos[0].number="+number+"&detailVos[0].skuId="+sid;
 		$('#form_sku_id').val(sid);
		$('#form_sku_number').val(number);
		$('#myform').submit(); 
	}
	
	function addCart(){
		var sid=0;
		if($('.attrTr').size()<=0){
			sid=$('input[name="skuId"]').eq(0).val();
		}else{
			if(attrs.length<$('.attrTr').size()){
				alert("请选择完整的规格！");
				return;
			}
			sid=getSku(attrs);
		}
		if(sid==''){
			alert("请选择完整的规格!");
			return;
		}
		var number=$("input[name='optNumber']").val();
		if(parseInt(number)<=0){
			alert("购买数量必须大于0！");
			return false;
		}
		var realStock=$('#realStock').html();
		if(parseInt(number)>parseInt(realStock)){
			alert("库存不足！");
			return false;
		}
		$.ajax({
			url:"${ctx}/login/cart/addCart.do",
			data:{"sid":sid,"number":number},
			type:"post",
			dataType:'json',
			success:function(data){
				if(data.message=='success'){
					alert('添加购物车成功!');
				}else if(data.message=='forbidden'){
					location.href=data.data;
				}else{
					alert('添加购物车失败!');
				}
			}
		});
	}
	//根据已选的规格，更新其他规格的状态
	function updateAttrObj(attrtmps){
		$('.attrObj').each(function(){
			var isExist=false;
			for(var i=0;i<attrtmps.length;i++){
				if(attrtmps[i].attrId==$(this).attr('attrId')){
					isExist=true;
					break;
				}
			}
			if(!isExist){
				var tmpObj=new Object();
				tmpObj.attrId=$(this).attr('attrId');
				tmpObj.attrValue=$(this).attr('attrValue');
				var result=attrtmps.slice(0);
				result[result.length]=tmpObj;
				if(getSku(result)==""){
					$(this).addClass("attrObj_disable");
				}/* else{
					$(this).removeClass("attrObj_disable");
				} */
			}
			
		});
	}
	//全组合出所有情况来更新规格的状态
	//递归全排列算法，index代表从第几个元素开始全排列，attrtmps代表index之前已经排列好的组合
	function Combination(index,attrtmps){
		for(;index<attrs.length;index++){
			var result=attrtmps.slice(attrtmps.length);
			result[result.length]=attrs[index];
			updateAttrObj(result);
			Combination(index+1, result);
		}
	}
	
	
	var attrs=[];//当前选中的 attr数组
	
	//选中规格事件
	function selectAttr(thisobj){
		if($(thisobj).attr("class").indexOf("attrObj_disable")>=0){
			return;
		}
		//初始化本行的attr选中状态
		$(thisobj).parent().find(".attrObj").css('border',"1px solid #cccccc");
		$(thisobj).parent().find(".attrObj").css('background-color',"");
		//清空所有的无效状态
		$('.attrObj').each(function(){
			$(this).removeClass("attrObj_disable");
		});
		var index=0;
		var isExist=false;
		var attrNow=new Object();
		//验证attrs数组中是否已经存在该attr
		for(var i=0;i<attrs.length;i++){
			if(attrs[i].attrId==$(thisobj).attr('attrId')){
				if(attrs[i].attrValue==$(thisobj).attr('attrValue')){
					isExist=true;
				}
				break;
			}
			index++;
		}
		if(isExist){//如果已经存在，则代表此次点击是取消选中，删除attrs数组中的该元素
			attrs.splice(index,1); 
		}else{//如果不存在，则添加该元素，并且改变样式
			//更新点击的div样式为选中状态
			$(thisobj).css('border',"2px solid #C40000");
			$(thisobj).css('background-color',"");
			//将选中的div中的attr信息加入到attrs数组中
			attrNow.attrId=$(thisobj).attr('attrId');
			attrNow.attrValue=$(thisobj).attr('attrValue');
			attrs[index]=attrNow;
		}
		//重新排列更新attr有效状态
		Combination(0,[]);
		updateRealStock();
	}
	//更新sku库存显示，sku图片显示，sku价格显示
	function updateRealStock(){
		var sid=0;
		if($('.attrTr').size()<=0){
			sid=$('input[name="skuId"]').eq(0).val();
		}else{
			if(attrs.length<$('.attrTr').size()){
				$('#realStock').html('${goods.stock}');
				return;
			}
			sid=getSku(attrs);
		}
		if(sid==''){
			$('#realStock').html('${goods.stock}');
			return;
		}
		$('#realStock').html($('#skuStock'+sid).val());
		$('#goodsPrice').html($('#skuPrice'+sid).val());
		showSkuPic(sid);
	}
	
	
	
	//根据选中的规格找到对应的sku
	function getSku(attrs){
		var hasSku=false;
		var skuId="";
		$('.skuInfo').each(function(){
			for(var i=0;i<attrs.length;i++){
				var value=$(this).find('input[name="attrValue_'+attrs[i].attrId+'"]').val();
				if(value==attrs[i].attrValue){
					hasSku=true;
					continue;
				}else{
					hasSku=false;
					break;
				}
			}
			if(hasSku){
				skuId=$(this).find('input[name="skuId"]').val();
				return false;
			}
		});
		return skuId;
	}
	
	
	function checkNumber(){
		var reg = /^\d+$/;
		if(!reg.test($('#number').val()))
		{
			$('#number').val(1);
			return false;
		}
		return true;
	}
	
	function opNumber(type){
		if(!checkNumber()){
			return false;
		}
		var number=parseInt($('#number').val());
		if(type=='add'){
			$('#number').val(number+1);
		}else{
			if(number<=1){
				return false;
			}
			$('#number').val(number-1);
		}
		
	}
	
	function showSkuPic(skuId){
		$('.skuPic').removeClass("skuPicSelect");
		$('#skuPic'+skuId).addClass("skuPicSelect");
		$('#goodsDetailPic').attr('src',$('#skuPic'+skuId).attr('src'));
	}
	
</script>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top1.jsp" %>
	<div class="page_middle" style="border-top: 1px solid #ddd;margin-top: 0px;padding-top: 50px">
		<div style="width: 80%;margin: 0px auto">
			<c:forEach items="${goods.skus}" var="sku">
				<div class="skuInfo">
					<input type="hidden" name="skuId" value="${sku.sid}">
					<input type="hidden" id="skuStock${sku.sid}" name="skuStock" value="${sku.stock}">
					<input type="hidden" id="skuPrice${sku.sid}" name="skuPrice" value="${sku.price}">
					<c:forEach items="${sku.attrs}" var="attr">
						<input type="hidden" name="attrValue_${attr.attrId}" value="${attr.attrValue}">
					</c:forEach>
				</div>
			</c:forEach>
			<form action="${ctx}/login/order/savePreOrder.do" method="post" id="myform">
				<input type="hidden" name="detailVos[0].sid" id="form_sku_id" value=""/>
				<input type="hidden" name="detailVos[0].number" id="form_sku_number" value=""/>
				<input type="hidden" name="type" id="form_type" value="0"/>
			</form>
			<table width="1100px" class="goodsDetailTable" cellpadding="0" cellspacing="0"  style="font-size: 14px;color:#74777b;height: 401px;font-family: Arial">
				<tr>
					<td rowspan="${fn:length(goods.skus[0].attrs)+5}" width="500px">
						<div style="width: 400px;border: 1px solid #E8E8E8;padding: 1px">
							<!-- margin-top: 1px;margin-left: 1px;margin-bottom: 1px -->
							<img src="${goods.goodsPic}" width="400px" height="400px" id="goodsDetailPic" alt="商品详情图" style="">
						</div>
						<ul style="width: 402px;margin-top: 2px;height: 100px">
							<c:forEach items="${goods.skus}" var="sku">
								<li style="display: inline-block;">
									<img src="${sku.skuPic}" width="70px" id="skuPic${sku.sid}" alt="商品详情图" onmouseover="showSkuPic(${sku.sid})"  class="skuPic">
								</li>
							</c:forEach>
						</ul>
					</td>
					<td colspan="2" style="font-weight: bold;font-size: 14px;color: #666666;">
						${goods.goodsName}
					</td>
				</tr>
				<tr>
					<td width="70px">
						价格
					</td>
					<td style="color: #f40;font-size: 24px;font-style: normal;" >
						<font style="font-family: arial">¥</font>
						<font id="goodsPrice" style="font-weight: 700;font-family: verdana,arial;">${goods.price}</font>
					</td>
				</tr>
				<tr>
					<td>
						销量
					</td>
					<td style="font-weight: bold;font-size: 16px;">
						${goods.sales}
					</td>
				</tr>
				<c:forEach items="${attrs}" var="attr">
					<tr class="attrTr" height="60px;">
						<td valign="center">
							${attr.key}
						</td>
						<td>
							<ul>
								<c:forEach items="${attr.value}" var="attrVal">
									<li class="attrObj" attrId="${attrVal.value}" attrValue="${attrVal.key}" onclick="selectAttr(this)">
										${attrVal.key}
									</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td>
						数量
					</td>
					<td>
						<div class="optNumber_div">
							<div onclick="opNumber('cut');" title="减1" class="optNumber_cut">-</div>
							<input type="text"  onblur="checkNumber()"  title="请输入购买量" class="optNumber" id="number" name="optNumber" value="1" maxlength="8">
							<div onclick="opNumber('add');" title="加1" class="optNumber_add">+</div>
				    	</div>件&nbsp;(库存&nbsp;<span id="realStock" style="display: inline-block;color: #666666">${goods.stock}</span>&nbsp;件)
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" onclick="butNow()" class="buy_button" value="立即购买">
					&nbsp;
						<input type="button" onclick="addCart()" class="cart_button" value="加入购物车">
					</td>
				</tr>
			</table>
			<div style="border: 1px solid #cccccc;width: 80%;height:auto;margin-top: 20px;" class="mall_item_info">
				<%@ include file="/common/goods-desc.jsp" %>
			</div>
		</div>
	</div>
	<%@ include file="/common/bottom1.jsp" %>
</div>
</body> 
</html>