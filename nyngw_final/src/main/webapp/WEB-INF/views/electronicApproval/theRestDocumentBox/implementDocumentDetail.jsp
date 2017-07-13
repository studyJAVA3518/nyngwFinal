<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

그외문서함>시행문서함>디테일
   ① 시행관리 : 클릭 시 아래 그림과 같이 시행관리를 할 수 있는 팝업이 뜹니다.
      - 시행구분 : 미시행/시행준비/시행/반송 중 한 가지를 선택합니다.
      - 시행 사항을 입력 한 후 ‘저장’ 버튼을 클릭하면 시행 상태가 변경되고, 결재문서에서 '시행관리'
        버튼 클릭 시 확인/수정 할 수 있습니다.
   ②   결재이력 : 결재 처리 과정을 확인 할 수 있는 팝업이 뜹니다.
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
	<button type="button" id="implementManagement_go">시행관리</button>
	<button type="button" id="approvalHistory_go">결재이력</button>
</form>

<script type="text/javascript">
$(function(){
	 $('#approvalHistoryDialog').css('display', 'none');
	 $('#implementManagementDialog').css('display', 'none');
	 
	/////////////////////////////////////////////////
	$("#implementManagement_go").click(function(){
		$('#implementManagementDialog').dialog({
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
})
</script>
<div id="implementManagementDialog">
	시행관리<br>
	시행구분
	<select>
		<option>미시행</option>
		<option>시행준비</option>
		<option>시행</option>
		<option>반송</option>
	</select>
	시행사항<input type="text">
	<button type="button">저장</button>
</div>

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
	상신한(반려된) 기안 문서
</div>
<div>
	반려의견확인<br>
	<textarea></textarea>
</div>