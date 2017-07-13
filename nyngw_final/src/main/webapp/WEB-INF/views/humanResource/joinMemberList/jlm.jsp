<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<form action="excelMemberRank">
	<button class="btn btn-default">엑셀출력</button>
</form>
	<table class="table table-bordered">
		<tr>
			<th>사원번호</th>
			<th>ID</th>
			<th>사원</th>
			<th>부서</th>
			<th>직급</th>
			<th>생년월일</th>
			<th>메일</th>
			<th>연락처</th>
		</tr>
		<c:forEach items="${joinMemberList}" var="member">
			<tr>
				<td><a href="<c:url value=''/>" value="${member.mem_number }">${member.mem_number }</a></td>
				<td>${member.mem_id }</td>
				<td>${member.mem_name }</td>
				<td>${member.mem_dept_number }</td>
				<td>${member.mem_position_number }</td>
				<td>${member.mem_reg }</td>
				<td>${member.mem_email }</td>
				<td>${member.mem_tel }</td>
			</tr>
		</c:forEach>
			
	</table>


