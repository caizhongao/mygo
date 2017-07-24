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
	.title_flag{
		display:inline-block;height: 20px;width: 40px;background-color: #EE2457;color: white;text-align:center;line-height: 20px;font-weight: bold;font-size: 13px;
	}
	.title_words{
		display:inline-block;height: 20px;width:auto;color: #666666;text-align:center;line-height: 20px;font-weight: bold;font-size: 14px;
	}
	.goods_name{
		color: #333333;
		font-size: 13px;
	}
	.goods_name:hover{
		text-decoration: underline;
	}
	.goods_img_div{width: 300px;height:300px;margin-top:10px}
	.goods_name_price{width: 300px;text-align: center;margin-top: 10px;font-family: Arial;}
	.goods_price{color:#FF464E;font-size:30px;}
	.goods_price em{font-size:14px;font-style: normal;font-family: "微软雅黑","verdana";}
</style>
</head>
<body  class="_body">
<div  class="page_body">
	<%@ include file="/common/top1.jsp" %>
	<div class="page_middle" style="border-top: 1px solid #ddd;margin-top: 0px;padding-top: 50px">
			<c:choose>
				<c:when test="${pager.result==null || empty pager.result}">
					<div style="width: 100%;text-align: center;font-weight: bold">没搜索到结果</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${pager.result}" var="goods" varStatus="status">
						<c:set var="num" value="${(status.index+1)%4}"></c:set>
						<c:if test="${num==1}">
							<div class="top-box" style="width: 80%;margin: 0px auto">
						</c:if>
						<div class="col_1_of_3 span_1_of_3" style="width: 23%">
					  	  <a href="${ctx}/unlogin/goods/goodsDetail.do?gid=${goods.gid }">
							 <div class="inner_content clearfix">
								<div class="product_image">
									<img src="${goods.goodsPic}" alt="" style="width:300px;height:300px;" />
								</div>
				                <div class="price">
								   <div class="cart-left">
										<p class="title" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">${goods.goodsName}</p>
										<div class="price1">
										  <span class="actual">￥${goods.price }</span>
										</div>
									</div>
									<div class="cart-right"> </div>
									<div class="clear"></div>
								 </div>				
			                  </div>
			               </a>
						</div>
						<c:if test="${num==0}">
							<div class="clear"></div>
							</div>	
						</c:if>
					</c:forEach>
					<c:if test="${num!=0 }">
						<div class="clear"></div>
						</div>
					</c:if>
					<br>
					<%@ include file="/common/page.jsp" %>
				</c:otherwise>
			</c:choose>
	</div>
	<%@ include file="/common/bottom1.jsp" %>
</div>
</body> 
</html>