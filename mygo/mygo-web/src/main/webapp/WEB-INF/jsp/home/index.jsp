<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<html>
<head>
<title>漂亮大气响应式电商网站模板HTML下载</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/common/home/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="${ctx}/common/home/css/form.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<!-- start menu -->
<link href="${ctx}/common/home/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/common/home/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<!--start slider -->
    <link rel="stylesheet" href="${ctx}/common/home/css/fwslider.css" media="all">
    <script src="${ctx}/common/home/js/jquery-ui.min.js"></script>
    <script src="${ctx}/common/home/js/css3-mediaqueries.js"></script>
    <script src="${ctx}/common/home/js/fwslider.js"></script>
<!--end slider -->
<script src="${ctx}/common/home/js/jquery.easydropdown.js"></script>
<style type="text/css">
	.title_flag{
		display:inline-block;height: 20px;width: 40px;background-color: #EE2457;color: white;text-align:center;line-height: 20px;font-weight: bold;font-size: 15px;
	}
	.title_words{
		display:inline-block;height: 20px;width:74px;color: #777;text-align:center;line-height: 20px;font-weight: bold;font-size: 18px;
	}
	</style>
<script type="text/javascript">
$(function(){
	initGoodsList();
	initCategory();
})
function initGoodsList(){
	//推荐商品
	$.ajax({
		url:'${ctx}/unlogin/goods/listNewGoods.do',
		type:'post',
		dataType:'json',
		success:function(goodsList){
			var goodsHtml='';
			var trGoodsNum=0;
			//一列4个商品
			$.each(goodsList,function(index,goods){
				if(index+1>9){
					return false;
				}
				trGoodsNum=(index+1)%3;
				goodsHtml+='<div class="col_1_of_3 span_1_of_3">'+ 
			   '<a href="single.html">'+
				'<div class="inner_content clearfix">'+
					'<div class="product_image">'+
						'<img src="'+goods.goodsPic+'" style="width:300px;height:300px;" alt=""/>'+
					'</div>'+
                   ' <div class="sale-box"><span class="on_sale title_shop">New</span></div>'+	
                    '<div class="price">'+
					   '<div class="cart-left">'+
							'<p class="title" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;" title="'+goods.goodsName+'">'+goods.goodsName+'</p>'+
							'<div class="price1">'+
							  '<span class="actual">￥'+goods.price+'</span>'+
							'</div>'+
						'</div>'+
						'<div class="cart-right"> </div>'+
						'<div class="clear"></div>'+
					 '</div>'+				
                   '</div>'+
                 '</a>'+
				'</div>';
				if(trGoodsNum==0){
					goodsHtml+='<div class="clear"></div>';
					$('.top-box').eq((index+1)/3-1).append(goodsHtml);
					goodsHtml='';
				}
			});
		}
	});
	
	$.ajax({
		url:'${ctx}/unlogin/goods/listHotGoods.do',
		type:'post',
		dataType:'json',
		success:function(goodsList){
			var goodsHtml='';
			var trGoodsNum=0;
			//一列4个商品
			$.each(goodsList,function(index,goods){
				if(index+1>9){
					return false;
				}
				trGoodsNum=(index+1)%3;
				goodsHtml+='<div class="col_1_of_3 span_1_of_3">'+ 
			   '<a href="single.html">'+
				'<div class="inner_content clearfix">'+
					'<div class="product_image">'+
						'<img src="'+goods.goodsPic+'" style="width:300px;height:300px;" alt=""/>'+
					'</div>'+
                   ' <div class="sale-box"><span class="on_sale title_shop">New</span></div>'+	
                    '<div class="price">'+
					   '<div class="cart-left">'+
							'<p class="title" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;" title="'+goods.goodsName+'">'+goods.goodsName+'</p>'+
							'<div class="price1">'+
							  '<span class="actual">￥'+goods.price+'</span>'+
							'</div>'+
						'</div>'+
						'<div class="cart-right"> </div>'+
						'<div class="clear"></div>'+
					 '</div>'+				
                   '</div>'+
                 '</a>'+
				'</div>';
				if(trGoodsNum==0){
					goodsHtml+='<div class="clear"></div>';
					$('.top-box').eq((index+1)/3-1+3).append(goodsHtml);
					goodsHtml='';
				}
			});
		}
	});
}

