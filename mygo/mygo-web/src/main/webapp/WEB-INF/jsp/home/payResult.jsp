<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付结果</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
.show {
	clear: left;
	display: block;
}
.content {
	margin-top: 5px;
}

.content dt {
	width: 100px;
	display: inline-block;
	float: left;
	margin-left: 20px;
	color: #666;
	font-size: 13px;
	margin-top: 8px;
}

.content dd {
	margin-left: 120px;
	margin-bottom: 5px;
}

.content dd input {
	width: 85%;
	height: 28px;
	border: 0;
	-webkit-border-radius: 0;
	-webkit-appearance: none;
}

.one_line {
	display: block;
	height: 1px;
	border: 0;
	border-top: 1px solid #eeeeee;
	width: 100%;
	margin-left: 20px;
}
.new-btn-login-sp {
	padding: 1px;
	display: inline-block;
	width: 75%;
}

.new-btn-login {
	background-color: #02aaf1;
	color: #FFFFFF;
	font-weight: bold;
	border: none;
	width: 100%;
	height: 30px;
	border-radius: 5px;
	font-size: 16px;
}

.note-help {
	color: #999999;
	font-size: 12px;
	line-height: 130%;
	margin-top: 5px;
	width: 100%;
	display: block;
}


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
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top1.jsp" %>
	<div class="page_middle" >
		<div style="width: 400px;margin-left:20%;height: 40px;">
			<c:if test="${payResult=='success'}">
			<div id="body1" class="show" name="divcontent">
				<dl class="content">
					<dt>商户订单号 ：</dt>
					<dd>
						<input id="WIDout_trade_no" name="WIDout_trade_no" value="${oid}" readonly="readonly"/>
					</dd>
					<hr class="one_line">
					<dt>付款金额 ：</dt>
					<dd>
						<input id="WIDtotal_amount" name="WIDtotal_amount" value="${amount}" readonly="readonly"/>
					</dd>
					<hr class="one_line">
					<dt>支付流水 ：</dt>
					<dd>
						<input id="WIDtotal_amount" name="WIDtotal_amount" value="${payNo}" readonly="readonly"/>
					</dd>
					<hr class="one_line">
					<dt>付款结果：</dt>
					<dd>
						<input id="WIDbody" name="WIDbody" value="付款成功"  readonly="readonly"/>
					</dd>
					<hr class="one_line">
					<dt></dt>
					<dd id="btn-dd" style="margin-left: 80px;">
						<span class="new-btn-login-sp">
							<button class="new-btn-login"
								style="text-align: center;" onclick="location.href='${ctx}'">返回首页</button>
						</span> <span class="note-help"></span>
					</dd>
				</dl>
			</div>
			</c:if>
			<c:if test="${payResult=='failed'}">
				<div id="body1" class="show" name="divcontent" style="padding-top:20px;padding-left:50px;color: #666666;font-size: 16px;font-weight: bold;border: 1px solid #F6C8B5;height: 70px;width: 1000px;">
					<div style="border-radius:20px;width: 40px;height: 40px;background-color: red;color: white;font-size: 30px;font-weight: bold;text-align: center;line-height: 40px;display: inline-block;">×</div>
					付款失败，可登陆支付宝查看交易记录。
				</div>
			</c:if>
		</div>
	</div>
	<%@ include file="/common/bottom1.jsp" %>
</div>
</body> 
</html>