<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

퇴사자 리스트
<form action="rmm" method="post">
	<table>
		<tr>
			<td>
				<select name="mem_dept_number" class="btn btn-default">
					<option value="all" selected= "selected">모든부서</option>
					<option value="dept3" >인사부</option>
					<option value="dept4" >기획홍보부</option>
					<option value="dept5" >영업부</option>
					<option value="dept6" >생산부</option>
					<option value="dept7" >인사1팀</option>
					<option value="dept8" >인사2팀</option>
					<option value="dept9" >기획1팀</option>
					<option value="dept10" >기획2팀</option>
				</select>
				<input type="text" name="mem_name" />
				<button class="btn btn-default">검색</button>
			</td>
		</tr>
	</table>
	<table class="table table-bordered">
		<tr>
			<th>퇴사</th>
			<th>부서</th>
			<th>이름</th>
			<th>직책</th>
			<th>연락처</th>
		</tr>
		<c:forEach items="${memberList}" var="member">
			<tr>
				<td>${member.mem_retirement }</td>
				<td>${member.dept_name }</td>
				<td>${member.mem_name}</td>
				<td>${member.position_name }</td>
				<td>${member.mem_tel }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="복직" name="" class="btn btn-default" /> <input
		type="button" value="퇴사" name="" class="btn btn-default" />
</form>