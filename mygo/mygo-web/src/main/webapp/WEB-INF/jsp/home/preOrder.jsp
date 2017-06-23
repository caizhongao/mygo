<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		font-size: 14px;
		color: #666666;
	} 
	
		#mytable{
		font-size: 14px;
	}
	.tableData td{
		border-right: 1px solid #F2F2F2;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		color: #666666;
		font-size: 14px;
	}
	.tableData th{
		border-right: 1px solid #DFDFDF;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		background-color: #EFEEF0;
		color: #666666;
	}
	.editTable tr{
		height: 40px;
	}
	.tableData tr{
		height: 40px;
	}

	.selectAddr{
		width:80px;
	}

.icons {
    background: url(${ctx}/img/icons-total.gif) no-repeat;
    display: block;
}
.fl {
    float: left;
}
.icons-address {
    width: 13px;
    height: 15px;
    background-position: -14px -276px;
    margin: 3px 5px 0 0;
}
.icons-phone {
    width: 13px;
    height: 15px;
    background-position: -28px -276px;
    margin: 2px 5px 0 0;
}
.icons-user {
    width: 13px;
    height: 15px;
    background-position: 0 -276px;
    margin: 3px 5px 0 0;
}
.order-address{
    width: 273px;
    height: 155px;
    background: url(${ctx}/img/add-address-bg.gif) no-repeat 0 -155px;
    margin: 10 20px 20px 0;
   	padding-left: 20px;
   	padding-top: 10px;
   	padding-bottom: 10px;
   	padding-right: 10px;
   	font-size: 13px;
}

.pay-tips {
    color: #f36;
    margin-left: 10px;
    font-size: 12px;
    height: 20px;
    line-height: 19px;
    padding-left: 10px;
    padding-right: 10px;
    border: 1px solid #f36;
    display: inline-block;
    vertical-align:middle;
}
.order-address{
    background-position: 0 0;
    color: #333;
}
</style>
<script type="text/javascript">

	function makeOrder(){
		if($('input[name="addrId"]').eq(0).val()==''){
			alert('请选择收获地址！');
			return;
		}
		$('#addrForm').submit();
	}
</script>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<div style="margin: 0px auto;width: 80%">
			<form action="${ctx}/login/order/saveOrder.do" id="addrForm" method="post" enctype="application/x-www-form-urlencoded">
				<span style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px">选择收货地址</span>
				<table class="editTable order-address">
					<c:if test="${order.addr==null }">
						<tr><td>无收获地址&nbsp;
						<a href="${ctx}/login/addr/editAddr.do" target="_blank" style="font-size: 13px">新增地址</a></td></tr>
					</c:if>
					<c:if test="${order.addr!=null }">
					<tr>
						<td>
							<em class="icons icons-user fl"></em>${order.addr.receiver}&nbsp;收
						</td>
					</tr>
					<tr>
						<td>
							<em class="icons icons-address fl"></em>
							${order.addr.province}&nbsp;${order.addr.city}&nbsp;${order.addr.area}&nbsp;
							${order.addr.addr}
						</td>
					</tr>
					<tr>
						<td>
							<em class="icons icons-phone  fl"></em>${order.addr.mobilphone}
							&nbsp;<a href="${ctx}/login/addr/editAddr.do" target="_blank" style="font-size: 13px">修改</a>
						</td>
					</tr>
					</c:if>
				</table>
				<br>
				<span style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px">选择支付方式</span>
				<div style="width: 500px;height: 80px;margin-top: 20px">
					<input type="radio" autocomplete="off" value="2" style="width: 40px;vertical-align:middle" name="choose_pay" data-type="支付宝支付" checked="">
                     <span style="border: #ccc solid 1px;text-align: center;width: 168px;height: 40px;display: inline-block;vertical-align:middle">
                     	 <img src="${ctx}/img/Alipay.jpg" style="margin-top: 7px;">
                     </span>
                     <span class="pay-tips">推荐</span>
				</div>
				<span style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px">确认订单信息</span>
				<table style="width: 1000px;margin-top: 20px" cellpadding="0" cellspacing="0">
					<tr height="50px">
						<td width="300px" align="center" style="border-bottom: 1px dashed #ccc;">商品</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">属性</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">单价</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">数量</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">金额</td>
					</tr>
					<tr height="100px">
						<td align="center" style="border-bottom: 1px dashed #ccc;">
							${order.sku.goodsName}
	 						<input type="hidden" name="skuId" value="${order.sku.sid}">
							<input type="hidden" name="addrId" value="${order.addr.uaid}">
							<input type="hidden" name="number" value="${order.number}">
						</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">
							<c:forEach items="${order.sku.attrs }" var="attr">
								${attr.attrName}:${attr.attrValue}<br>
							</c:forEach>
						</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">${order.sku.price}</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">${order.number}</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">${order.amount}</td>
					</tr>
				</table>
				<div style="width: 1000px;text-align: right;top: 30px;margin-top: 20px">
					<input type="button" value="确认订单" class="manager_button" onclick="makeOrder()">
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>