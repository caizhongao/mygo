<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商城首页</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
	#mytable{
		font-size: 14px;
	}
	.title_flag{
		display:inline-block;height: 20px;width: 40px;background-color: #EE2457;color: white;text-align:center;line-height: 20px;font-weight: bold;font-size: 13px;
	}
	.title_words{
		display:inline-block;height: 20px;width:auto;color: #666666;text-align:center;line-height: 20px;font-weight: bold;font-size: 14px;
	}
	.goods_name{
		color: #333333;
		font-size: 13px;
	}
	.goods_name:hover{
		text-decoration: underline;
	}
	.goods_img_div{width: 300px;height:300px;margin-top:10px}
	.goods_name_price{width: 300px;text-align: center;margin-top: 10px;font-family: Arial;}
	.goods_price{color:#FF464E;font-size:30px;}
	.goods_price em{font-size:14px;font-style: normal;font-family: "微软雅黑","verdana";}
</style>
</head>
<body  class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle" style="margin-top: 50px;">
		<%@ include file="/common/search/scrollSearch.jsp" %>
		<%@ include file="/common/category.jsp" %>
		<table id="newGoods"  width="80%"  cellpadding="0" cellspacing="0" align="center" style="margin-top: 10px">
			<c:choose>
				<c:when test="${pager.result==null || empty pager.result}">
					<tr>
						<td width="40px;">
							
						</td>
						<td colspan="6" height="30px" style="font-weight: bolder;">
							<br>
							暂无商品
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" height="30px">
							<span class="title_flag">NEW</span>&nbsp;&nbsp;
							<span class="title_words">
								<c:forEach items="${categoryList}" var="category">
									<c:if test="${cid==category.cid}">${category.cname}</c:if>
								</c:forEach>
							</span>
						</td>
					</tr>
					<c:forEach items="${pager.result}" var="goods" varStatus="status">
						<c:set var="num" value="${(status.index+1)%4}"></c:set>
						<c:if test="${status.index%4==0}">
							<tr>
						</c:if>
								<td width="310px">
									<div class="goods_img_div">
										<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${goods.gid }" target="_blank"  class="goods_name">
											<img alt="" src="${goods.goodsPic}" style="width:300px;height:300px;">
										</a>
									</div>
									<div class="goods_name_price">
										<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${goods.gid}" target="_blank"  class="goods_name">
											${goods.goodsName}
										</a>&nbsp;&nbsp;&nbsp;<span class="goods_price"><em>￥</em>${goods.price }</span>
									</div>
								</td>
						<c:if test="${status.index%4!=3 }">
							<td>&nbsp</td>
						</c:if>
						<c:if test="${status.index%4==3 }">
							</tr>
						</c:if>
					</c:forEach>
					<c:forEach begin="${num }" var="i" end="4">
						<td width="310px">&nbsp</td>
						<c:if test="${i%4!=3 }">
							<td>&nbsp</td>
						</c:if>
						<c:if test="${i%4==3 }">
							</tr>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<br>
		<%@ include file="/common/scrollPage.jsp" %>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>