<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生成订单</title>
<style type="text/css">
	table{
		font-size: 14px;
		color: #666666;
	} 
	
		#mytable{
		font-size: 14px;
	}
	.tableData td{
		border-right: 1px solid #F2F2F2;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		color: #666666;
		font-size: 14px;
	}
	.tableData th{
		border-right: 1px solid #DFDFDF;
		border-bottom: 1px solid #DFDFDF;
		text-align: center;
		height: 30px;
		background-color: #EFEEF0;
		color: #666666;
	}
	.editTable tr{
		height: 50px;
	}
	.tableData tr{
		height: 40px;
	}

	.selectAddr{
		width:80px;
	}

.icons {
    background: url(${ctx}/img/icons-total.gif) no-repeat;
    display: block;
}
.fl {
    float: left;
}
.icons-address {
    width: 13px;
    height: 15px;
    background-position: -14px -276px;
    margin: 6px 5px 0 0;
}
.icons-phone {
    width: 13px;
    height: 15px;
    background-position: -28px -276px;
    margin: 6px 5px 0 0;
}
.icons-user {
    width: 13px;
    height: 15px;
    background-position: 0 -276px;
    margin: 6px 5px 0 0;
}
.order-address{
    width: 273px;
    height: 155px;
    background: url(${ctx}/img/add-address-bg.gif) no-repeat 0 -155px;
    margin: 10 20px 20px 0;
   	padding-left: 20px;
   	padding-top: 10px;
   	padding-bottom: 10px;
   	padding-right: 10px;
   	font-size: 13px;
}

.pay-tips {
    color: #f36;
    margin-left: 10px;
    font-size: 12px;
    height: 20px;
    line-height: 19px;
    padding-left: 10px;
    padding-right: 10px;
    border: 1px solid #f36;
    display: inline-block;
    vertical-align:middle;
}
.order-address{
    background-position: 0 0;
    color: #333;
}
.addressList{
	color: #666666;
	font-size: 14px;
}
.address div{
	display: inline-block;
}
.address{
	min-height: 30px;
	line-height: 30px;
	width: 1000px;
	border:1px solid white;
	margin-top: 15px;
}
/* .address:HOVER{
	cursor: pointer;
} */
.addr_select{
	border-color:#FF4400;
	background-color: #FFF0E8;
}

/* #editAddrInfo { 
background-color:#fff; 
border:5px solid rgba(0,0,0, 0.4); 
height:400px; 
left:50%; 
margin:-200px 0 0 -200px; 
padding:1px; 
position:fixed !important; position:absolute; 
top:50%; 
width:400px; 
z-index:5; 
border-radius:5px; 
display:none; 
}  */


