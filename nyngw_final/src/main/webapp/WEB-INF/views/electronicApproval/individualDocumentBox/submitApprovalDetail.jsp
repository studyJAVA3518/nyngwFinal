<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

개인문서함>상신문서함>디테일
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
	<button type="button" id="editDraft_go">수정</button>
	<button type="button" id="editHistory_go">내역보기</button>
</form>

<script type="text/javascript">
$(function(){
	 $('#editHistoryDialog').css('display', 'none');
	 
	/////////////////////////////////////////////////
	$("#editHistory_go").click(function(){
		$('#editHistoryDialog').dialog({
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

<div id="editHistoryDialog">
	수신 및 참조 수정 내용
	<table class="table">
		<tr>
			<th>순서</th>
			<th>성명</th>
		</tr>
		<tr>
			<th colspan="2">날짜</th>
		</tr>
		<tr>
			<td><input type="radio" checked="checked">1<td>
			<td>정혜리<td> 
		</tr>
		<tr>
			<td colspan="2">2009-03-29 10:08:50</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<th>회사</th>
			<th>부서</th>
			<th>사용자</th>
		</tr>
		<tr>
			<td>회사</td>
			<td>부서</td>
			<td>사용자</td>
		</tr>
	</table>
</div>

<div>
	상신한 (수정할) 기안 문서
</div>