<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
그외문서함>참조문서함
참조문서함은 본인이 수신참조인으로 지정된 결재문서를 확인 할 수 있는 메뉴입니다.
<form>
	<table class="table">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption">
					<option value="ea_writedate">기안일</option>
					<option value="ea_startdate">시작일</option>
					<option value="ea_enddate">종료일</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>문서검색</td>
			<td>
				<select name="docSearchOption">
					<option value="all">--선택--</option>
					<option value="ea_title">제목</option>
					<option value="ea_number">품의번호</option>
					<option value="doc_name">문서분류</option>
				</select>
			</td>
			<td>
				<input type="text" name="searchText">
			</td>	
		</tr>		
	</table>
	<button type="button" onclick="searchImplementDocument_go(this.form);">검색</button>
</form>

<table class="table" border="1">
	<tr>
		<th>품의번호</th>
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>시행일</th>
		<th>상태</th> 
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:forEach items="${EAList }" var="EA" varStatus="status">
		<tr>
			<td>${EA.ea_number }</td>
			<td>${EA.doc_name }</td>
			<td><a href="/electronicApproval/theRestDocumentBox/referenceDocumentDetail?ea_number=${EA.ea_number}">${EA.ea_title }</a></td>
			<td>${EA.mem_name}</td>
			<td><fmt:formatDate value="${EA.ea_writedate}" pattern="yyyy/MM/dd"/>
			<td><fmt:formatDate value="${EA.ea_startdate}" pattern="yyyy/MM/dd"/>
					~
				<fmt:formatDate value="${EA.ea_enddate}" pattern="yyyy/MM/dd"/>
			</td>
			<td>${EA.ah_status }</td>
		</tr>
	</c:forEach>
</table>
<script>
	function searchReferenceDocument_go(form){
		form.method="get";
		form.action="/electronicApproval/theRestDocumentBox/searchReferenceDocument";
		form.submit();
	} 
</script>
