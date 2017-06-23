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
<script type="text/javascript">

	$(function() { 
		$(window).scroll(function() { 
			var top = $(window).scrollTop()+200; 
			var left= $(window).scrollLeft()+420; 
			$("#deleteDescDiv").css({ left:left + "px", top: top + "px" }); 
		}); 
	}); 
	
	
	function showDesc(oid){
		$('#oid').val(oid);
		$('#oidTitle').html("订单【"+oid+"】");
		$('#deleteDescDiv').css('display','block');
	}
	
	function closeDesc(){
		$('#oid').val('');
		$('#deleteDescDiv').css('display','none');
	}
	function deleteOrder(){
		$.ajax({
			url:"${ctx}/manager/order/deleteOrder.do",
			type:'post',
			data:{"oid":$('#oid').val(),"deleteDesc":$('#deleteDesc').val()},
			success:function(){
				location.reload();
			}
		});
	}
	
	
</script>
</head>
<body>
	<div style="width:95%;margin: 0px auto;">
	

<div style="height:1500px">
	<div style="display:none;position: absolute;border-radius:5px;width: 430px;height: 160px;margin: 0px auto;z-index: 100;top: 200px;left: 420px;background-color: #EFEEF0" id="deleteDescDiv">
		<div onclick="closeDesc()" style="width: 400px;height: 20px;margin: 0px auto;line-height: 25px;color: #666666;font-size: 20px;font-weight: bold;text-align: right;cursor: pointer;">
			×
		</div>
		<div style="width: 400px;height: 20px;margin: 0px auto;line-height: 20px;color: #666666;font-size: 13px;font-weight: bold;">
			<font style="color: red" id="oidTitle"></font>关闭原由：
		</div>
		<div style="width: 400px;height: 90px;margin: 0px auto">
			<textarea rows="5" id="deleteDesc" cols="54" style="border-radius:3px;"></textarea>
			<input type="hidden" name="oid" id="oid">
		</div>
		<div style="width: 400px;height: 30px;margin: 0px auto;text-align: right">
			<a href="javascript:deleteOrder()" class="manager_button">提交</a>
		</div>
	</div>
		<form action="${ctx}/manager/order/listNotPayOrder.do" method="post">
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
		<tr style="font-size: 14px;">
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
				状态
			</th>
			<th>
				操作
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
				<td title="${order.deleteDesc}">
					<c:if test="${order.status==0 }">
						有效
					</c:if>
					<c:if test="${order.status==1 }">
						无效
					</c:if>
				</td>
				<td style="width: 180px;">
					<c:if test="${order.status==1}">
						无
					</c:if>
					<c:if test="${order.status==0}">
						<a onclick="showDesc(${order.oid})" class="manager_button">关闭</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<br>
		<%@ include file="/common/page.jsp" %>
	</div>
</body> 
</html>