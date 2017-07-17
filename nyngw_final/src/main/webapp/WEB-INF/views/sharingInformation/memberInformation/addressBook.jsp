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
	<form action="/sharingInformation/memberInformation/addressBook">
		<label>이름</label>
		<input type="text" name="mem_name">
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
		<c:choose>
			<c:when test="${addressBookViewVO.memberTotalCount > 0 }">
				<c:forEach items="${addressBookViewVO.addressBookList }" var="addressBook">
					<tr>
						<td>${addressBook.mem_dept_name }</td>
						<td>${addressBook.mem_name }</td>
						<td>${addressBook.mem_position_name }</td>
						<td>${addressBook.mem_email }</td>
						<td>${addressBook.mem_tel }</td>
						<td>${addressBook.mem_addr1 } 부근</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td style="text-align: center;" colspan="6">내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div id="pageNum">
		<c:forEach begin="1" end="${addressBookViewVO.getPageTotalCount()}" step="1" var="i">
			<a href="/sharingInformation/memberInformation/addressBook?page=${i }">[${i}]</a>
		</c:forEach>
	</div>
	
</body>
</html>