<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
	.category{text-align: center;font-weight: bold;}
	.category:hover{background-color: #E92250;cursor: pointer;}
	.active{background-color: #E92250;cursor: pointer;}
	.ct-icon{display: inline-block; width: 24px; height: 24px;vertical-align: middle;position: relative; top: -2px;background: url(${ctx}/img/bg_icon.png) no-repeat 0 0;}
	.ct-icon-1{background-position: -216px 0;} /*食品**/
	.ct-icon-2{background-position: 0 0;} /*服装**/
	.ct-icon-3{background-position: -144px 0;} /*家具**/
	.ct-icon-4{background-position: -264px 0;} /*电器**/
</style>
<script>
	function gotoPage(cid){
		location.href="${ctx}/unlogin/goods/listCategoryGoods.do?cid="+cid;
	}
	function toIndex(){
		location.href="${ctx}/";
	}
 	
	$(function(){
		initCategory();
	});
	
	function initCategory(){
		var cid='${cid}';
		$.ajax({
			url:'${ctx}/unlogin/goods/listCategory.do',
			type:'post',
			dataType:'json',
			success:function(categoryList){
				var categoryHtml='';
				$.each(categoryList,function(index,category){
					var active='';
					if(cid==category.cid){
						active='active';
					}
					categoryHtml+='<td width="20px">&nbsp;</td>'+
									'<td width="80px" class="category '+active+'" onclick="gotoPage('+category.cid+')">'+
									'<i class="ct-icon ct-icon-'+category.cid+'"></i>'+
									category.cname+
								   '</td>';
				});
				categoryHtml+='<td>&nbsp;</td>';
				$('#firstTd').after(categoryHtml);
			}
		});
	}
</script>
<div style="width: 100%;background-color: #02AAF1;">
	<table width="80%"  cellpadding="0" cellspacing="0" align="center">
		<tr style="height: 35px;color: white;">
			<td width="80px" class="category <c:if test="${cid==null}">active</c:if>" onclick="toIndex()"  id="firstTd">首页</td>
			<td>&nbsp;</td>
		</tr>		
	</table>
</div>
