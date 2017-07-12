<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

결재진행>미결재문서함상세
<!-- 선택한 결재문서의 정보들을 가지고 들어와야하고 이 때 필요한 정보를 hidden에 담아 주자 -->
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
	<button type="button" id="approve_go">결재</button>
	<button type="button" id="insteadApprove_go">전결</button>
	<button type="button" id="refuse_go">반려</button>
	<button type="button" id="postpone_go">보류</button>
	<button type="button" id="approvalHistory_go">결재이력</button>
</form>

<script type="text/javascript">
$(function(){
	 $('#approvalDialog').css('display', 'none');
	 
	/////////////////////////////////////////////////
	 $("#approve_go").click(function(){
		 $('#approvalDialog').dialog({
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
  // ======검색버튼====================================================
 	$("#conformApproval").click(function(){
 		$.ajax({
 			url:"/electronicApproval/approvalProgress/conformApproval",	// 결재처리 하는 컨트롤러 url
 			type:"post",
 			data: {"id":"데이터"},
 			success: function(result){ // success
 				location.href = result.uri;
 			},
 			dataType:"json"				// dataType
 		});
 	});
})
</script>

<div id="approvalDialog">
	결재하기
	<form>
		<button type="button" id="conformApproval">확인</button>
		<%User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();%>
		<%=user.getUsername() %>님 결재하시겠습니까?<br>
		비밀번호 <input type="text">
		결재사유
		<br>
		<textarea id="textArea"></textarea>
	</form>   
</div>

<div>
	결재할 기안 문서
</div>