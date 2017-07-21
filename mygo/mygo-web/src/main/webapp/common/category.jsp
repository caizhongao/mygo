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
	
/* 	function initCategory(){
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
	} */
	
	function initCategory(){
		var cid='${cid}';
		$.ajax({
			url:'${ctx}/unlogin/goods/listCategory.do',
			type:'post',
			dataType:'json',
			success:function(categoryList){
				var categoryHtml='<li class="active grid"><a href="index.html" onclick="toIndex()">首页</a></li>';
				$.each(categoryList,function(index,category){
				/* 	var active='';
					if(cid==category.cid){
						active='active';
					} */
					categoryHtml+='<li class="active grid"><a href="index.html" onclick="gotoPage('+category.cid+')"">'+category.cname+'</a></li>';
				});
				$('#category').html(categoryHtml);
			}
		});
	}
</script>
	<div class="header-bottom">
	    <div class="wrap">
			<div class="header-bottom-left">
				<div class="logo">
					<a href="index.html"><img src="${ctx}/common/home/images/logo.png" alt=""/></a>
				</div>
				<div class="menu">
	            <ul class="megamenu skyblue" id="category">
				</ul>
			</div>
		</div>
	   <div class="header-bottom-right">
         <div class="search">	  
				<input type="text" name="s" class="textbox" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}">
				<input type="submit" value="Subscribe" id="submit" name="submit">
				<div id="response"> </div>
		 </div>
	  <div class="tag-list">
	    <ul class="icon1 sub-icon1 profile_img">
			<li><a class="active-icon c1" href="#"> </a>
				<ul class="sub-icon1 list">
					<li><h3>sed diam nonummy</h3><a href=""></a></li>
					<li><p>Lorem ipsum dolor sit amet, consectetuer  <a href="">adipiscing elit, sed diam</a></p></li>
				</ul>
			</li>
		</ul>
		<ul class="icon1 sub-icon1 profile_img">
			<li><a class="active-icon c2" href="#"> </a>
				<ul class="sub-icon1 list">
					<li><h3>No Products</h3><a href=""></a></li>
					<li><p>Lorem ipsum dolor sit amet, consectetuer  <a href="">adipiscing elit, sed diam</a></p></li>
				</ul>
			</li>
		</ul>
	    <ul class="last"><li><a href="#">Cart(0)</a></li></ul>
	  </div>
    </div>
     <div class="clear"></div>
     </div>
	</div>
