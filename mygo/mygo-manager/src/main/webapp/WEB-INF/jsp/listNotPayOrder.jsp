<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>未支付订单列表</title>
<script type="text/javascript">

	$(function() { 
		$(window).scroll(function() { 
			var top = $(window).scrollTop()+200; 
			var left= $(window).scrollLeft()+420; 
			$("#deleteDescDiv").css({ left:left + "px", top: top + "px" }); 
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
			success:function(data){
				if(data=='success'){
					location.reload();
				}else{
					alert("关闭订单失败，请联系管理员!");
				}
			}
		});
	}
	
	
</script>
</head>
<body>
	<div>
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
			<span style="float: left;margin-left: 5px;">
				管理中心 - 订单列表
			</span>
			<span style="float: right;padding-right: 10px;">
			</span>
		</div>
	<div id="fullbg" style="background-color: gray; left: 0; opacity: 0.5; position: absolute; top: 0; z-index: 3; filter: alpha(opacity = 50); -moz-opacity: 0.5; -khtml-opacity: 0.5;"></div>
	<div style="display:none;position: absolute;border-radius:5px;width: 430px;height: 160px;z-index: 100;top: 200px;left: 35%;background-color: #EFEEF0" id="deleteDescDiv">
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
		<form action="${ctx}/login/order/listNotPayOrder.do" method="post">
			<div class="query_div">
				<span style="display: inline-block;width: 300px">
					订单号：<input class="searchInput" type="text" name="oid" value="${order.oid}">
				</span>
				
				<span style="display: inline-block;width: 300px">
					商品名称：<input class="searchInput" type="text" name="goodsName" value="${order.goodsName}">
				</span>
				<span style="display: inline-block;width: 150px;text-align: center">
					<input type="button" class="searchBtn" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
				</span>
			</div>
		</form>
		<table class="listTable"  cellspacing="1">
				<tr>
					<th width="320">商品信息</th>
					<th width="100">单价</th>
					<th width="80">数量</th>
					<th width="100">金额</th>
					<th width="100">总金额</th>
					<th>发货地址</th>
					<th width="140px">操作</th>
				</tr>
			</table>
			<br>
			<c:forEach items="${pager.result}" var="order" varStatus="status">
				<table  class="listTable" cellspacing="1">
					<tr>
						<th colspan="7" style="text-align: left;">
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
									【已付款】&nbsp;流水号: ${order.payNo }
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
						</th>
					</tr>
					<tr>
						<c:forEach items="${order.detailVos}" var="detail" varStatus="detailstatus">
							<c:if test="${detailstatus.index==0}">
								<td width="320px">
									<a href="${goodsDetail}?gid=${detail.gid }" target="_blank">
										<img src="${detail.sku.skuPic}" width="80px" style="border: 1px solid #ccc; vertical-align: middle;">
									</a>
									<div style="width: 200px; vertical-align: middle; display: inline-block; text-align: left;">
										<div style="width: 190px; margin-left: 5px;">
											<a href="${goodsDetail}?gid=${detail.gid }" target="_blank">
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
								<td width="100px">${detail.orderPrice}</td>
								<td width="100px">${detail.number}</td>
								<td width="100px">${detail.amount}</td>
							</c:if>
						</c:forEach>
						<td rowspan="${fn:length(order.detailVos)}"   width="100px">
							${order.amount}
						</td>
						<td style="padding-left: 10px; padding-right: 10px" rowspan="${fn:length(order.detailVos)}">
							${order.province} ${order.city} ${order.area}
							<br>${order.addr}
						</td>
						<td width="140px" rowspan="${fn:length(order.detailVos)}">
							<c:if test="${order.status==1}">
								<span style="width: 35px;padding: 5px;cursor: pointer;"  onclick="showDesc('${order.oid}')" title="关闭订单">
									<img alt="" width="30px" src="${ctx}/img/closeOrder.png">
								</span>
							</c:if> 
						</td>
					</tr>
					<c:forEach items="${order.detailVos}" var="detail" varStatus="detailstatus">
						<c:if test="${detailstatus.index!=0}">
							<tr>
								<td width="320px">&nbsp;
									<a href="${goodsDetail}?gid=${detail.gid }" target="_blank">
										<img src="${detail.sku.skuPic}" width="80px" style="border: 1px solid #ccc; vertical-align: middle;">
									</a>
									<div style="width: 200px; vertical-align: middle; display: inline-block; text-align: left;">
										<div style="width: 190px; margin-left: 5px;">
											<a href="${goodsDetail}?gid=${detail.gid }" target="_blank">
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
								<td width="100px">${detail.orderPrice}</td>
								<td width="80px">${detail.number}</td>
								<td width="100px">${detail.amount}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<br>
			</c:forEach>
	<br>
		<%@ include file="/common/page.jsp" %>
	</div>
</body> 
</html>