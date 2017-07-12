<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/sharingInformation/board/write" method="post">
<%-- 			<label>게시글번호</label><input type="text" value="${board.board_number }" name="board_number" readonly="readonly"><br> --%>
			<label>게시판종류</label><input type="text" value="자유게시판" readonly="readonly"><br>
			<label>제목</label><input type="text" name="board_title"><br>
			<label>내용</label><input type="text" name="board_content"><br>
			<label>파일이름</label><input type="text" name="board_file_name"><br>
			<input type="hidden" value="${page}">
			<input type="submit" value="등록" />
			<input type="reset" value="초기화" />		
			<button type="button"><a href="/sharingInformation/board/list?page=${page }">취소</a></button>
		</form>
	</div>
</body>
</html>