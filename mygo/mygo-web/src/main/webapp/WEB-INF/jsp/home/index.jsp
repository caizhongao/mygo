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
	.active{
		background-color: #E92250;
		cursor: pointer;
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
<script type="text/javascript">
	$(function(){
		initGoodsList();
	})
	function gotoPage(cid){
		location.href="${ctx}/unlogin/home/categoryGoods.do?cid="+cid;
	}
	function initGoodsList(){
		$.ajax({
			url:'${ctx}/unlogin/goods/listNewGoods.do',
			type:'post',
			dataType:'json',
			success:function(goodsList){
				var goodsHtml="";
				$.each(goodsList,function(index,goods){
					if(index%3==0){
						goodsHtml+="<tr>";
					}
					goodsHtml+='<td>'+
									'<div style="width: 300px;height:300px;">'+
									'<a href="${ctx}/unlogin/goods/goodsDetail.do?gid='+goods.gid+'" target="_blank"  class="goods_name">'+'<img alt="" src="'+goods.goodsPic+'" style="width:300px;height:300px;">'+'</a>'+
									'</div>'+
									'<div style="width: 300px;text-align: center;margin-top: 10px;">'+
										'<a href="${ctx}/unlogin/goods/goodsDetail.do?gid='+goods.gid+'" target="_blank"  class="goods_name">'+goods.goodsName+'</a>&nbsp;&nbsp;&nbsp;<span style="color: #FF464E">￥'+goods.price+'</span>'+
									'</div>'+
								'</td>'
					if(index%3==2){
						goodsHtml+="</tr>";
					}
				});
				$('#newGoods').append(goodsHtml);
			}
		});
		
		$.ajax({
			url:'${ctx}/unlogin/goods/listHotGoods.do',
			type:'post',
			dataType:'json',
			success:function(goodsList){
				var goodsHtml="";
				$.each(goodsList,function(index,goods){
					if(index%3==0){
						goodsHtml+="<tr>";
					}
					goodsHtml+='<td>'+
									'<div style="width: 300px;">'+
										'<img alt="" src="'+goods.goodsPic+'" width="300px">'+
									'</div>'+
									'<div style="width: 300px;text-align: center;margin-top: 10px;">'+
										'<a href="${ctx}/unlogin/goods/goodsDetail.do?gid='+goods.gid+'" target="_blank" class="goods_name">'+goods.goodsName+'</a>&nbsp;&nbsp;&nbsp;<span style="color: #FF464E">￥'+goods.price+'</span>'+
									'</div>'+
								'</td>'
					if(index%3==2){
						goodsHtml+="</tr>";
					}
				});
				$('#hotGoods').append(goodsHtml);
			}
		});
	}
</script>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<table width="80%"  cellpadding="0" cellspacing="0" align="center">
			<tr style="background-color: #02AAF1;height: 30px;color: white;">
				<td width="40px" style="border-left: 1px solid white">&nbsp;</td>
				<td width="70px" class="category active">首页</td>
				<c:forEach items="${categoryList}" var="category">
					<td width="20px">&nbsp;</td>
					<td width="70px" class="category" onclick="gotoPage(${category.cid})">
						${category.cname}
					</td>
				</c:forEach>
				<td>&nbsp;</td>
			</tr>		
		</table>
		<table id="newGoods"  width="80%"  cellpadding="0" cellspacing="0" align="center" style="margin-top: 10px">
			<tr>
				<td colspan="4" height="30px">
					<span class="title_flag">NEW</span>
					<span class="title_words">新品上架</span>
				</td>
			</tr>
		</table>
		<br>
		<table id="hotGoods"  width="80%"  cellpadding="0" cellspacing="0" align="center" style="margin-top: 10px">
			<tr>
				<td colspan="4" height="30px">
					<span class="title_flag">HOT</span>
					<span class="title_words">最热商品</span>
				</td>
			</tr>
		</table>
	</div>
</div>
</body> 
</html>