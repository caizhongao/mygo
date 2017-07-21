	<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<link rel="stylesheet" type="text/css" href="${ctx}/common/upload/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/upload/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/upload/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/upload/css/demo.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/upload/webuploader-0.1.5/webuploader.css">
		<p>sku图片信息</p>
		<div id="uploader" class="wu-example" style="width: 70%">
			<div class="queueList">
				<div id="dndArea" class="placeholder">
					<div id="filePicker"></div>
					<p>或将照片拖到这里</p>
				</div>
			</div>
			<div class="statusBar" style="display:none">
				<div class="progress">
					<span class="text">0%</span>
					<span class="percentage"></span>
				</div>    
				<div class="info"></div>
				<div class="btns">
					<div id="filePicker2"></div>
					<div class="uploadBtn">开始上传</div>
				</div>
			</div>
		</div>
	<script type="text/javascript" src="${ctx}/common/upload/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/common/upload/webuploader-0.1.5/webuploader.js"></script>
	<script type="text/javascript">
	window.webuploader = {
		config:{
			thumbWidth: 220, //缩略图宽度，可省略，默认为110
			thumbHeight: 220, //缩略图高度，可省略，默认为110
			wrapId: 'uploader', //必填
		},
		//处理客户端新文件上传时，需要调用后台处理的地址, 必填
		uploadUrl: '${ctx}/login/goods/uploadPic.do',
		//处理客户端原有文件更新时的后台处理地址，必填
		updateUrl: 'fileupdate.php',
		//当客户端原有文件删除时的后台处理地址，必填
		removeUrl: 'filedel.php',
		//初始化客户端上传文件，从后台获取文件的地址, 可选，当此参数为空时，默认已上传的文件为空
		initUrl: 'fileinit.php',
		formData: {  
			skuCode:$('#goodsCode').val()
	    },
	}
	</script>
	<script src="${ctx}/common/upload/webuploader-0.1.5/extend-webuploader.js" type="text/javascript"></script>

