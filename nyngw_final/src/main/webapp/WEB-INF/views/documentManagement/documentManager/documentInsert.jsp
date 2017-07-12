<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	문서관리 > 문서등록<br>
	<label>문서번호</label><input type="text" name="doc_number"><br>
	<label>보존기간</label><input type="text" name="doc_lifetime">
	<label>해당담당자</label><input type="text" value="" readonly="readonly"><br>
	<label>문서명</label><input type="text" name="doc_name"><br>
	<label>설명</label><textarea rows="20" cols="60" name="doc_explanation">텍스트편집기</textarea><br>
	<label>파일</label><textarea rows="20" cols="60" name="doc_file_name">drag&drop</textarea> 
	
	
</body>
</html>