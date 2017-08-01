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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<h2>전자문서 조회</h2>
	<p class="docTitleDescription">
		전자 문서를 확인할 수 있습니다.
	</p>
	<div id="searchDiv" style="text-align: center;">
		<div>
			<form action="/documentManagement/documentManager/edocumentSelect">
				<select name="index" class="form-control docInputSelect">
					<option value="dv_code_name">문서종류</option>
					<option value="dv_doc_name">문서명</option>
					<option value="dv_mem_name">등록자</option>
				</select>
				<input type="hidden" value="${select.index }">
				<input type="text" name="val" value="${select.val }" class="form-control docInputSearch">
				<input type="submit" value="검색" class="btn btn-default">
			</form>
		</div>
		<div>
		</div>
	</div>
	<br>
	<div></div>
	<br>
	<sec:authorize access="hasAnyRole('role_admin','role_hr_admin','role_ppr_admin','role_master')">
	<div class="insertDocBtnWrap textRight">
		<button type="button" class="btn btn-default"> 
			<a href="/documentManagement/documentManager/edocumentInsert">등록</a>
		</button>
	</div>
	</sec:authorize>
	<div>
		<table class="table table-bordered textCenter">
			<tr>
				<th>번호</th>
				<th>문서종류</th>
				<th>문서명</th>
				<th>등록자</th>
				<th>등록일</th>
			</tr>
		<c:choose>
			<c:when test="${viewData.documentCountPerPage>0 }">
				<c:forEach items="${viewData.documentList }" var="documentView" varStatus="i">
					<tr>
						<td>${fn:substring(documentView.dv_doc_number,3,10077777)}</td>
						<td>${documentView.dv_code_name }</td>
						<td><a href="/documentManagement/documentManager/edocumentDetail?dv_doc_number=${documentView.dv_doc_number }&page=${pageNumber}">${documentView.dv_doc_name }</a></td>
						<td>${documentView.dv_mem_name }</td>
						<td>${documentView.dv_doc_date }</td>
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
	<div id="pageNum" class="insertJoinBtnWrap textCenter pageBottoWrap">
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/documentManagement/documentManager/edocumentSelect?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/documentManagement/documentManager/edocumentSelect?page=${pno}&index=${select.index}&val=${select.val}"/>">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/documentManagement/documentManager/edocumentSelect?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
	</div>
