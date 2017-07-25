<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<style>
	.tableTd{
 		width: 17%;
 		height: 50px;
	}
	.tableSign{
 		height: 80px;
	}
	.memSign {
		display : block;
		width : 80px;
		height : 60px;
		margin :0 auto;
		background-size: contain;
		background-repeat: no-repeat;
		background-position : top center;
	}
</style>
<script type="text/javascript">
$(function(){
	$('#approvalHistoryDialog').css('display', 'none');
	/////////////////////////////////////////////////
     
     
	$(".approvalHistory_go").click(function(){
		
		var tmp = $(this).siblings('.ea_number').text();
		$.ajax({
			url:'/electronicApproval/individualDocumentBox/completeAllrovalDetail',
			type:'get',
			data: {'ea_number' : tmp},
			success : function(res){
				var code = "";
      	   		$.each(res, function (i,value){
					code+='<tr><td>'+value.dept_name+'</td>';
					code+='<td>'+value.position_name+'</td>';
					code+='<td>'+value.mem_name+'</td>';
					code+='<td>'+value.ah_status+'</td>';
					code+='<td>'+value.ah_time+'</td></tr>';
				});
				$("#historyList").append(code);
			},
			dataType : 'json'
		})
     
     
		$('#approvalHistory_go').click(function(){
			$('#approvalHistoryDialog').dialog({
	 			width: 700,
	 			height: 500,
	 			modal: true,
	 			buttons: {
	 		       "취소": function() {
	 					$(this).dialog("close");
					}
				},
				close: function() {
 					$('#textArea').val('');
				}
			}); 
		});
     
	})
})
</script>

결재진행>완료문서상세
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number}">
	<button type="button" id="approvalHistory_go">결재이력</button>
</form>

<div id="approvalHistoryDialog">
	결재상태 이력보기
	<table class="table" id="historyList">
		<tr>
			<th>부서</th>
			<th>직급</th>
			<th>이름</th>
			<th>결재종류</th>
			<th>결재시간</th>
		</tr>
	</table>
</div>

<hr>
<div>
	<input type="hidden" name="ea_doc_number" value="${eaVO.ea_doc_number}">
	<table class="table table-bordered">
		<tr>
			<th class="tableTh">품의번호</th>
			<td colspan="5">${eaVO.ea_number}</td>
			<input type="hidden" name="ea_number" value="${eaVO.ea_number }">
		</tr>
		<tr>
			<th class="tableTh">작성일자</th>
			<td colspan="5">${eaVO.ea_writedate }</td>
		</tr>
		<tr>
			<th class="tableTh">기안자</th>
			<td colspan="5">${mem_name }</td>
		</tr>
		<!-- 결재자 이름-->   
		<tr id="approvalMember">
			<th rowspan="2">결재</th>
			<c:forEach items="${approvalMember}" var="member">
				<th class="tableTd">${member.mem_name }</th>
			</c:forEach>
			<c:forEach var="i" begin="1" end="${lastAstPriorityOfA }" step="1">
				<th class="tableTd"></th>
			</c:forEach>
		</tr>   
		<!-- 결재자 서명 -->                              
		<tr id="approvalStatus">                                 
			<c:forEach items="${approvalMem_sign}" var="sign" varStatus="index">
				<td id="approvalStatus${index.count}"><div class="tableTd tableSign memSign" style="background-image:url('/resources/memsign/${sign }')"></div></td>
			</c:forEach>
			<c:forEach var="i" begin="${noStartA }" end="${noEndA }" step="1">
				<td id="approvalStatus${i}" class="tableTd tableSign"></td>
			</c:forEach>
			<c:forEach var="i" begin="${emptyStartA }" end="5" step="1">
				<td id="approvalStatus${i}" class="tableTd tableSign"></td>
			</c:forEach>
		</tr>      
		<!-- 합의자 이름 -->                          
		<tr id="agreementMember">                                 
			<th rowspan="2">합의</th>        
			<c:forEach items="${agreementMember}" var="member">
				<th class="tableTd">${member.mem_name }</th>
			</c:forEach>
			<c:forEach var="i" begin="1" end="${lastAstPriorityOfB}" step="1">
				<th class="tableTd"></th>
			</c:forEach>
		</tr>   
		<!-- 합의자 서명 -->                               
		<tr id="agreementStatus"> 
			<c:forEach items="${agreementMem_sign}" var="sign" varStatus="index">  
				<td id="agreementStatus${index.count}"><div class="memSign tableTd tableSign" style="background-image:url('/resources/memsign/${sign }')"></div></td>                            
			</c:forEach>
			<c:forEach var="i" begin="${noStartB }" end="${noEndB}" step="1">
				<td id="agreementStatus${i}"  class="tableTd tableSign"></td>
			</c:forEach>
			<c:forEach var="i" begin="${emptyStartB }" end="5" step="1">
				<td id="agreementStatus${i}"  class="tableTd tableSign"></td>
			</c:forEach>
		</tr>
		<tr>
			<th class="tableTh">시행자</th>
			<td colspan="5">${implementMemberName}</td>
		</tr>
		<tr>
			<th class="tableTh">수신 및 참조</th>
			<td colspan="5">${referenceMemberName}</td>
		</tr>
		<tr>
			<th class="tableTh">시행일자</th>
			<td colspan="5">${eaVO.ea_startdate } ~ ${eaVO.ea_enddate}</td>
		</tr>
		<tr>
			<th class="tableTh">제목</th>
			<td colspan="5">${eaVO.ea_title }</td>
		</tr>
		<tr>
			<th colspan="6">내용</th>
		</tr>
		<tr>
			<td colspan="6">
				${eaVO.ea_content }
			</td>	
		</tr>
	</table>
</div>