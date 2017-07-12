<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 상세정보페이지 입니다.<br>
	<div>
		<table class="table table-bordered">
			<tr>
				<th>문서번호</th><td>${document.doc_number }</td><th>등록자</th><td>${document.doc_mem_number }</td>
			</tr>
			<tr>
				<th>문서명</th><td colspan="3">${document.doc_name }</td>
			</tr>
			<tr>
				<th>등록일자</th><td><fmt:formatDate value="${document.doc_date}" pattern="yyyy/MM/dd"/></td>
				<th>보존기간</th><td><fmt:formatDate value="${document.doc_lifetime}" pattern="yyyy/MM/dd"/></td>
			</tr>
			<tr>
				<th>설명</th><td colspan="3">${document.doc_explanation }</td>
			</tr>
			<tr>
				<th>파일이름</th>
				<td colspan="2">${document.doc_file_name }</td>
				<td><button class="btn">다운로드</button></td>
			</tr>
		</table>
	</div>
	<div>
		<button class="btn"><a href="/documentManagement/documentManager/documentUpdateForm?doc_number=${document.doc_number }&page=${pageNumber}">수정</a></button>
		<button class="btn"><a href="/documentManagement/documentManager/documentSelect?page=${page }">목록</a></button>
		<button class="btn"><a href="/documentManagement/documentManager/documentDelete?doc_number=${document.doc_number }&page=${pageNumber}">삭제</a></button>
	</div>
</body>
</html>
