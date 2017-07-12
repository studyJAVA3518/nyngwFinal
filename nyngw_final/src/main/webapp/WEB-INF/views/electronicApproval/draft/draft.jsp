<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 전자결재>기안하기-->
<form>
	<table class="table">
		<tr>
			<td>기안문서함</td>
			<td>
				<select name="draftBoxOption">
					<option>--선택--</option>
					<option value="code8">기안문서A</option>
					<option value="code9">기안문서B</option>
					<option value="code10">기안문서C</option>
				</select>
			</td>
			
		</tr>
		<tr>
			<td>검색어</td>
			<td>
				<select name="searchOption">
					<option>--선택--</option>
					<option value="doc_name">문서명</option>
					<option value="doc_explanation">문서설명</option>
					<option value="doc_mem_number">문서등록자</option>
				</select>
			</td>
			<td>
				<input type="text" name="searchText">
			</td>	
		</tr>
	</table>
	<button type="button" onclick="searchDraft_go(this.form);">검색</button>
</form>	

<table class="table">
	<tr>
		<th>기안문서함</th>
		<th>문서명</th>
		<th>문서설명</th>
		<th>문서등록자</th>
		<th>문서등록일</th>
	</tr>
	
	<c:forEach items="${documentList }" var="document" varStatus="status">
		<tr>
			<td>${code_nameList[status.index].code_name }</td>
			<td>${document.doc_name }</td>
			<td>${document.doc_explanation }</td>
			<td>${document.doc_mem_number }</td>
			<td><fmt:formatDate value="${document.doc_date}" pattern="yyyy/MM/dd"/></td>
		</tr>
	</c:forEach>
</table>

<script>
	function searchDraft_go(form){
		form.method="get";
		form.action="/electronicApproval/draft/searchDraftDocument";
		form.submit();
	} 
</script>
