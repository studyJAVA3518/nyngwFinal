<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

결재진행>미결재문서함
미결재문서는 본인이 결재자로 지정된 결재 문서 중 결재 처리를 하지 않은 문서들을 검색, 처리 할 수 있는 메뉴입니다.

<form>
	<table class="table">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption">
					<option>기안일</option>
					<option>완료일</option>
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
<!-- 					<option>전결</option> -->
				</select>
			</td>
		</tr>		
		<tr>
			<td>결재분류</td>
			<td>
				<select name="EAClassificationOption">
					<option>--선택--</option>
					<option>미결</option>
					<option>예결</option>
					<option>후결</option>
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
	<button type="button" onclick="searchwaitingApproval_go(this.form);">검색</button>
</form>

<table class="table" border="1">
	<tr>
		<th>품의번호</th>
		<!-- <th>결재분류</th> -->
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>시행일</th>
		<th>상태</th>
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:forEach items="${eaList}" var="EA" varStatus="status"> 
		<tr>
			<td>${EA.ea_number}</td>
			<td>${code_nameList[status.index].code_name}</td>
			<td><a href="/electronicApproval/approvalProgress/waitingApprovalDetail?ea_number=${EA.ea_number}">${EA.ea_title }</td>
			<td>${memberList[status.index].mem_name}</td>
			<td><fmt:formatDate value="${EA.ea_writedate}" pattern="yyyy/MM/dd"/>
			<td><fmt:formatDate value="${EA.ea_startdate}" pattern="yyyy/MM/dd"/>
				~
				<fmt:formatDate value="${EA.ea_enddate}" pattern="yyyy/MM/dd"/>
			</td>
			<td>${statusList[status.index]}</td>
		</tr>
	</c:forEach>
</table>

<script>
	function searchwaitingApproval_go(form){
		form.method="get";
		form.action="/electronicApproval/approvalProgress/searchWaitingApproval";
		form.submit();
	} 
</script>