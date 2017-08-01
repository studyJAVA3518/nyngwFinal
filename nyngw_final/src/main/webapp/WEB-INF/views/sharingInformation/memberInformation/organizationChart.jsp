<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>조직도 조회 페이지</h2>
	<p class="docTitleDescription">
		사내 조직도를 확인할 수 있습니다.
	</p>
<!-- http://info.korail.com/mbs/www/subview.jsp?id=www_010604010000   를 참고하였음 -->
<!-- 링크를 각각 잡아줘야함 (현재 22번째 줄에서 구글로 잡아주고 있다) -->

<style>
	.verticalTree li a {
	    padding: 8px 15px;
	    font-size: 16px;
	}
	.tableTd1{
		 width:111px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd2{
		 width:75px;
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd3{
		 width:233px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd4{
		 width:160px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd5{
		 width:213px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.verticalTree {
		margin : 0 auto;
	}
</style>

<script>

$(function(){
	// 	기본 설정 dialog 보이지 않게                                                                                                                                                                        
	$('#deptAddress').css('display', 'none');
	
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
					var abc = data.itemId;
				    var $div = $("<a id='detail' name='"+data.itemId+"'>").append(data.label);
				    return $div;
				}
			});
		},
		dataType:"json"
	});
	
	$(document).delegate("#detail",'click', function(){
		var dept_number = this.name;
		$.ajax({                                                                                                                                                                                 
				url:'/sharingInformation/memberInformation/organizationChartDetail',                                                                                                              
				type:'get',                                                                                                                                                                          
				data: {'dept_number': dept_number},                                                                                                                                                  
				success : function(res){                                                                                                                                                             
					var code = "";                   
					var dept = "";
						$.each(res, function (i,value){                                                                                                                                              
						code+='<tr>';                                                                                                                                    
						code+='<td class="tableTd1">'+value.mem_name+'</td>';                                                                                                                                    
						code+='<td class="tableTd2">'+value.mem_position_name+'</td>';                                                                                                                                         
						code+='<td class="tableTd3">'+value.mem_email+'</td>';                                                                                                                                        
						code+='<td class="tableTd4">'+value.mem_tel+'</td>';                                                                                                                                        
						code+='<td class="tableTd5">'+value.mem_addr1+'</td></tr>';  
						dept = '<h2>'+value.mem_dept_name+'</h2>';
					});                                                 
					$("#dept_name").append(dept);
					$("#deptAddressList").append(code);                                                                                                                                                  
				},                                                                                                                                                                                   
				dataType : 'json'                                                                                                                                                                    
		}) 
		
		$('#deptAddress').dialog({  
			width: 870,                                                                                                                                                                          
			height: 500,                                                                                                                                                                         
			modal: true,                                                                                                                                                                         
			buttons: {                                                                                                                                                                           
			   "확인": function() {                                                                                                                                                                
					$(this).dialog("close");
				}                                                                                                                                                                                
			},                                                                                                                                                                                    
			close: function() {                                                                                                                                                                  
				$('#deptAddressList').html('<tr><th class="tableTd1">이름</th><th class="tableTd2">직위</th><th class="tableTd3">메일주소</th><th class="tableTd4">연락처</th><th class="tableTd5">주소</th></tr>'); 
				$("#dept_name").html('');
			}                                                                                                                                                                                    
		}); 
	}); 
	
});

</script>

<div class="row insertJoinBtnWrap">
	<div class="verticalTree"></div>
</div>

<div id="deptAddress" style="text-align: center;">                                                                                                                                                                 
	<div id="dept_name"></div>                                                                                                                                                                              
	<table class="table" id="deptAddressList">                                                                                                                                                       
		<tr>                                                                                                                                                                                     
			<th>이름</th>                                                                                                                                                                          
			<th>직위</th>                                                                                                                                                                          
			<th>메일주소</th>                                                                                                                                                                          
			<th>연락처</th>                                                                                                                                                                        
			<th>주소</th>                                                                                                                                                                        
		</tr>                                                                                                                                                                                    
	</table>                                                                                                                                                                                     
</div>     
