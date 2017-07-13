<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

그외문서함>참조문서함>디테일
반려의견확인 : 결재 문서 하단에서 반려자가 입력한 반려 의견을 확인 할 수 있습니다.
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
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
			<th>회사</th>
			<th>부서</th>
			<th>직급</th>
			<th>직책</th>
			<th>이름</th>
			<th>결재종류</th>
			<th>결재종시간</th>
		</tr>
		<tr>
			<td>심플렉스인터넷(주)</td>
			<td>TFT 기획</td>
			<td>사원</td>
			<td>사원</td>
			<td>이주빈</td>
			<td>가결</td>
			<td>2008-03-25 11:53:07</td>
		</tr>
	</table>
</div>

<div>
	자신이 참조자로 있는 결재 문서
</div>
