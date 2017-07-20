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