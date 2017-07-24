<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商城首页</title>
<script type="text/javascript">
function checkNumber(obj){
	var $number=$(obj).parent().find('.optNumber');
	var reg = /^\d+$/;
	if(!reg.test($number.val()))
	{
		$number.val(1);
		return false;
	}
	return true;
}
function changeNumber(obj){
	if(!checkNumber(obj)){
		return false;
	}
	var $number=$(obj).parent().find('.optNumber');
	var $price=$(obj).parent().parent().prevAll('.price');
	var $amount=$(obj).parent().parent().nextAll('.amount');
	$amount.html(returnFloat(parseFloat($price.html())*parseInt($number.val())));
	check();
}

function opNumber(type,obj){
	if(!checkNumber(obj)){
		return false;
	}
	var $number=$(obj).parent().find('.optNumber');
	var number=parseInt($number.val());
	if(type=='add'){
		$number.val(number+1);
	}else{
		if(number<=1){
			return false;
		}
		$number.val(number-1);
	}
	var $price=$(obj).parent().parent().prevAll('.price');
	var $amount=$(obj).parent().parent().nextAll('.amount');
	$amount.html(returnFloat(parseFloat($price.html())*parseInt($number.val())));
	check();
	
	
}

function check(){
	$checked=$('.check_goods:checked');
	$('.total_number').html($checked.size());
	var total_amount=0;
	$checked.each(function(){
		total_amount+=parseFloat($(this).parent().parent().nextAll('.amount').html());
	});
	$('.total_amount').html(total_amount.toFixed(2));
}

function allCheck(){
	if($('.allCheck').prop('checked')){
		var total_amount=0;
		$('.check_goods').each(function(){
			$(this).prop('checked',true);
			total_amount+=parseFloat($(this).parent().parent().nextAll('.amount').html());
		});
		$('.total_number').html($('.check_goods').size());
		$('.total_amount').html(total_amount.toFixed(2));
	}else{
		$('.check_goods').each(function(){
			$(this).prop('checked',false);
		});
		$('.total_number').html(0);
		$('.total_amount').html(0.00);
	}
}


function butNow(){
	var html='<input type="hidden" name="type" value="1">';
	var index=0;
	if($('.check_goods:checked').size()<=0){
		alert("请选择需要结算的商品!");
		return;
	}
	$('.check_goods:checked').each(function(){
		var selectSku=$(this).parent().parent().parent().find('.optNumber');
		var skuId=selectSku.attr('skuId');
		var number=selectSku.val();
		var skuStock=selectSku.attr('skuStock');
		if(parseInt(number)<=0){
			alert("购买数量必须大于0！");
			return false;
		}
		if(parseInt(number)>parseInt(skuStock)){
			alert("库存不足！");
			return false;
		} 
		html+='<input type="hidden" name="detailVos['+index+'].sid" id="form_sku_id" value="'+skuId+'"/>'+
			'<input type="hidden" name="detailVos['+index+'].number" id="form_sku_number" value="'+number+'"/>';
		index++;
	});
	$('#myform').html(html);
	//return false;
	$('#myform').submit(); 
}

</script>
<style type="text/css">
	 .optNumber_div{
		 height: 30px;
		 display: inline-block;
		 border: 1px solid #CCCCCC;
	 }
 	.optNumber_add,.optNumber_cut{
 		line-height:28px;
		font-weight:bolder;
		color:#666666;
		text-align: center;
		font-size: 20px;
		text-decoration: none;
		display: inline-block;
		width: 23px;
		vertical-align: middle;
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
	#empty{
	padding: 88px 0 100px 156px;
    background: url('${ctx}/img/cart.png') no-repeat 40px 86px;
    position: relative;
   	width: 80%;
   	margin: 0px auto;
   	margin-top: 30px
	}
	#empty h2 {
    font: 700 14px / 20px arial;
	}
