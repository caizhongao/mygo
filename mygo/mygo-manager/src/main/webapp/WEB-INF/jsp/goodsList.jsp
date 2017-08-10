<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品列表</title>
<script type="text/javascript">
	
</script>
<style type="text/css">

	.listTable tr:HOVER{
		background-color: ;
	}
</style>
<script type="text/javascript">
function updateGoodsStatus(obj,gid,goodsIndex){
	if(obj=="on"){
		if(confirm("确定上架该商品?")){
		$.ajax({
			url:'${ctx}/login/goods/onShelf.do',
			data:{'gid':gid},
			type:'post',
			dataType:"json",
			success:function(data){
				if(data.message=="success"){
					alert('上架成功!');
					location.reload();
				}else{
					alert('上架失败,'+message.data);
				}
			}
		});
		}
	}else{
		if(confirm("确定下架该商品?")){
		$.ajax({
			url:'${ctx}/login/goods/offShelf.do',
			data:{'gid':gid},
			type:'post',
			dataType:"json",
			success:function(data){
				if(data.message=="success"){
					alert('下架成功!');
					location.reload();
				}else{
					alert('下架失败,'+message.data);
				}
			}
		});
		}
	}
}
function toAddGoods(){
	location.href='${ctx}/login/goods/addGoods.do';
}
function editGoods(gid){
	location.href="${ctx}/login/goods/editGoods.do?gid="+gid;
}
</script>
</head>
<body>
	<div style="margin: 0px auto;width: 100%;">
		
		<div style="display: inline-block;border-bottom: 1px solid #dcdcdc;width: 100%;padding-bottom: 7px;margin-bottom: 10px">
			<span style="float: left;margin-left: 5px;">
				管理中心 - 商品列表
			</span>
			<span style="float: right;padding-right: 10px;">
				<input type="button" class="addBtn" onclick="toAddGoods()" style="width: 100px" value="添加商品">
			</span>
		</div>
		<!-- 搜索区域 -->
		<form action="${ctx}/login/goods/listGoods.do" method="post">
			<div class="query_div">
				<span class="query_span">
					商品名称：<input type="text" class="searchInput" name="goodsName" value="${goods.goodsName}">
				</span>
				<span class="query_span">
					商品状态：
					<select name="status" class="searchInput">
						<option value="">所有</option>
						<option value="W" <c:if test='${goods.status=="W"}'>selected="selected"</c:if>>待上架</option>
						<option value="O" <c:if test='${goods.status=="O"}'>selected="selected"</c:if>>已上架</option>
						<option value="F" <c:if test='${goods.status=="F"}'>selected="selected"</c:if>>已下架</option>
					</select>
				</span>
				<span style="display: inline-block;width: 150px;text-align: center">
					<input type="button" class="searchBtn" onclick="$('form').eq(0).submit()" style="width: 100px" value="查询">
				</span>
			</div>
		</form>
		<!-- 搜索区域  end-->
		<table style="width: 100%"  class="listTable" cellspacing="1">
			<tr>
				<th>
					序号
				</th>
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
					价格
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>	
			</tr>
			<c:forEach items="${pager.result}" var="goods" varStatus="status">
				<tr>
					<td width="100px">
						${status.index+1+(pager.pageNum-1)*pager.pageSize}
					</td>
					<td width="200px">
						<img src="${goods.goodsPic}" height="100px">
					</td>
					<td width="150px">
						${goods.goodsCode}
					</td>
					<td style="font-weight: bold;">
						${goods.goodsName }
					</td>
					<td width="100px">
						${goods.categoryName }
					</td>
					<td class="money">
						${goods.price}
					</td>
					<td  width="100px">
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
					<td width="180px">
						<span style="width: 30px;padding: 5px;cursor: pointer;" onclick="editGoods(${goods.gid})" title="编辑">
							<img alt="" width="25px" src="${ctx}/img/edit.png">
						</span>
						<c:if test='${goods.status=="W"||goods.status=="F"}'>
							<span style="width: 30px;padding: 5px;cursor: pointer;" onclick="updateGoodsStatus('on',${goods.gid})" title="上架">
								<img alt="" width="25px" src="${ctx}/img/onshelf.png">
							</span>
						</c:if>
						<c:if test='${goods.status=="O"}'>
							<span style="width: 30px;padding: 5px;cursor: pointer;" onclick="updateGoodsStatus('off',${goods.gid})" title="下架">
								<img alt="" width="25px" src="${ctx}/img/offshelf.png">
							</span>
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