</style>
<script type="text/javascript">
	function makeOrder(){
		var addrId=$('input[name="addrId"]:checked').val();
		if(addrId==''){
			alert('请选择收获地址！');
			return;
		}
		$('#orderForm').submit();
	}
	
	function selectAddr(obj){
		$('.address').removeClass('addr_select');
		$(obj).parent().parent().parent().addClass('addr_select');
		$('.address').find('.editThisAddr').css('display','none');
		$('.addr_select').find('.editThisAddr').css('display','inline-block');
	}
	function editThisAddr(type){
		$('#editAddrInfo').css('display','block');
		var bh = $("body").height(); 
		var bw = $("body").width(); 
		$("#fullbg").css({ 
			height:bh, 
			width:bw, 
			display:"block" 
		}); 
		if(type==1){//编辑
			var receiver=$('.addr_select').find('.areaIds').attr('receiver');
			var addr=$('.addr_select').find('.areaIds').attr('addr');
			var mobilphone=$('.addr_select').find('.areaIds').attr('mobilphone');
			var isDefault=$('.addr_select').find('.areaIds').attr('isDefault');
			var uaid=$('.addr_select').find('.areaIds').attr('uaid');
			var pid=$('.addr_select').find('.areaIds').attr('pid');
			var cid=$('.addr_select').find('.areaIds').attr('cid');
			var aid=$('.addr_select').find('.areaIds').attr('aid');
			
			$('input[name="receiver"]').val(receiver);
			$('textarea[name="addr"]').val(addr);
			$('input[name="mobilphone"]').val(mobilphone);
			$('input[name="uaid"]').val(uaid);
	 		if(isDefault==0){
				$('input[name="isDefault"]').prop('checked',false);
			}else{
				$('input[name="isDefault"]').prop('checked',true);
			}
			if(pid!=''){
				$('#province').val(pid);
				$.ajax({
					url:'${ctx}/login/addr/listAreas.do',
					type:'post',
					dataType:'json',
					data:{'paid':pid},
					success:function(data){
						$.each(data,function(index,city){
							if((city.aid+'')==cid){
								$('#city').append('<option selected="selected" value="'+city.aid+'">'+city.aname+'</option>');
							}else{
								$('#city').append('<option value="'+city.aid+'">'+city.aname+'</option>');
							}
						});
					}
				});
				$.ajax({
					url:'${ctx}/login/addr/listAreas.do',
					type:'post',
					dataType:'json',
					data:{'paid':cid},
					success:function(data){
						$.each(data,function(index,area){
							if((area.aid+'')==aid){
								$('#area').append('<option selected="selected" value="'+area.aid+'">'+area.aname+'</option>');
							}else{
								$('#area').append('<option value="'+area.aid+'">'+area.aname+'</option>');
							}
							
						});
					}
				});
			}
		}else{
			$('input[name="uaid"]').val('');
			$('input[name="receiver"]').val('');
			$('input[name="mobilphone"]').val('');
			$('#city').val('');
			$('#area').val('');
			$('textarea[name="addr"]').val('');
			$('input[name="isDefault"]').prop('checked',false);
		}
	}
	function listCitys(){
		$('#city').html('<option value="">请选择</option>')
		$('#area').html('<option value="">请选择</option>')
		var aid=$('#province option:selected').val();
		if(aid==''){
			return;
		}
		$.ajax({
			url:'${ctx}/login/addr/listAreas.do',
			type:'post',
			dataType:'json',
			data:{'paid':aid},
			success:function(data){
				$.each(data,function(index,city){
					$('#city').append('<option value="'+city.aid+'">'+city.aname+'</option>');
				});
			}
		});
	}
	
	function listAreas(){
		$('#area').html('<option value="">请选择</option>')
		
		var aid=$('#city option:selected').val();
		if(aid==''){
			return;
		}
		$.ajax({
			url:'${ctx}/login/addr/listAreas.do',
			type:'post',
			dataType:'json',
			data:{'paid':aid},
			success:function(data){
				$.each(data,function(index,area){
					$('#area').append('<option value="'+area.aid+'">'+area.aname+'</option>');
				});
			}
		});
	}
	function submitAddr(){
		if($('#province option:selected').val()==''){
			alert("请选择省份!");
			return;
		}else{
			$('input[name="province"]').val($('#province option:selected').html());
		}
		if($('#city option:selected').val()==''){
			alert("请选择城市!");
			return;
		}else{
			$('input[name="city"]').val($('#city option:selected').html());
		}
		if($('#area option:selected').val()==''){
			alert("请选择区县!");
			return;
		}else{
			$('input[name="area"]').val($('#area option:selected').html());
		}
		$.ajax({
			url:'${ctx}/login/addr/saveAddrAjax.do?'+$('#addrForm').serialize(),
			type:'get',
			async:false,
			dataType:'json',
			success:function(data){
				if(data.message=='success'){
					alert("地址保存成功!");
					var result=data.result;
					var uaid=$('input[name="uaid"]').val();
					if(uaid==''){
						$('.address').removeClass('addr_select');
						$('#addAddrBtn').before('<div class="address addr_select">'+
								'<div style="width: 30px;margin-left: 10px;vertical-align: top;margin-top: 3px;">'+
								'<input type="radio" name="addrId" checked="checked" value="'+result.uaid+'" onclick="selectAddr(this)">'+
								'</div>'+
								'<div class="areaIds" style="max-width: 550px" pid="'+result.pid+'" aid="'+result.aid+'" cid="'+result.cid+'" addr="'+result.addr+'"'+
								'receiver="'+result.receiver+'" mobilphone="'+result.mobilphone+'" isDefault="'+result.isDefault+'" uaid="'+result.uaid+'">'+
									'<em class="icons icons-address fl"></em>'+result.province+'&nbsp;'+result.city+'&nbsp;'+result.area+'&nbsp;'+result.addr+'&nbsp;&nbsp;'+
								'</div>'+
								'<div style="vertical-align: top;"><em class="icons icons-user fl"></em>'+result.receiver+'&nbsp;收&nbsp;&nbsp;</div>'+
								'<div style="vertical-align: top;"><em class="icons icons-phone  fl"></em>'+result.mobilphone+'&nbsp;</div>'+
								'</div>');
					}else{
						$('.addr_select').find(".addressInfo").html('<div style="width: 30px;margin-left: 10px;vertical-align: top;margin-top: 3px;">'+
								'<input type="radio" name="addrId" checked="checked" value="'+result.uaid+'" onclick="selectAddr(this)">'+
								'</div>'+
								'<div class="areaIds" style="max-width: 550px" pid="'+result.pid+'" aid="'+result.aid+'" cid="'+result.cid+'" addr="'+result.addr+'"'+
								'receiver="'+result.receiver+'" mobilphone="'+result.mobilphone+'" isDefault="'+result.isDefault+'" uaid="'+result.uaid+'">'+
									'<em class="icons icons-address fl"></em>'+result.province+'&nbsp;'+result.city+'&nbsp;'+result.area+'&nbsp;'+result.addr+'&nbsp;&nbsp;'+
								'</div>'+
								'<div style="vertical-align: top;"><em class="icons icons-user fl"></em>'+result.receiver+'&nbsp;收&nbsp;&nbsp;</div>'+
								'<div style="vertical-align: top;"><em class="icons icons-phone  fl"></em>'+result.mobilphone+'&nbsp;</div>');
					}
					
				}else{
					alert('地址保存失败，错误码:['+data.message+']');
				}
				closeBg();
			}
		});	
	}
	function closeBg() { 
		$("#fullbg,#editAddrInfo").hide(); 
	} 
