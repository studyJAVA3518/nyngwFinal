<%@page import="java.util.ArrayList"%>
<%@page import="com.nyngw.dto.DocumentViewVO"%>
<%@page import="java.util.List"%>
<%@page import="com.nyngw.documentManagement.documentManager.DocumentListView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<h1>문서조회</h1>
	<div id="searchDiv" style="text-align: center;">
		<div>
			<form action="/documentManagement/documentManager/documentSelect">
				<select name="index">
					<option value="dv_code_name">문서종류</option>
					<option value="dv_doc_name">문서명</option>
					<option value="dv_mem_name">등록자</option>
				</select>
				<input type="hidden" value="${select.index }">
				<input type="text" name="val" value="${select.val }">
				<input type="submit" value="검색">
			</form>
		</div>
		<div>
		</div>
	</div>
	<br>
	<div></div>
	<br>
	<div style="text-align: right;">
		<button type="button"class="btn"> <a href="/documentManagement/documentManager/documentInsert">등록</a></button>
	</div>
	
	<div>
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<th>문서종류</th>
				<th>등록일</th>
				<th>문서번호</th>
				<th>문서명</th>
				<th>등록자</th>
			</tr>
		<c:choose>
			<c:when test="${viewData.documentCountPerPage>0 }">
				<c:forEach items="${viewData.documentList }" var="documentView" varStatus="i">
					<tr>
						<td>${fn:substring(documentView.dv_doc_number,3,10077777)}</td>
						<td>${documentView.dv_code_name }</td>
						<td>${documentView.dv_doc_date }</td>
						<td><a href="/documentManagement/documentManager/documentDetail?dv_doc_number=${documentView.dv_doc_number }&page=${pageNumber}">${documentView.dv_doc_number }</a></td>
						<td>${documentView.dv_doc_name }</td>
						<td>${documentView.dv_mem_name }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td style="text-align : center;">내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
			
		</table>
	</div>
	<div id="pageNum" style="text-align: center;">
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/documentManagement/documentManager/documentSelect?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/documentManagement/documentManager/documentSelect?page=${pno}&index=${select.index}&val=${select.val}"/>">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/documentManagement/documentManager/documentSelect?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
	</div>
