<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 수정페이지입니다.
	 <div>
		<form action="/documentManagement/documentManager/documentUpdate" method="post">
			<label>문서구분번호</label><input type="text" value="${document.doc_code_number }" name="doc_code_number" readonly="readonly"><br>
			<label>문서번호</label><input type="text" value="${document.doc_number }" name="doc_number" readonly="readonly"><br>
			<label>문서명</label><input type="text" value="${document.doc_name }" name = "doc_name" readonly="readonly"><br>
			<label>설명</label><input type="text" name="doc_explanation" value="${document.doc_explanation }"><br>
			<label>파일이름</label><input type="text" name="doc_file_name" value="${document.doc_file_name }"><br>
			<label>등록일자</label><input type="text" name="doc_date" value="<fmt:formatDate value="${document.doc_date}" pattern="yyyy/MM/dd"/>"><br>
			<label>보존기간</label><fmt:formatDate value="${document.doc_lifetime}" pattern="yyyy/MM/dd"/><br>
			<label>등록자</label><input type="text" name="doc_mem_number" value="${document.doc_mem_number }" readonly="readonly"><br>
			<input type="hidden" value="${page}">
			<input type="submit" value="수정" />
			<input type="reset" value="초기화" />		
			<button type="button"><a href="/documentManagement/documentManager/documentSelect?page=${page }">취소</a></button>
		</form>
	</div> 
	
<!-- <button class="btn"><a href="/documentManagement/documentManager/documentSelect">저장</a></button> -->
</body>
</html>