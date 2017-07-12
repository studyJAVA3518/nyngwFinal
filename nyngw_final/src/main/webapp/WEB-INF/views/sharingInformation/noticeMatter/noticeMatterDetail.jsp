<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			<tr><th>제목</th><td>제목나와야하구용~</td><th>작성자</th><td>작성자이름나오구용~</td></tr>
			<tr><th>작성일</th><td>날자 나와야하구용</td><th>조회수</th><td>조회수나옵니다</td></tr>
			<tr><th>내용</th><td>내용나와야하는 곳</td></tr>
			<tr><th>첨부파일</th><td>첨부파일이 있다면 나와야하는곳</td></tr>
		</table>
			<button><a href="/sharingInformation/noticeMatter/nmUpdateForm">수정하기</a></button>
			<button><a href="/sharingInformation/noticeMatter/nmList">목록</a></button>
	</div>
</body>
</html>