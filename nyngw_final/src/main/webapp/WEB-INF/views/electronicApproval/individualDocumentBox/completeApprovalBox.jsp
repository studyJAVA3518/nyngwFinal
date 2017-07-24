<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
개인문서함>완료문서함
결재완료문서는 본인이 결재라인에 포함되어 있는 문서들 중 결재 처리를 한 문서들을 확인할 수 있는 메뉴입니다.
검색 목록의 제목을 클릭하면 결재문서가 팝업으로 뜨면 결재 이력을 확인할 수 있습니다. 

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

<form>
	<table class="table">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption">
					<option>기안일</option>
					<option>최종 결재일</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>결재상태</td>
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
			<td>문서검색</td>
			<td>
				<select name="docSearchOption">
					<option>--선택--</option>
					<option>제목</option>
					<option>품의번호</option>
					<option>문서분류</option>
				</select>
			</td>
			<td>
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
			<td>${EA.ea_number }</td>
			<td>${code_nameList[status.index].code_name }</td>
			<td id="approvalHistory_go" style="color:blue;">${EA.ea_title }</td>
			<td>${EA.ea_mem_number }</td>
			<td>${EA.ea_startdate}</td>
			<td>결재한날짜가있어야함</td>			
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