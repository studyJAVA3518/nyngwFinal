<%@page import="com.nyngw.dto.DocumentViewVO"%>
<%@page import="java.util.List"%>
<%@page import="com.nyngw.documentManagement.documentManager.DocumentListView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	문서관리 > 문서조회
	<div id="searchDiv" style="text-align: center;">
		<div>
			<select>
				<option>문서종류</option>
				<option>문서명</option>
				<option>등록자</option>
			</select>
		</div>
		<div>
			<input type="search">
		</div>
		<div>
			<button class="btn"><a href="/documentManagement/documentManager/documentSelect">검색</a></button>
		</div>
	</div>
	<br>
	<div></div>
	<br>
	<div style="text-align: right;">
		<button class="btn"> <a href="/documentManagement/documentManager/documentInsert">등록</a></button>
	</div>
	<%
		Integer pageNumber = (Integer)request.getAttribute("pageNumber");
		DocumentListView viewData = (DocumentListView)request.getAttribute("viewData");
	%>
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
			<%if(viewData.getDocumentCountPerPage()>0){
				List<DocumentViewVO> documentList = viewData.getDocumentList();
				for(int i = 0; i < documentList.size(); i++){
				%>
					<tr>
						<td><%=viewData.getFirstRow()+i %></td>
						<td><%=documentList.get(i).getDv_code_name()%></td>
						<td><%=documentList.get(i).getDv_doc_date() %></td>
						<td><%=documentList.get(i).getDv_doc_number() %></td>
						<td><a href="/documentManagement/documentManager/documentDetail"><%=documentList.get(i).getDv_doc_name() %></a></td>
						<td><%=documentList.get(i).getDv_mem_name() %></td>
					</tr>
				<%
				}
				%>
				
			<%}else{ %>
				<tr>
					<td style="text-align: center;">내용없음</td>
				</tr>
			<%} %>
		</table>
	</div>
	<div id="pageNum" style="text-align: center;">
		<%for(int i = 1; i<viewData.getPageTotalCount()+1;i++){ %>
			<a href="/documentManagement/documentManager/documentSelect?page=<%=i %>">
			[<%=i %>]</a>
		<%} %>
	</div>
</body>
</html>