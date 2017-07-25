$(function(){
	$('.listTable tr').hover(function(){
	    $(this).find('td').css("background-color","#F4FAFB");
	},function(){
		$(this).find('td').css("background-color","#fff");
	});
})

function isInt(val){
	var reg = /^\d+$/;
	if(!reg.test(val)){
		return false;
	}
	return true;
}

function isFloat(val){
	var regp = /^\d+(\.\d+)?$/;
	if(!reg.test(val)){
		return false;
	}
	return true;
}

function delspace(obj){
	$(obj).val($(obj).val().replace(/\s/g,''));
}

function returnFloat(value){
	 var value=Math.round(parseFloat(value)*100)/100;
	 var xsd=value.toString().split(".");
	 if(xsd.length==1){
	 value=value.toString()+".00";
	 return value;
	 }
	 if(xsd.length>1){
	 if(xsd[1].length<2){
	 value=value.toString()+"0";
	 }
	 return value;
	 }
}