#empty ul {
    margin-top: 12px;
    line-height: 20px;
} ul {
    list-style: none;
    margin: 0;
    padding: 0;
}
.cartTable th{
background-color:#FBFBFB;font-size: 13px;font-weight: normal;
}
.listTable td{
	padding: 0;
}
</style>
</head>
<body  class="_body" style="font-family: sans-serif;">
<div  class="page_body">
	<%@ include file="/common/top1.jsp" %>
	<div class="page_middle" style="border-top: 1px solid #ddd;margin-top: 0px;padding-top: 30px">
	<div style="width: 80%;margin: 0px auto">
			<form action="${ctx}/login/order/savePreOrder.do" method="post" id="myform">
				
			</form>
		<div style="width: 100%;font-size: 30px;font-weight: bold;color: #f40;padding-left: 20px;border-bottom: 2px solid #e6e6e6;">购物车</div>
		<c:choose>
		<c:when test="${cartList==null || empty cartList}">
		<div id="empty">
			<h2>您的购物车还是空的，赶紧行动吧！您可以：</h2>
			<ul style="font-size: 14px">
				<li>看看 <a href="${ctx}/" style="color: #B1B1B1">首页商品</a></li>
				<li>看看 <a href="${ctx}/login/order/listOrder.do" style="color: #B1B1B1">已买到的宝贝</a></li>
			</ul>
		</div>
		</c:when>
		<c:otherwise>
		<table width="100%" class="cartTable" cellspacing="0" cellpadding="0" style="margin-top: 20px;border: 1px solid #dddddd; ">
		<tr height="50px"  >
			<th width="55px" style="padding-left: 10px"  align="left">
				 <input type="checkbox" onclick="allCheck()" class="allCheck" style="width: 15px;height: 15px;">全选
			</th>
			<th width="500px">
				商品信息
			</th>
			<th style="width: 100px;text-align: right;padding: 0 10 0 10">
				单价(元)
			</th>
			<th>
				数量(件)
			</th>
			<th  style="width: 100px;text-align: right;padding: 0 10 0 10">
				金额(元)
			</th>
			<th style="padding-right: 20px;text-align: right;width:200px">
				操作
			</th>
		</tr>
		</table>
		<table  width="100%"  cellspacing="0" cellpadding="0">
		<c:forEach items="${cartList}" var="cart" varStatus="status">
			<tr height="120px">
				<td width="55px" style="padding-left: 10px" align="left" valign="bottom">
					<div style="height: 100px"><input type="checkbox" class="check_goods" onclick="check()" style="width: 15px;height: 15px;"></div>
				</td>
				<td align="center" width="500px" style="text-align: center" valign="middle">
					&nbsp;<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${cart.sku.gid }" target="_blank"><img src="${cart.sku.skuPic}" width="100px" style="border: 1px solid #ccc;vertical-align: middle;"></a>
						<div style="width: 350px;vertical-align: middle;display: inline-block; text-align: left;">
							<div style="width: 340px;margin-left: 5px;">
								<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${cart.sku.gid }" target="_blank">${cart.sku.goodsName}</a>
							</div>
							<div style="color: #9e9e9e;font-size: 12px;width: 340px;margin-left: 5px;margin-top: 10px;">
								<c:forEach items="${cart.sku.attrs }" var="attr">
									${attr.attrName}：${attr.attrValue}&nbsp;
								</c:forEach>
							</div>
							<div style="color: #9e9e9e;font-size: 12px;width: 340px;margin-left: 5px;margin-top: 10px;">
									剩余库存：<font style="color: #666666">${cart.sku.stock}</font>&nbsp;件
							</div>
						</div>
					</a>
				</td>
				<td align="center" class="price" style="width: 100px;text-align: right;padding: 0 10 0 10">
					${cart.sku.price}
				</td>
				<td align="center">
					<div class="optNumber_div">
						<div onclick="opNumber('cut',this);" title="减1" class="optNumber_cut">-</div>
						<input type="text"  onblur="changeNumber(this)"  title="请输入购买量" class="optNumber" skuStock="${cart.sku.stock}" skuId="${cart.sku.sid}" value="${cart.number}" name="optNumber" maxlength="8">
						<div onclick="opNumber('add',this);" title="加1" class="optNumber_add">+</div>
				    </div>
				</td>
				<td align="center" class="amount" style="width: 100px;text-align: right;padding: 0 10 0 10">
					${cart.amount}
				</td>
				<td align="right" style="padding-right: 20px;width:200px">
					<a href="${ctx}/login/cart/removeCart.do?sid=${cart.sid}" style="color: #666">
						<img src="${ctx}/img/closeOrder.png" width="28px" style="vertical-align: middle;" title="删除"></img>
					</a>
				</td>
			</tr>
		</c:forEach>
		<tr height="20px">
		<td colspan="6" style="border-top: 1px dashed #e6e6e6;"></td>
		</tr>
		<tr height="50px;">
			<td colspan="3" style="background-color: #e5e5e5">
				&nbsp;
			</td>
			<td style="background-color: #e5e5e5" align="center">
				已选商品&nbsp;<font style="color: red;font-weight: bold;font-size: 16px" class="total_number">0</font>&nbsp;件
			</td>
			<td style="background-color: #e5e5e5" align="center">
				合计：&nbsp;<font style="color: red;font-weight: bold;font-size: 16px" class="total_amount">0.00</font>
			</td>
			<td style="background-color: #e5e5e5" align="right">	
				<input type="button" class="manager_button" value="结算" onclick="butNow()" style="height: 50px;width: 110px;border-radius:0px;">
			</td>
		</tr>
	</table>
	</c:otherwise>
	</c:choose>
	</div>
	</div>
	<%@ include file="/common/bottom1.jsp" %>
</div>
</body> 
</html>