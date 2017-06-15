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
	
}
</style>
<script type="text/javascript">
function updateGoodsStatus(obj,gid){
	if(obj=="on"){
		$.ajax({
			url:'${ctx}/manager/goods/onShelf.do',
			data:{'gid':gid},
			type:'post',
			success:function(data){
				if(data=="success"){
					alert('上架成功!');
					location.reload();
				}else{
					alert('上架失败!');
				}
			}
		});
	}else{
		$.ajax({
			url:'${ctx}/manager/goods/offShelf.do',
			data:{'gid':gid},
			type:'post',
			success:function(data){
				if(data=="success"){
					alert('下架成功!');
					location.reload();
				}else{
					alert('下架失败!');
				}
			}
		});
	}
}
function addF(obj) {
	alert(1);
	var $this = $(obj);
	addIframe($this);
}
/*添加iframe*/
function addIframe(cur){
	var $this = cur;
	var h = $this.attr("href"),
		m = $this.data("index"),
		label = $this.find("span").text(),
		isHas = false;
	if (h == "" || $.trim(h).length == 0) {
		return false;
	}
	
	var fullWidth = $(window).width();
	if(fullWidth >= 750){
		$(".layout-side").show();
	}else{
		$(".layout-side").hide();
	}
	
	$(".content-tab").each(function() {
		if ($(this).data("id") == h) {
			if (!$(this).hasClass("active")) {
				$(this).addClass("active").siblings(".content-tab").removeClass("active");
				addTab(this);
			}
			isHas = true;
		}
	});
	if(isHas){
		$(".body-iframe").each(function() {
			if ($(this).data("id") == h) {
				$(this).show().siblings(".body-iframe").hide();
			}
		});
	}
	if (!isHas) {
		var tab = "<a href='javascript:;' class='content-tab active' data-id='"+h+"'>"+ label +" <i class='icon-font'>&#xe617;</i></a>";
		$(".content-tab").removeClass("active");
		$(".tab-nav-content").append(tab);
		var iframe = "<iframe class='body-iframe' name='iframe"+ m +"' width='100%' height='99%' src='"+ h +"' frameborder='0' data-id='"+ h +"' seamless></iframe>";
		$(".layout-main-body").find("iframe.body-iframe").hide().parents(".layout-main-body").append(iframe);
		addTab($(".content-tab.active"));
	}
	return false;
}


/*添加tab*/
function addTab(cur) {
	var prev_all = tabWidth($(cur).prevAll()),
		next_all = tabWidth($(cur).nextAll());
	var other_width =tabWidth($(".layout-main-tab").children().not(".tab-nav"));
	var navWidth = $(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
	var hidewidth = 0;
	if ($(".tab-nav-content").width() < navWidth) {
		hidewidth = 0
	} else {
		if (next_all <= (navWidth - $(cur).outerWidth(true) - $(cur).next().outerWidth(true))) {
			if ((navWidth - $(cur).next().outerWidth(true)) > next_all) {
				hidewidth = prev_all;
				var m = cur;
				while ((hidewidth - $(m).outerWidth()) > ($(".tab-nav-content").outerWidth() - navWidth)) {
					hidewidth -= $(m).prev().outerWidth();
					m = $(m).prev()
				}
			}
		} else {
			if (prev_all > (navWidth - $(cur).outerWidth(true) - $(cur).prev().outerWidth(true))) {
				hidewidth = prev_all - $(cur).prev().outerWidth(true)
			}
		}
	}
	$(".tab-nav-content").animate({
		marginLeft: 0 - hidewidth + "px"
	},
	"fast")
}

/*获取宽度*/
function tabWidth(tabarr) {
	var allwidth = 0;
	$(tabarr).each(function() {
		allwidth += $(this).outerWidth(true)
	});
	return allwidth;
}

</script>
</head>
<body>
	<div style="width:95%;margin: 0px auto;">
	<table style="width: 95%" cellpadding="0" cellspacing="0">
		<tr>
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
		</tr>
		<c:forEach items="${orderList}" var="order">
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
			</tr>
		</c:forEach>
		
	</table>
	</div>
</body> 
</html>