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
	<div>
		<form action="/sharingInformation/noticeMatter/nmUpdate" method="post">
			<label>게시글번호</label><input type="text" value="공지사항 번호나와야 함용" name="board_number" readonly="readonly"><br>
			<label>게시글구분번호</label><input type="text" value="자유게시판" readonly="readonly"><br>
			<label>제목</label><input type="text" name="board_title" value="자동으로 제목을 가져와주겠죠?"><br>
			<label>내용</label><input type="text" name="board_content" value="물론 내용도 ㅎ}"><br>
			<label>파일이름</label><input type="text" name="board_file_name"><br>
			<c:choose>
				<c:when test="${board.board_count==null||board.board_count=='0'}">
					<label>조회수</label>
					<input type="text" value="0" name="board_count" readonly="readonly">
					<br>
				</c:when>
				<c:otherwise>
					<label>조회수</label>
					<input type="text" value="${board.board_count }" name="board_count" readonly="readonly">
					<br>
				</c:otherwise>
			</c:choose>
			<label>작성날자</label>날짜가 나와야하구열 ㅎ<br>
			<label>작성자</label><input type="text" name="board_mem_number" value="작성자또한 자동이겠쥬?" readonly="readonly"><br>
			<input type="hidden" value="${page}">
			<input type="submit" value="수정" />
			<input type="reset" value="초기화" />		
			<button type="button"><a href="/sharingInformation/noticeMatter/nmList">취소</a></button>
		</form>
	</div>
</body>
</html>