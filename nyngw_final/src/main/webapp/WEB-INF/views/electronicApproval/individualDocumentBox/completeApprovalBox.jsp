<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
개인문서함>완료문서함
결재완료문서는 본인이 결재라인에 포함되어 있는 문서들 중 결재 처리를 한 문서들을 확인할 수 있는 메뉴입니다.
검색 목록의 제목을 클릭하면 결재문서가 팝업으로 뜨면 결재 이력을 확인할 수 있습니다. 

<script type="text/javascript">

// function approvalHistory_go(){
// 	$(function(){
		
// 	})
// }

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
//         		   $('#dept_name').text(res.dept_name);
// 	        	   $('#position_name').text(res.position_name);
// 	        	   $('#mem_name').text(res.mem_name);
// 	        	   $('#ah_status').text(res.ah_status);
// 	        	   $('#ah_time').text(res.ah_time);
        		   
        	   });
				$("#historyList").append(code);
           },
           dataType : 'json'
        })
		
		
		
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
    })
    ///////////////////////////////////////////////
	$("#editDraft_go").click(function(){
		location.href="/electronicApproval/individualDocumentBox/editDraftForm";
	});
	
})
</script>
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

<form>
	<table class="table">
		<tr>
			<th>검색일자</th>
			<td>
				<select name="EADateOption">
					<option>기안일</option>
					<option>최종 결재일</option>
				</select>
			</td>
			<th>결재상태</th>
			<td>
				<select name="EAStatusOption">
					<option>--선택--</option>
					<option>상신</option>
					<option>진행</option>
					<option>종결</option>
					<option>반려</option>
				</select>
			</td>
		</tr>		
		<tr>
			<th>문서검색</th>
			<td>
				<select name="docSearchOption">
					<option>--선택--</option>
					<option>제목</option>
					<option>품의번호</option>
					<option>문서분류</option>
				</select>
				<input type="text" name="searchText">
			</td>	
		</tr>		
	</table>
	<button type="button" onclick="completeApprovalBox_go(this.form);">검색</button>
</form>

<table class="table" border="1">
	<tr>
		<th>품의번호</th>
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>결재일</th> <!-- 최종 결재자 -->
		<th>결재상태</th>	<!-- 결재상태  ( 결재 한 번도 안 됐을 때 '상신'/결재가 한 번이라도 이루어 졌을 때 '진행' --> 
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:forEach items="${myEaList }" var="EA" varStatus="status">
		<tr>
			<td class="ea_number">${EA.ea_number }</td>
			<td>${code_nameList[status.index].code_name }</td>
			<td class="approvalHistory_go" style="color:blue;">${EA.ea_title }</td>
			<td>${EA.ea_mem_number }</td>
			<td>${EA.ea_startdate}</td>
			<td><fmt:formatDate value="${EA.ea_ah_time}" pattern="yyyy/MM/dd"/></td>			
			<td>${status1 }</td>
		</tr>
	</c:forEach>
</table>

<script>
	function submitApprovalBox_go(form){
		form.method="get";
		form.action="/electronicApproval/individualDocumentBox/completeApprovalBox";
		form.submit();
	} 
</script>