function initCategory(){
	var cid='${cid}';
	$.ajax({
		url:'${ctx}/unlogin/goods/listCategory.do',
		type:'post',
		dataType:'json',
		success:function(categoryList){
			var categoryHtml='<li class="active grid" onclick="toIndex()">首页</li>';
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
</head>
<body>
     <%@ include file="/common/top.jsp" %>
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
  <!-- start slider -->
    <div id="fwslider">
        <div class="slider_container">
            <div class="slide"> 
                <!-- Slide image -->
                    <img src="${ctx}/common/home/images/banner.jpg" alt=""/>
                <!-- /Slide image -->
                <!-- Texts container -->
                <div class="slide_content">
                    <div class="slide_content_wrap">
                        <!-- Text title -->
                        <h4 class="title">Aluminium Club</h4>
                        <!-- /Text title -->
                        
                        <!-- Text description -->
                        <p class="description">Experiance ray ban</p>
                        <!-- /Text description -->
                    </div>
                </div>
                 <!-- /Texts container -->
            </div>
            <!-- /Duplicate to create more slides -->
            <div class="slide">
                <img src="${ctx}/common/home/images/banner1.jpg" alt=""/>
                <div class="slide_content">
                    <div class="slide_content_wrap">
                        <h4 class="title">consectetuer adipiscing </h4>
                        <p class="description">diam nonummy nibh euismod</p>
                    </div>
                </div>
            </div>
            <!--/slide -->
        </div>
        <div class="timers"></div>
        <div class="slidePrev"><span></span></div>
        <div class="slideNext"><span></span></div>
    </div>
    <!--/slider -->
<div class="main">
	<div class="wrap">
		<div class="section group">
		  <div class="cont span_2_of_3">
		  	<span class="title_flag">NEW</span>
			<span class="title_words">新品上架</span>
			<div class="top-box">
			</div>	
			<div class="top-box">
			</div>	
			<div class="top-box">
			</div>
			<span class="title_flag">NEW</span>
			<span class="title_words">热销商品</span>
		  <div class="top-box">
			</div>
		  <div class="top-box">
			</div>
		  <div class="top-box">
			</div>	
			<span class="title_flag">NEW</span>
			<span class="title_words">向您推荐</span>
		    <div class="top-box">
			</div>
			<div class="top-box">
			</div>
			<div class="top-box">
			</div>		 						 			    
		  </div>
			<div class="rsidebar span_1_of_left">
				<div class="top-border"> </div>
				 <div class="border">
	             <link href="${ctx}/common/home/css/default.css" rel="stylesheet" type="text/css" media="all" />
	             <link href="${ctx}/common/home/css/nivo-slider.css" rel="stylesheet" type="text/css" media="all" />
				  <script src="${ctx}/common/home/js/jquery.nivo.slider.js"></script>
				    <script type="text/javascript">
				    $(window).load(function() {
				        $('#slider').nivoSlider();
				    });
				    </script>
		    <div class="slider-wrapper theme-default">
              <div id="slider" class="nivoSlider">
                <img src="${ctx}/common/home/images/t-img1.jpg"  alt="" />
               	<img src="${ctx}/common/home/images/t-img2.jpg"  alt="" />
                <img src="${ctx}/common/home/images/t-img3.jpg"  alt="" />
              </div>
             </div>
              <div class="btn"><a href="single.html">Check it Out</a></div>
             </div>
	    </div>
	   <div class="clear"></div>
	</div>
	</div>
	</div>
   <div class="footer">
		<div class="footer-top">
			<div class="wrap">
			  <div class="section group example">
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
					  <li><img src="${ctx}/common/home/images/2.png"><span class="f-text">Free Shipping on orders over $ 100</span><div class="clear"></div></li>
					</ul>
				</div>
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
					  <li><img src="${ctx}/common/home/images/3.png"><span class="f-text">Call us! toll free-222-555-6666 </span><div class="clear"></div></li>
					</ul>
				</div>
				<div class="clear"></div>
		      </div>
			</div>
		</div>
		<div class="footer-middle">
			<div class="wrap">
			 <div class="section group example">
			  <div class="col_1_of_f_1 span_1_of_f_1">
				 <div class="section group example">
				   <div class="col_1_of_f_2 span_1_of_f_2">
				      <h3>Facebook</h3>
						<script>(function(d, s, id) {
						  var js, fjs = d.getElementsByTagName(s)[0];
						  if (d.getElementById(id)) return;
						  js = d.createElement(s); js.id = id;
						  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
						  fjs.parentNode.insertBefore(js, fjs);
						}(document, 'script', 'facebook-jssdk'));</script>
						<div class="like_box">	
							<div class="fb-like-box" data-href="http://www.17scuai.com/" data-colorscheme="light" data-show-faces="true" data-header="true" data-stream="false" data-show-border="true"></div>
						</div>
 				  </div>
				  <div class="col_1_of_f_2 span_1_of_f_2">
						<h3>From Twitter</h3>
				       <div class="recent-tweet">
							<div class="recent-tweet-icon">
								<span> </span>
							</div>
							<div class="recent-tweet-info">
								<p>Ds which don't look even slightly believable. If you are <a href="#">going to use nibh euismod</a> tincidunt ut laoreet adipisicing</p>
							</div>
							<div class="clear"> </div>
					   </div>
					   <div class="recent-tweet">
							<div class="recent-tweet-icon">
								<span> </span>
							</div>
							<div class="recent-tweet-info">
								<p>Ds which don't look even slightly believable. If you are <a href="#">going to use nibh euismod</a> tincidunt ut laoreet adipisicing</p>
							</div>
							<div class="clear"> </div>
					  </div>
				</div>
				<div class="clear"></div>
		      </div>
 			 </div>
			 <div class="col_1_of_f_1 span_1_of_f_1">
			   <div class="section group example">
				 <div class="col_1_of_f_2 span_1_of_f_2">
				    <h3>Information</h3>
						<ul class="f-list1">
						    <li><a href="#">Duis autem vel eum iriure </a></li>
				            <li><a href="#">anteposuerit litterarum formas </a></li>
				            <li><a href="#">Tduis dolore te feugait nulla</a></li>
				             <li><a href="#">Duis autem vel eum iriure </a></li>
				            <li><a href="#">anteposuerit litterarum formas </a></li>
				            <li><a href="#">Tduis dolore te feugait nulla</a></li>
			         	</ul>
 				 </div>
				 <div class="col_1_of_f_2 span_1_of_f_2">
				   <h3>Contact us</h3>
						<div class="company_address">
					                <p>500 Lorem Ipsum Dolor Sit,</p>
							   		<p>22-56-2-9 Sit Amet, Lorem,</p>
							   		<p>USA</p>
					   		<p>Phone:(00) 222 666 444</p>
					   		<p>Fax: (000) 000 00 00 0</p>
					 	 	<p>Email: <span>mail[at]leoshop.com</span></p>
					   		
					   </div>
					   <div class="social-media">
						     <ul>
						        <li> <span class="simptip-position-bottom simptip-movable" data-tooltip="Google"><a href="#" target="_blank"> </a></span></li>
						        <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Linked in"><a href="#" target="_blank"> </a> </span></li>
						        <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Rss"><a href="#" target="_blank"> </a></span></li>
						        <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Facebook"><a href="#" target="_blank"> </a></span></li>
						    </ul>
					   </div>
				</div>
				<div class="clear"></div>
		    </div>
		   </div>
		  <div class="clear"></div>
		    </div>
		  </div>
		</div>
		<div class="footer-bottom">
			<div class="wrap">
	             <div class="copy">
			        <p>Copyright &copy; 2014.Company name All rights reserved. <a target="_blank" href="http://www.17sucai.com/">17素材</a></p>
		         </div>
				<div class="f-list2">
				 <ul>
					<li class="active"><a href="about.html">About Us</a></li> |
					<li><a href="delivery.html">Delivery & Returns</a></li> |
					<li><a href="delivery.html">Terms & Conditions</a></li> |
					<li><a href="contact.html">Contact Us</a></li> 
				 </ul>
			    </div>
			    <div class="clear"></div>
		      </div>
	     </div>
	</div>
</body>
</html>