<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>조직도조회</h1>
http://info.korail.com/mbs/www/subview.jsp?id=www_010604010000   를 참고하였음
링크를 각각 잡아줘야함 (현재 22번째 줄에서 구글로 잡아주고 있다)

<script>

$(function(){
	$.ajax({
		url:"/sharingInformation/memberInformation/getTreeJsonDate",
		type: "get",
		success: function(result){
			var jsonData = getTreeModel( result, 'dept0',{
			    id: "itemId",
			    parentId: "parentId",
			    order: ["label","desc"]
			});
			$(".verticalTree").zooTree(jsonData, {
				forceCreate: true, // 현재 사용하지 않음
				render: function(data) { // <li>...</li> 태그안에 내용을 커스텀 할 수 있다
				    var $div = $("<a href='http://google.com'>").append(data.label);
				    return $div;
				}
			});
		},
		dataType:"json"
	});
});	
</script>


<div class="verticalTree"></div>