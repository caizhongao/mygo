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
</style>
<script type="text/javascript">
	function gotoPage(cid){
		location.href="${ctx}/unlogin/home/categoryGoods.do?cid="+cid;
	}
	function toIndex(){
		location.href="${ctx}/";
	}
</script>
</head>
<body  class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle" style="margin-top: 50px;">
		<%@ include file="/common/category.jsp" %>
		<table id="newGoods"  width="80%"  cellpadding="0" cellspacing="0" align="center" style="margin-top: 10px">
			<tr>
				<td colspan="4" height="30px">
					<span class="title_flag">NEW</span>&nbsp;&nbsp;
					<span class="title_words">
						<c:forEach items="${categoryList}" var="category">
							<c:if test="${cid==category.cid}">${category.cname}</c:if>
						</c:forEach>
					</span>
				</td>
			</tr>
			<c:if test="${goodsList==null || empty goodsList}">
			
				<tr>
					<td width="40px;">	
						
					</td>
					<td colspan="3" height="30px" style="font-weight: bolder;">
						<br>
						暂无商品
					</td>
				</tr>
			</c:if>
			<c:forEach items="${goodsList}" var="goods" varStatus="status">
					<c:if test="${status.index%3==0}">
						<tr>
					</c:if>
					<td>
						<div style="width: 300px;;height:300px;">
							<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${goods.gid}" target="_blank"  class="goods_name"><img alt="" src="${goods.goodsPic}" style="width:300px;height:300px;"></a>
						</div>
						<div style="width: 300px;text-align: center;margin-top: 10px;">
							<a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${goods.gid}" target="_blank"  class="goods_name">${goods.goodsName }</a>&nbsp;&nbsp;&nbsp;<span style="color: #FF464E">￥${goods.price }</span>
						</div>
					</td>
					<c:if test="${status.index%3==2}">
						</tr>
					</c:if>
			</c:forEach>
		</table>
		<br>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>