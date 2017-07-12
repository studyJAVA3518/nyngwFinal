<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

결재진행>완료문서상세
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
	<button type="button" id="rewriteApproval_go">재작성</button>
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
				}
	     });
     })
     
     $("#rewriteApproval_go").click(function(){
    	 location.href="/electronicApproval/approvalProgress/editDraftForm";
     });
})
</script>

<div id="approvalHistoryDialog">
	결재이력상세<br>
	블라블라
</div>

<hr>
<div>
	결재한 문서
</div>