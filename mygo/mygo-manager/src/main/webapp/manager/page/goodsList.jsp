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
	.queryParam{
	width: 80%;height: 30px;
	margin-top:25px;
	font-size: 14px;color: #666666;
	}
	.queryParam input,select{
		width: 150px;
	}
</style>
<script type="text/javascript">
function updateGoodsStatus(obj,gid,goodsIndex){
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

</script>
</head>
<body>
	<div style="width:95%;margin: 0px auto;">
		<form action="${ctx}/manager/goods/listGoods.do" method="post">
		<div class="queryParam">
			<span style="display: inline-block;width: 250px">
				商品名称：<input type="text" name="goodsName" value="${goods.goodsName}">
			</span>
			<span style="display: inline-block;width: 250px">
				商品状态：<select name="status">
					<option value="">所有</option>
					<option value="W" <c:if test='${goods.status=="W"}'>selected="selected"</c:if>>待上架</option>
					<option value="O" <c:if test='${goods.status=="O"}'>selected="selected"</c:if>>已上架</option>
					<option value="F" <c:if test='${goods.status=="F"}'>selected="selected"</c:if>>已下架</option>
				</select>
			</span>
			<span style="display: inline-block;width: 150px;text-align: center">
				<input type="button" class="manager_button" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
			</span>
		</div>
		</form>
	<table style="width: 95%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="7" style="border-right: none;text-align: right;height: 50px;"> 
				<a href="${ctx}/manager/goods/addGoods.do" class="manager_button">添加商品</a>
			</td>
		</tr>
		<tr>
			<th>
				商品图片
			</th>
			<th>
				商品编码
			</th>
			<th>
				商品名称
			</th>
			<th>
				商品分类
			</th>
			<th>
				商品价格
			</th>
			<th>
				状态
			</th>
			<th>
				操作
			</th>	
		</tr>
		<c:forEach items="${pager.result}" var="goods">
			<tr>
				<td>
					<img src="${goods.goodsPic}" width="100px">
				</td>
				<td>
					${goods.goodsCode}
				</td>
				<td>
					${goods.goodsName }
				</td>
				<td>
					${goods.categoryName }
				</td>
				<td>
					${goods.price}
				</td>
				<td>
					<c:choose>
						<c:when test='${goods.status=="W"}'>
							待上架
						</c:when>
						<c:when test='${goods.status=="O"}'>
							已上架
						</c:when>
						<c:otherwise>
							已下架
						</c:otherwise>
					</c:choose>
				</td>
				<td style="width: 180px;">
					<a href="${ctx}/manager/goods/editGoods.do?gid=${goods.gid}" class="manager_button">编辑</a>
					<c:if test='${goods.status=="W"}'>
						<a href="javascript:updateGoodsStatus('on',${goods.gid});" class="manager_button">上架</a>
					</c:if>
					<c:if test='${goods.status=="O"}'>
						<a href="javascript:updateGoodsStatus('off',${goods.gid});" class="manager_button">下架</a>
					</c:if>
					<c:if test='${goods.status=="F"}'>
						<a href="javascript:updateGoodsStatus('on',${goods.gid});" class="manager_button">上架</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>
		<br>
		<%@ include file="/common/page.jsp" %>
	</div>
</body> 
</html>