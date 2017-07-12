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
	<h1>주소록조회</h1>
	<form action="/sharingInformation/board/list">
		<label>이름</label>
		<input type="text" name="val">
		<input type="submit" value="검색">
	</form>
	<table class="table table-bordered">
		<tr>
			<th>부서</th>
			<th>이름</th>
			<th>직위</th>
			<th>메일주소</th>
			<th>연락처</th>
			<th>주소</th>
		</tr>
		<tr>
			<td>인사</td>
			<td>박횬근</td>
			<td>쫄개</td>
			<td>대전역@ㄴㅔ이버</td>
			<td>없음</td>
			<td>대전역 동광장 3번출구 부근</td>
		</tr>
		<tr>
			<td>홍보</td>
			<td>김근우</td>
			<td>쫄개</td>
			<td>대전역@다음</td>
			<td>없음</td>
			<td>대전역 동광장 2번출구 부근</td>
		</tr>

	</table>
	<div id="pageNum">
		<c:forEach begin="1" end="${viewData.getPageTotalCount()}" step="1"
			var="i">
			<a href="/sharingInformation/board/list?page=${i }">[${i}]</a>
		</c:forEach>
	</div>
	
</body>
</html>