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
	.category{
		text-align: center;
		font-weight: bold;
	}
	.category:hover{
		background-color: #E92250;
		cursor: pointer;
	}
	.title_flag{
		display:inline-block;height: 20px;width: 40px;background-color: #EE2457;color: white;text-align:center;line-height: 20px;font-weight: bold;font-size: 13px;
	}
	.title_words{
		display:inline-block;height: 20px;width:74px;color: #666666;text-align:center;line-height: 20px;font-weight: bold;font-size: 14px;
	}
	
	.goods_name{
		color: #333333;
		font-size: 13px;
	}
	.goods_name:hover{
		text-decoration: underline;
	}
</style>

</head>
<body>
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle" style="margin-top: 50px;">
		<div style="width: 400px;margin: 0px auto;height: 40px;">
			<c:if test="${orderSuccess==0}">
				订单生成成功，订单号:${order.orderId}
			</c:if>
			<c:if test="${orderSuccess==1}">
				订单生成失败，请联系管理员!
			</c:if>
		</div>
	</div>
</div>
</body> 
</html>