</script>
</head>
<body class="_body">
<div  class="page_body">
	<%@ include file="/common/top.jsp" %>
	<div class="page_middle">
		<div style="margin: 0px auto;width: 80%;">
				<!-- 遮罩层 -->
				<div id="fullbg" style="background-color:gray; left:0; opacity:0.5; position:absolute; top:0; z-index:3; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity:0.5; "></div> 
				<div style="display:none;position: absolute;border-radius:5px;width:600px;height: 400px;z-index: 100;background-color: #EFEEF0;left: 25%;top: 100px" id="editAddrInfo">
				<div onclick="closeBg()" style="width: 580px;height: 20px;margin: 0px auto;line-height: 25px;color: #666666;font-size: 20px;font-weight: bold;text-align: right;cursor: pointer;">
					×
				</div>
				<form action="${ctx}/login/addr/saveAddr.do" id="addrForm" method="post" enctype="application/x-www-form-urlencoded">
				<table class="editTable" align="center">
					<tr>
						<td colspan="2" style="font-weight:bold">编辑地址信息</td>
					</tr>
					<tr>
						<td width="90px">收件人：</td>
						<td>
							<input type="hidden" name="uaid" value="${addr.uaid }">
							<input type="text" name="receiver" value="${addr.receiver }" style="height: 30px;width: 240px;" id="receiver"><font color="red">*</font></td>
					</tr>
					<tr>
						<td>手机：</td>
						<td><input type="text" name="mobilphone" value="${addr.mobilphone }" style="height: 30px;width: 240px;" id="mobilphone"><font color="red">*</font></td>
					</tr>
					<tr>
						<td>收件地址：</td>
						<td>
							<select id="province" name="provinceId" onchange="listCitys()" style="height: 30px;width: 77px;" class="selectAddr">
								<option value="">请选择</option>
								<c:forEach items="${provinces}" var="province">
									<option value="${province.aid}">${province.aname}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="province">
							<select id="city" name="cityId" onchange="listAreas()"  class="selectAddr" style="height: 30px;width: 78px;">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="city">
							<select id="area" name="areaId"  class="selectAddr" style="height: 30px;width: 77px;">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="area"><font color="red">*</font>
						</td>
					</tr>
					<tr style="height: 80px">
						<td valign="top" style="line-height: 40px;">详细地址：</>
						<td id="attrs">
							<textarea rows="4" cols="50"  name="addr">${addr.addr}</textarea><font color="red">*</font>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td>默认地址：</td>
						<td id="attrs">
							<input type="checkbox" name="isDefault" value="1" style="width: 20px;">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="javascript:submitAddr()"  class="manager_button">提交</a>
						</td>
					</tr>
				</table>
			</form>
			</div>
			
			
			<form action="${ctx}/login/order/saveOrder.do" id="orderForm" method="post" enctype="application/x-www-form-urlencoded">
				<input type="hidden" name="token" value="${order.token}">
				<div style="width: 1000px;border-bottom: 2px solid #F1F1F1">
					<div style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px;width: 300px;float: left">选择收货地址</div>
					<div style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px;width: 700px;text-align: right;float: left "><a href="${ctx}/login/addr/editAddr.do" target="_blank" style="font-size: 13px">管理收货地址</a>&nbsp;</div>
					<div style="clear: left;"></div>
				</div>
				<div class="addressList">
					<c:choose>
						<c:when test="${addrs==null || empty addrs}">
							<div class="address" style="margin-left: 20px">无收获地址!</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${addrs}" var="addr">
								<div class="address <c:if test="${addr.isDefault==1}">addr_select</c:if>">
									<div style="width: 900px;" class="addressInfo">
										<div style="width: 30px;margin-left: 10px;vertical-align: top;margin-top: 3px;">
											<input type="radio" name="addrId" value="${addr.uaid}" onclick="selectAddr(this)" <c:if test="${addr.isDefault==1}">checked="checked"</c:if>>
										</div>
										<div class="areaIds" pid="${addr.provinceId}" style="max-width: 550px" aid="${addr.areaId}" cid="${addr.cityId}" addr="${addr.addr}" receiver="${addr.receiver}" mobilphone="${addr.mobilphone}" isDefault="${addr.isDefault}" uaid="${addr.uaid}"><em class="icons icons-address fl"></em>${addr.province}&nbsp;${addr.city}&nbsp;${addr.area}&nbsp;${addr.addr}&nbsp;&nbsp;</div>
										<div style="vertical-align: top;"><em class="icons icons-user fl"></em>${addr.receiver}&nbsp;收&nbsp;&nbsp;</div>
										<div style="vertical-align: top;"><em class="icons icons-phone  fl"></em>${addr.mobilphone}&nbsp;</div>
									</div>
									<div style="text-align: right;vertical-align: top;width: 80px;<c:if test="${addr.isDefault==0}">display: none;</c:if>" class="editThisAddr">
											<a href="javascript:editThisAddr(1)"  style="font-size: 13px">修改本地址</a>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<div class="address" id="addAddrBtn">
						<a href="javascript:editThisAddr(0)" style="font-size: 13px">+使用新地址 </a>
					</div>
				</div>
				<br>
				<span style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px">选择支付方式</span>
				<div style="width: 500px;height: 80px;margin-top: 20px">
					<input type="radio" autocomplete="off" value="2" style="width: 40px;vertical-align:middle" name="choose_pay" data-type="支付宝支付" checked="">
                     <span style="border: #ccc solid 1px;text-align: center;width: 168px;height: 40px;display: inline-block;vertical-align:middle">
                     	 <img src="${ctx}/img/Alipay.jpg" style="margin-top: 7px;">
                     </span>
                     <span class="pay-tips">推荐</span>
				</div>
				<span style="font-weight: bold;font-size: 14px;color: #333;line-height: 20px">确认订单信息</span>
				<table style="width: 1000px;margin-top: 20px" cellpadding="0" cellspacing="0">
					<tr height="50px">
						<td width="300px" align="center" style="border-bottom: 1px dashed #ccc;">商品</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">属性</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">单价(元)</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">数量</td>
						<td width="200px" align="center" style="border-bottom: 1px dashed #ccc;">金额(元)</td>
					</tr>
					<tr height="100px">
						<td align="center" style="border-bottom: 1px dashed #ccc;">
							<img src="${order.sku.skuPic}" width="80px" style="border: 1px solid #ccc;vertical-align: middle;">
							<div style="vertical-align: middle;display: inline-block; width: 150px;text-align: left;margin-left: 10px;">
								${order.sku.goodsName}
							</div>
	 						<input type="hidden" name="skuId" value="${order.sku.sid}">
							<input type="hidden" name="number" value="${order.number}">
						</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">
							<c:forEach items="${order.sku.attrs }" var="attr">
								${attr.attrName}:${attr.attrValue}<br>
							</c:forEach>
						</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">${order.sku.price}</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">${order.number}</td>
						<td align="center" style="border-bottom: 1px dashed #ccc;">${order.amount}</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
						<td align="center">
						<br><input type="button" value="确认订单" class="manager_button" onclick="makeOrder()"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="/common/bottom.jsp" %>
</div>
</body> 
</html>