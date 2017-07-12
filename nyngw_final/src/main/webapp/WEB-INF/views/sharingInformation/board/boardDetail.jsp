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
	<div>
		<table class="table">
			<tr><th>제목</th><td>${board.board_title}</td><th>작성자</th><td>${board.board_mem_number}</td></tr>
			<tr><th>작성일</th><td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td><th>조회수</th><td>${board.board_count }</td></tr>
			<tr><th>내용</th><td>${board.board_content }</td></tr>
			<tr><th>첨부파일</th><td>${board.board_file_name }</td></tr>
		</table>
			<button><a href="/sharingInformation/board/updateForm?board_number=${board.board_number}">수정하기</a></button>
			<button><a href="/sharingInformation/board/list?page=${page }">목록</a></button>
	</div>
</body>
</html>