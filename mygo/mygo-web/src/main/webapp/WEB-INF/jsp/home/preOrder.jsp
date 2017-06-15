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
	input,select{
		width: 175px;
		border: 1px solid #DFDFDF;
	}
	.selectAddr{
		width:80px;
	}
</style>
<script type="text/javascript">

	function makeOrder(){
		$('#addrForm').submit();
	}
</script>
</head>
<body>
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
	<div style="margin: 0px auto;width: 80%">
	<form action="${ctx}/login/order/saveOrder.do" id="addrForm" method="post" enctype="application/x-www-form-urlencoded">
		<table class="editTable">
			<tr>
				<td>${order.addr.province}&nbsp;${order.addr.city}&nbsp;${order.addr.area}&nbsp;
					${order.addr.addr}&nbsp;(${order.addr.receiver}&nbsp;收)&nbsp;${order.addr.mobilphone}
				</td>
			</tr>
			<tr>
				<td colspan="2" style="font-weight:bold"><a href="${ctx}/login/addr/editAddr.do" target="_blank">使用其他地址</a></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td colspan="5" style="font-weight: bold">确认订单信息</td>
			</tr>
			<tr>
				<td width="100px">商品</td>
				<td width="100px">属性</td>
				<td width="100px">单价</td>
				<td width="100px">数量</td>
				<td width="100px">金额</td>
			</tr>
				<tr>
					<td>${order.sku.goodsName}
 						<input type="hidden" name="skuId" value="${order.sku.sid}">
						<input type="hidden" name="addrId" value="${order.addr.uaid}">
						<input type="hidden" name="number" value="${order.number}">
					</td>
					<td>
						<c:forEach items="${order.sku.attrs }" var="attr">
							${attr.attrName}:${attr.attrValue}<br>
						</c:forEach>
					</td>
					<td>${order.sku.price}</td>
					<td>${order.number}</td>
					<td>${order.amount}</td>
				</tr>
				<tr>
					<td colspan="5">
						<a href="javascript:void(0)"  onclick="makeOrder()" class="manager_button" style="font-size: 13px;text-decoration: none">确认订单</a>
					</td>
				</tr>
		</table>
		
	</form>
	
	
</div>
</body> 
</html>