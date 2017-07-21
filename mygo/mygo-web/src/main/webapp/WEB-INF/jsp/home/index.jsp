<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<html>
<head>
<title>漂亮大气响应式电商网站模板HTML下载</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!--end slider -->
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
			   '<a href="${ctx}/unlogin/goods/goodsDetail.do?gid='+goods.gid+'" target="_blank">'+
				'<div class="inner_content clearfix">'+
					'<div class="product_image">'+
						'<img src="'+goods.goodsPic+'" style="width:300px;height:300px;" alt=""/>'+
					'</div>'+
                   ' <div class="sale-box"><span class="on_sale title_shop">新</span></div>'+	
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
			   '<a href="${ctx}/unlogin/goods/goodsDetail.do?gid='+goods.gid+'" target="_blank">'+
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


</script>
</head>
<body>
     <%@ include file="/common/top1.jsp" %>
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
                        <h4 class="title">正品保证</h4>
                        <!-- /Text title -->
                        
                        <!-- Text description -->
                        <p class="description">追求品质</p>
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
                        <h4 class="title">物流快速</h4>
                        <p class="description">三天必达</p>
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
	<%@ include file="/common/bottom1.jsp" %>
</body>
</html>