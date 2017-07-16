<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的订单</title>
<style type="text/css">
.queryParam {
	width: 80%;
	height: 40px;
	margin: 0px auto;
	font-size: 14px;
	color: #666666;
}

.queryParam input, select {
	width: 150px;
}

.mytable td {
	border-right: 1px solid #daf3ff;
	border-top: 1px solid #daf3ff;
	text-align: center;
	height: 95px;
	color: #666666;
	font-size: 13px;
}

.thtable td {
	text-align: center;
	height: 30px;
	background-color: #f5f5f5;
	color: #666666;
	font-size: 13px;
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
		dataType:'json',
		success:function(data){
			if('success'==data.message){
				alert("操作成功!");
				location.reload();
			}else if('forbidden'==data.message){
				location.href=data.data;
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
	<div class="page_body">
		<%@ include file="/common/top.jsp"%>
		<div class="page_middle">
			<!-- 遮罩层 -->
			<div id="fullbg"
				style="background-color: gray; left: 0; opacity: 0.5; position: absolute; top: 0; z-index: 3; filter: alpha(opacity = 50); -moz-opacity: 0.5; -khtml-opacity: 0.5;"></div>
			<div
				style="display: none; position: absolute; border-radius: 5px; width: 430px; height: 160px; z-index: 100; top: 200px; left: 35%; background-color: #EFEEF0"
				id="deleteDescDiv">
				<div onclick="closeDesc()"
					style="width: 400px; height: 20px; margin: 0px auto; line-height: 25px; color: #666666; font-size: 20px; font-weight: bold; text-align: right; cursor: pointer;">
					×</div>
				<div
					style="width: 400px; height: 20px; margin: 0px auto; line-height: 20px; color: #666666; font-size: 13px; font-weight: bold;">
					<font style="color: red" id="oidTitle"></font>关闭原由：
				</div>
				<div style="width: 400px; height: 90px; margin: 0px auto">
					<textarea rows="5" id="deleteDesc" cols="54"
						style="border-radius: 3px;"></textarea>
					<input type="hidden" name="oid" id="oid">
				</div>
				<div
					style="width: 400px; height: 30px; margin: 0px auto; text-align: right">
					<a href="javascript:deleteOrder()" class="manager_button">提交</a>
				</div>
			</div>

			<form action="${ctx}/login/order/listOrder.do" method="post">
				<div class="queryParam">
					<span style="display: inline-block; width: 250px"> 
						订单号：<input type="text" name="oid" value="${order.oid}">
					</span> 
					<span style="display: inline-block; width: 250px"> 
						商品名称：<input type="text" name="goodsName" value="${order.goodsName}">
					</span> 
					<span style="display: inline-block; width: 250px"> 
						订单状态：<select name="status">
									<option value="">所有</option>
									<option value="0" <c:if test="${order.status==1}">selected="selected"</c:if>>未支付</option>
									<option value="1" <c:if test="${order.status==2}">selected="selected"</c:if>>已支付</option>
									<option value="2" <c:if test="${order.status==3}">selected="selected"</c:if>>已退款</option>
								</select>
					</span> 
					<span style="display: inline-block; width: 150px; text-align: center">
						<input type="button" class="manager_button" onclick="$('.page_middle form').eq(0).submit()" style="width: 100px" value="查询">
					</span>
				</div>
			</form>
			<table style="width: 80%; border: 1px solid #e8e8e8" class="thtable"
				cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td width="320">商品信息</td>
					<td width="80">单价</td>
					<td width="80">数量</td>
					<td width="100">金额</td>
					<td width="100">总金额</td>
					<td>发货地址</td>
					<td width="140px">操作</td>
				</tr>
			</table>
			<br>
			<c:forEach items="${pager.result}" var="order" varStatus="status">
				<table style="width: 80%; border: 1px solid #daf3ff;"
					class="mytable" cellpadding="0" cellspacing="0"  align="center">
					<tr>
						<td colspan="7" style="background-color: #eaf8ff; height: 30px; text-align: left;border-top: none;border-right: none;">
							<font style="font-weight: bold; margin-left: 20px;">
							<jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>
							<jsp:setProperty property="time" name="dateObject" value="${order.createTime*1000}"/>
							<fmt:formatDate value="${dateObject}" pattern="yyyy-MM-dd"/></font>&nbsp;
							订单号: ${order.oid}&nbsp; 
							<c:choose>
								<c:when test="${order.status==1}">
									【待付款】
								</c:when>
								<c:when test="${order.status==2}">
									【已付款:${order.payNo }】
								</c:when>
								<c:when test="${order.status==3}">
									【已退款】
								</c:when>
								<c:when test="${order.status==4}">
									【用户关闭】
								</c:when>
								<c:when test="${order.status==5}">
									【系统关闭】
								</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<c:forEach items="${order.detailVos}" var="detail" varStatus="detailstatus">
							<c:if test="${detailstatus.index==0}">
								<td width="320px" align="center">&nbsp;
									<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${detail.gid }" target="_blank">
										<img src="${detail.sku.skuPic}" width="80px" style="border: 1px solid #ccc; vertical-align: middle;">
									</a>
									<div style="width: 200px; vertical-align: middle; display: inline-block; text-align: left;">
										<div style="width: 190px; margin-left: 5px;">
											<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${detail.gid }" target="_blank">
												${detail.goodsName}
											</a>
										</div>
										<div style="color: #9e9e9e; font-size: 12px; width: 190px; margin-left: 5px; margin-top: 10px;">
											<c:forEach items="${detail.sku.attrs }" var="attr">
												${attr.attrName}：${attr.attrValue}&nbsp;
											</c:forEach>
										</div>
									</div>
								</td>
								<td width="80px">${detail.orderPrice}</td>
								<td width="80px">${detail.number}</td>
								<td width="100px">${detail.amount}</td>
							</c:if>
						</c:forEach>
						<td rowspan="${fn:length(order.detailVos)}" width="100px">
							${order.amount}</td>
						<td style="padding-left: 10px; padding-right: 10px"
							rowspan="${fn:length(order.detailVos)}">${order.province}
							${order.city} ${order.area}<br> ${order.addr}
						</td>
						<td width="140px" rowspan="${fn:length(order.detailVos)}" style="border-right: none;">
							<c:if test="${order.status==1}">
								<a href="${ctx}/login/order/toOrderPayPage.do?oid=${order.oid}" class="manager_button" target="_blank">付款</a>
								<a href="javascript:void(0)" onclick="showDesc('${order.oid}')" class="manager_button" target="_blank">关闭</a>
							</c:if> 
							<c:if test="${order.status==2}">
								<a href="javascript:toRefund(${order.oid})" class="manager_button">退款</a>
								<a class="manager_button" style="visibility: hidden">&nbsp;</a>
							</c:if>
						</td>
					</tr>
					<c:forEach items="${order.detailVos}" var="detail" varStatus="detailstatus">
						<c:if test="${detailstatus.index!=0}">
							<tr>
								<td width="320px" align="center">&nbsp;
									<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${detail.gid }" target="_blank">
										<img src="${detail.sku.skuPic}" width="80px" style="border: 1px solid #ccc; vertical-align: middle;">
									</a>
									<div style="width: 200px; vertical-align: middle; display: inline-block; text-align: left;">
										<div style="width: 190px; margin-left: 5px;">
											<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${detail.gid }" target="_blank">
												${detail.goodsName}
											</a>
										</div>
										<div style="color: #9e9e9e; font-size: 12px; width: 190px; margin-left: 5px; margin-top: 10px;">
											<c:forEach items="${detail.sku.attrs }" var="attr">
												${attr.attrName}：${attr.attrValue}&nbsp;
											</c:forEach>
										</div>
									</div>
								</td>
								<td width="80px">${detail.orderPrice}</td>
								<td width="80px">${detail.number}</td>
								<td width="100px">${detail.amount}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<br>
			</c:forEach>
			<br>
			<%@ include file="/common/page.jsp"%>
		</div>
		<%@ include file="/common/bottom.jsp"%>
	</div>
</body>
</html>