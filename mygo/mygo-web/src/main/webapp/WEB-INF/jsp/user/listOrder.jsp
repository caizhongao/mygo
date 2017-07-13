<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的订单</title>
<style type="text/css">
	#mytable{
		font-size: 14px;
		margin: 0px auto;
	}
	.queryParam{
	width: 80%;height: 40px;margin: 0px auto;
	font-size: 14px;color: #666666;
	}
	.queryParam input,select{
		width: 150px;
	}
	#mytable td{
		border-right: 1px dashed #F2F2F2;
		border-bottom: 1px dashed #DFDFDF;
		text-align: center;
		height: 110px;
		color: #666666;
		font-size: 14px;
	}
	#mytable th{
		border-right: 1px solid #DFDFDF;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		background-color: #EFEEF0;
		color: #666666;
	}
	
}
</style>
<script type="text/javascript">


$(function() { 
	$(window).scroll(function() { 
		var top = $(window).scrollTop()+200; 
		//var left= $(window).scrollLeft()+30%; 
		$("#deleteDescDiv").css({ /* left:left + "px", */ top: top + "px" }); 
	}); 
}); 


function showDesc(oid){
	var bh = $("body").height(); 
	var bw = $("body").width(); 
	$("#fullbg").css({ 
		height:bh, 
		width:bw, 
		display:"block" 
	}); 
	
	
	$('#oid').val(oid);
	$('#oidTitle').html("订单【"+oid+"】");
	$('#deleteDescDiv').css('display','block');
}

function closeDesc(){
	$('#oid').val('');
	$("#fullbg,#deleteDescDiv").hide(); 
}
function deleteOrder(){
	$.ajax({
		url:"${ctx}/login/order/deleteOrder.do",
		type:'post',
		data:{"oid":$('#oid').val(),"deleteDesc":$('#deleteDesc').val()},
		dataType:'text',
		success:function(data){
			if('success'==data.message){
				location.reload();
			}else{
				alert("关闭订单失败，请联系管理员!");
			}
		}
	});
}
function toRefund(oid){
	$.ajax({
		url:'${ctx}/login/order/toRefund.do',
		data:{'oid':oid},
		type:'post',
		success:function(data){
			if('success'==data.message){
				alert('退款成功!');
				location.reload();
			}else{
				alert("退款失败!");
			}
		}
	})
}
</script>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
	<!-- 遮罩层 -->
				<div id="fullbg" style="background-color:gray; left:0; opacity:0.5; position:absolute; top:0; z-index:3; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity:0.5; "></div> 
		<div style="display:none;position: absolute;border-radius:5px;width: 430px;height: 160px;z-index: 100;top: 200px;left:35%;background-color: #EFEEF0" id="deleteDescDiv">
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
	
		<form action="${ctx}/login/order/listOrder.do" method="post">
		<div class="queryParam">
			<span style="display: inline-block;width: 250px">
				订单号：<input type="text" name="oid" value="${order.oid}">
			</span>
			
			<span style="display: inline-block;width: 250px">
				商品名称：<input type="text" name="goodsName" value="${order.goodsName}">
			</span>
			<span style="display: inline-block;width: 250px">
				订单状态：<select name="payStatus">
					<option value="">所有</option>
					<option value="0" <c:if test="${order.payStatus==0}">selected="selected"</c:if>>未支付</option>
					<option value="1" <c:if test="${order.payStatus==1}">selected="selected"</c:if>>已支付</option>
					<option value="2" <c:if test="${order.payStatus==2}">selected="selected"</c:if>>已退款</option>
				</select>
			</span>
			<span style="display: inline-block;width: 150px;text-align: center">
				<input type="button" class="manager_button" onclick="$('.page_middle form').eq(0).submit()" style="width: 100px" value="查询">
			</span>
		</div>
		</form>
		<table style="width: 80%" id="mytable" cellpadding="0" cellspacing="0">
		<tr>
			<th>
				序号
			</th>
			<th>
				订单号
			</th>
			<th>
				商品信息
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
			<th>
				订单状态
			</th>
			<th>
				支付状态
			</th>
			<th>
				操作
			</th>
		</tr>
		<c:forEach items="${pager.result}" var="order" varStatus="status">
			<tr>
				<td width="50px">
					${status.index+1}
				</td>
				<td width="100px">
					${order.oid}
				</td>
				<td width="300px" align="center">
					&nbsp;<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${order.gid }" target="_blank"><img src="${order.skuPic}" width="80px" style="border: 1px solid #ccc;vertical-align: middle;"></a>
						<div style="width: 200px;vertical-align: middle;display: inline-block; text-align: left;">
							<div style="width: 190px;margin-left: 5px;">
								<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${order.gid }" target="_blank">${order.goodsName}</a>
							</div>
							<div style="color: #9e9e9e;font-size: 12px;width: 190px;margin-left: 5px;margin-top: 10px;">
								<c:forEach items="${order.attrVos }" var="attr">
									${attr.attrName}：${attr.attrValue}&nbsp;
								</c:forEach>
							</div>
							
						</div>
						
					</a>
				</td>
				<td width="100px">
					${order.orderPrice}
				</td>
				<td width="100px">
					${order.number}
				</td>
				<td  width="100px">
					${order.amount}
				</td>
				<td style="padding-left: 10px;padding-right: 10px">
					${order.province} ${order.city} ${order.area}<br>
					${order.addr}
				</td>
				<td width="100px">
					${order.payNo }
				</td>
				<td width="100px">
					<c:if test="${order.status==0}">
						正常
					</c:if>
					<c:if test="${order.status==1}">
						<font title="${order.deleteDesc}">关闭</font>
					</c:if>
					<c:if test="${order.status==2}">
						<font title="${order.deleteDesc}">关闭</font>
					</c:if>
				</td>
				<td width="100px">
					<c:if test="${order.payStatus==0}">
						未支付
					</c:if>
					<c:if test="${order.payStatus==1}">
						已支付
					</c:if>
					<c:if test="${order.payStatus==2}">
						已退款
					</c:if>
				</td>
				<td width="140px">
					<c:if test="${order.payStatus==0}">
						<c:if test="${order.status==0}">
							<a href="${ctx}/login/order/toOrderPayPage.do?oid=${order.oid}" class="manager_button" target="_blank">付款</a>
							<a href="javascript:void(0)" onclick="showDesc(${order.oid})" class="manager_button" target="_blank">关闭</a>
						</c:if>
					</c:if>
					<c:if test="${order.payStatus==1}">
						<a href="javascript:toRefund(${order.oid})" class="manager_button">退款</a>
						<a class="manager_button" style="visibility:hidden">关闭</a>
					</c:if>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	<br>
	<%@ include file="/common/page.jsp" %>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>