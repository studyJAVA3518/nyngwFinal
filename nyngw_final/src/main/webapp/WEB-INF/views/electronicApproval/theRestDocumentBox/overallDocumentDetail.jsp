<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

그외문서함>참조문서함>디테일
반려의견확인 : 결재 문서 하단에서 반려자가 입력한 반려 의견을 확인 할 수 있습니다.
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
	<button type="button" id="approvalSpacification_go">결재특이사항</button>
	<button type="button" id="approvalHistory_go">결재이력</button>
</form>

<script type="text/javascript">
$(function(){
	 $('#approvalHistoryDialog').css('display', 'none');
	 
	/////////////////////////////////////////////////
	$("#approvalHistory_go").click(function(){
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
	<table class="table">
		<tr>
			<th>부서</th>
			<th>직급</th>
			<th>이름</th>
		</tr>
		<c:forEach items="${list}"  var="ea">
			<tr>
				<td>${ea.dept_name}</td>
				<td>${ea.position_name}</td>
				<td>${ea.mem_name}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div>
	어떤 결재 문서
</div>
