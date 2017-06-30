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
<script type="text/javascript">
	function gotoPage(cid){
		location.href="${ctx}/unlogin/home/categoryGoods.do?cid="+cid;
	}
	function toIndex(){
		location.href="${ctx}/";
	}
	$(function(){
		initGoodsList();
	});
	
	function initGoodsList(){
		$.ajax({
			url:"${ctx}/unlogin/goods/listCategoryGoods.do?cid=${cid}",
			type:'post',
			dataType:'json',
			success:function(goodsList){
				$('#newGoods').append(makeGoodsHtml(goodsList));
			}
		});
	}
	
</script>
</head>
<body  class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle" style="margin-top: 50px;">
	<%@ include file="/common/search/search.jsp" %>
		<%@ include file="/common/category.jsp" %>
		<table id="newGoods"  width="80%"  cellpadding="0" cellspacing="0" align="center" style="margin-top: 10px">
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
		</table>
		<br>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>