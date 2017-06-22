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
		location.href="${ctx}/unlogin/home/categoryGoods.do?cid="+cid;
	}
	function toIndex(){
		location.href="${ctx}/";
	}
</script>
<div style="height: 40px;position:absolute;bottom:0;color: #c6c6c6;background-color: #f5f5f5;width: 100%">
	<table width="100%"  cellpadding="0" cellspacing="0" align="center"  style="height: 40px;color: #c6c6c6;background-color: #f5f5f5;">
		<tr>
			<td  style="border-left: 1px solid white;font-size: 14px" align="center" >
				Copyright © 2016 -2017 mygo.com All Rights Reserved
			</td>
		</tr>		
	</table>
</div>
