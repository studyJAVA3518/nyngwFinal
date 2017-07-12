<%@page import="com.nyngw.dto.DocumentViewVO"%>
<%@page import="java.util.List"%>
<%@page import="com.nyngw.documentManagement.documentManager.DocumentListView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	문서관리 > 문서조회
	<div id="searchDiv" style="text-align: center;">
		<div>
			<form action="/documentManagement/documentManager/documentSelect">
				<select name="index">
					<option value="doc_code_number">문서종류</option>
					<option value="doc_name">문서명</option>
					<option value="doc_mem_number">등록자</option>
				</select>
				<input type="text" name="val">
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
		<button type="button"class="btn"> <a href="/documentManagement/documentManager/documentInsert?page=${pageNumber}">등록</a></button>
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
						<td>${fn:substring(bocumentView.doc_number,3,10077777)}</td>
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
		<c:forEach begin="1" end="${viewData.getPageTotalCount()}" step="1"
			var="i">
			<a href="/documentManagement/documentManager/documentSelect?page=${i} %>">[${ i}]</a>
		</c:forEach>
	</div>
</body>
</html>