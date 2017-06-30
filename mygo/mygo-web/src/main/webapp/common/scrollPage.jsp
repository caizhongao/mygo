	<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<link rel="stylesheet" type="text/css" href="${ctx}/common/page/zxf_page.css">
		<div class="zxf_pagediv">
		</div>
	<script src="${ctx}/common/page/jquery-1.10.2.js.download"></script>
	<script src="${ctx}/common/page/jquery.min.js.download"></script>
	<script src="${ctx}/common/page/zxf_page.js.download" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var total=${pager.count};
		var pageSize=${pager.pageSize};
		var pageNumber=${pager.pageNum};
		var pageNum=parseInt(total/pageSize);
		if(total%pageSize>0){
			pageNum++;
		}
		if(pageNum>-1){
			$(".zxf_pagediv").createPage({
				pageNum: pageNum,//总页码
				current: pageNumber,//当前页
				shownum: 8,//每页显示个数
				//activepage: "",//当前页选中样式
				activepaf: "",//下一页选中样式
				backfun: function(e) {
					if(e.current==pageNumber){
						location.reload();
					}else{
						$('.page_middle form').eq(0).append('<input type="hidden" name="pageNum" value="'+e.current+'">');
						$('.page_middle form').eq(0).append('<input type="hidden" name="scrollPage" value="${goods.scrollPage }">');
						$('.page_middle form').eq(0).append('<input type="hidden" name="scrollId" value="${goods.scrollId }">');
						$('.page_middle form').eq(0).append('<input type="hidden" name="count" value="${goods.count }">');
						$('.page_middle form').eq(0).submit();
					}
					
				}
			});
		}
		
	</script>