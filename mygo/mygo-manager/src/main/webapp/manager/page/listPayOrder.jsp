<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
	#mytable{
		font-size: 14px;
	}
	table td{
		border-right: 1px solid #F2F2F2;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 100px;
		color: #666666;
		font-size: 14px;
	}
	table th{
		border-right: 1px solid #DFDFDF;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		background-color: #EFEEF0;
		color: #666666;
	}
		.queryParam{
	width: 80%;height: 30px;
	margin-top:25px;
	font-size: 14px;color: #666666;
	}
	.queryParam input,select{
		width: 150px;
	}
	
</style>
</head>
<body>
	<div style="width:95%;margin: 0px auto;">
		<form action="${ctx}/manager/order/listPayOrder.do" method="post">
		<div class="queryParam">
			<span style="display: inline-block;width: 250px">
				订单号：<input type="text" name="oid" value="${order.oid}">
			</span>
			
			<span style="display: inline-block;width: 250px">
				商品名称：<input type="text" name="goodsName" value="${order.goodsName}">
			</span>
			<span style="display: inline-block;width: 150px;text-align: center">
				<input type="button" class="manager_button" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
			</span>
		</div>
		</form>
	<table style="width: 95%" cellpadding="0" cellspacing="0">
		<tr>
			<th>
				订单号
			</th>
			<th>
				商品名称
			</th>
			<th>
				单价
			</th>
			<th>
				数量
			</th>
			
			<th>
				总金额
			</th>
			<th>
				发货地址
			</th>
			<th>
				支付流水号
			</th>
		</tr>
		<c:forEach items="${pager.result}" var="order">
			<tr>
				<td>
					${order.oid}
				</td>
				<td>
					${order.goodsName }
				</td>
				<td>
					${order.orderPrice}
				</td>
				<td>
					${order.number}
				</td>
				<td>
					${order.amount}
				</td>
				<td>
					${order.province} ${order.city} ${order.area}<br>
					${order.addr}
				</td>
				<td>
					${order.payNo }
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<br>
		<%@ include file="/common/page.jsp" %>
	</div>
</body> 
</html>