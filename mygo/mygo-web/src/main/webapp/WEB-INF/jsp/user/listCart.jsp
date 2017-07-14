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
	var reg = /\d+$/;
	if(!reg.test($number.val()))
	{
		$(obj).val(1);
		return false;
	}
	if(parseInt($number.val())<0){
		$(obj).val(1);
		return false;
	}
	return true;
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


</script>
<style type="text/css">
	#mytable th{
	 border-bottom: 1px dashed #e6e6e6;
	 }
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
</style>
</head>
<body  class="_body" style="font-family: sans-serif;">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
	<%@ include file="/common/search/search.jsp" %>
	<div style="width: 80%;margin: 0px auto">
		<div style="width: 90%;font-size: 30px;font-weight: bold;color: #f40;padding-left: 20px;margin-top: 20px;border-bottom: 2px solid #e6e6e6;">购物车</div>
		<table  id="mytable" width="90%" cellpadding="0" cellspacing="0" style="margin-top: 20px">
		<tr height="50px">
			<th align="left">
				<input type="checkbox" onclick="allCheck()" class="allCheck" style="width: 15px;height: 15px;">全选
			</th>
			<th>
				商品信息
			</th>
			<th>
				单价(元)
			</th>
			<th>
				数量(件)
			</th>
			<th>
				金额(元)
			</th>
			<th>
				操作
			</th>
		</tr>
		<c:forEach items="${cartList}" var="cart" varStatus="status">
			<tr height="120px">
				<td width="55px" align="left" valign="bottom">
					<div style="height: 100px"><input type="checkbox" class="check_goods" onclick="check()" style="width: 15px;height: 15px;"></div>
				</td>
				<td align="center" width="600px">
					&nbsp;<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${cart.sku.gid }" target="_blank"><img src="${cart.sku.skuPic}" width="100px" style="border: 1px solid #ccc;vertical-align: middle;"></a>
						<div style="width: 480px;vertical-align: middle;display: inline-block; text-align: left;">
							<div style="width: 470px;margin-left: 5px;">
								<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${cart.sku.gid }" target="_blank">${cart.sku.goodsName}</a>
							</div>
							<div style="color: #9e9e9e;font-size: 12px;width: 470px;margin-left: 5px;margin-top: 10px;">
								<c:forEach items="${cart.sku.attrs }" var="attr">
									${attr.attrName}：${attr.attrValue}&nbsp;
								</c:forEach>
							</div>
						</div>
					</a>
				</td>
				<td align="center">
					${cart.sku.price}
				</td>
				<td align="center" width="300px">
					<div class="optNumber_div">
						<div onclick="opNumber('cut',this);" title="减1" class="optNumber_cut">-</div>
						<input type="text"  onblur="checkNumber(this)"  title="请输入购买量" class="optNumber" value="${cart.number}" name="optNumber" maxlength="8">
						<div onclick="opNumber('add',this);" title="加1" class="optNumber_add">+</div>
				    </div>
				</td>
				<td align="center" class="amount" width="250px">
					${cart.amount}
				</td>
				<td width="140px" align="center">
					删除
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
				<input type="button" class="manager_button" value="结算" style="height: 50px;width: 110px;border-radius:0px;">
			</td>
		</tr>
	</table>
	</div>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>