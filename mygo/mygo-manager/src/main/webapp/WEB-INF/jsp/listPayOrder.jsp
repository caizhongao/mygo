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
		.queryParam{
	width: 80%;height: 30px;
	margin-top:25px;
	font-size: 14px;color: #666666;
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
</style>
</head>
<body>
	<div style="width:95%;margin: 0px auto;">
		<form action="${ctx}/login/order/listPayOrder.do" method="post">
			<div class="queryParam">
				<span style="display: inline-block;width: 300px">
					订单号：<input class="query_obj" type="text" name="oid" value="${order.oid}">
				</span>
				
				<span style="display: inline-block;width: 300px">
					商品名称：<input class="query_obj" type="text" name="goodsName" value="${order.goodsName}">
				</span>
				<span style="display: inline-block;width: 150px;text-align: center">
					<input type="button" class="manager_button" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
				</span>
			</div>
		</form>
		<table style="width: 95%; border: 1px solid #e8e8e8" class="thtable" cellpadding="0" cellspacing="0">
				<tr>
					<td width="320">商品信息</td>
					<td width="80">单价</td>
					<td width="80">数量</td>
					<td width="100">金额</td>
					<td width="100">总金额</td>
					<td>发货地址</td>
					<!-- <td width="140px">操作</td> -->
				</tr>
			</table>
			<br>
			<c:forEach items="${pager.result}" var="order" varStatus="status">
				<table style="width: 95%; border: 1px solid #daf3ff;"
					class="mytable" cellpadding="0" cellspacing="0">
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
						</td>
					</tr>
					<tr>
						<c:forEach items="${order.detailVos}" var="detail" varStatus="detailstatus">
							<c:if test="${detailstatus.index==0}">
								<td width="320px" align="center">&nbsp;
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
						<%-- <td width="140px" rowspan="${fn:length(order.detailVos)}" style="border-right: none;">
							<c:if test="${order.status==1}">
								<a href="javascript:void(0)" onclick="showDesc('${order.oid}')" class="manager_button" target="_blank">关闭</a>
							</c:if> 
						</td> --%>
					</tr>
					<c:forEach items="${order.detailVos}" var="detail" varStatus="detailstatus">
						<c:if test="${detailstatus.index!=0}">
							<tr>
								<td width="320px" align="center">&nbsp;
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
		<%@ include file="/common/page.jsp" %>
	</div>
</body> 
</html>