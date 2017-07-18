<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="excelMemberRank">
	<button class="btn btn-default">엑셀출력</button>
</form>
	<table class="table table-bordered">
		<tr>
			<th>ID</th>
			<th>사원</th>
			<th>부서</th>
			<th>직급</th>
			<th>생년월일</th>
			<th>메일</th>
			<th>연락처</th>
		</tr>
		<c:forEach items="${joinMemberList}" var="member">
			<tr onclick="location.href='/humanResource/joinMemberList/detail?mem_id=${member.mem_id }'">
				<td>${member.mem_id }</td>
				<td>${member.mem_name }</td>
				<td>${member.dept_name}</td>
				<td>${member.position_name }</td>
				<td>${member.mem_reg }</td>
				<td>${member.mem_email }</td>
				<td>${member.mem_tel }</td>
			</tr>
		</c:forEach>
			
	</table>
	<c:forEach begin="1" end="${page.finalPageNo}" step="1"	var="i">
			<a href="/humanResource/joinMemberList/jlm?page=${i}">[${i}]</a>
	</c:forEach>


