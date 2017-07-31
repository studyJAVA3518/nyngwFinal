<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>결재완료문서함</h2>
<p class="docTitleDescription">
	결재완료문서는 본인이 결재라인에 포함되어 있는 문서들 중 결재 처리를 한 문서들을 확인할 수 있는
	메뉴입니다. 검색 목록의 제목을 클릭하면 결재문서가 팝업으로 뜨면 결재 이력을 확인할 수 있습니다.
</p>
<div class="eaSearchDivMagin">
	<form>
		검색일자
			<select name="EADateOption" class="form-control docInputSelect" style="width: 120px;">
					<option>기안일</option>
					<option>최종 결재일</option>
			</select>
		결재상태
			<select name="EAStatusOption" class="form-control docInputSelect" style="width: 100px;"> 
					<option>--선택--</option>
					<option>상신</option>
					<option>진행</option>
					<option>종결</option>
					<option>반려</option>
			</select>
		문서검색
			<select name="docSearchOption" class="form-control docInputSelect" style="width: 110px;">
				<option>--선택--</option>
				<option>제목</option>
				<option>품의번호</option>
				<option>문서분류</option>
			</select> 
		<input type="text" name="searchText"  class="form-control eaInputSearch" style="width: 231px;">
		<button type="button" onclick="completeApprovalBox_go(this.form);" class="btn btn-default">검색</button>
	</form>
</div>

<div class="insertJoinBtnWrap textCenter"> 
	<table class="table textCenter" border="1">
		<tr>
			<th>품의번호</th>
			<th>문서분류</th>
			<th>제목</th>
			<th>기안자</th>
			<th>기안일</th>
			<th>결재일</th>
			<!-- 최종 결재자 -->
			<th>결재상태</th>
			<!-- 결재상태  ( 결재 한 번도 안 됐을 때 '상신'/결재가 한 번이라도 이루어 졌을 때 '진행' -->
		</tr>
	
		<!-- EA=electronicApproval (전자결재) -->
		<c:if test="${empty myEaList }">
			<tr><td colspan="7">결재 완료 문서가 없습니다!</td></tr>
		</c:if>
		<c:forEach items="${myEaList }" var="EA" varStatus="status">
			<tr>
				<td class="ea_number">${EA.ea_number }</td>
				<td>${code_nameList[status.index].code_name }</td>
				<td><a href="/electronicApproval/approvalProgress/completeApprovalDetail?ea_number=${EA.ea_number}&checkBox=indi">${EA.ea_title }</a></td>
				<td>${EA.write_name }</td>
				<td>${EA.ea_startdate}</td>
				<td><fmt:formatDate value="${EA.ea_ah_time}" pattern="yyyy/MM/dd" /></td>
				<td>${status1 }</td>
			</tr>
		</c:forEach>
	</table>
</div>	

<script>
	function submitApprovalBox_go(form){
		form.method="get";
		form.action="/electronicApproval/individualDocumentBox/completeApprovalBox";
		form.submit();
	} 
</script>