<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>




<table class="table table-bordered">
	<tr>
		<th>사원 아이디</th>
		<td>${member.mem_id }</td>
	</tr>
	<tr>
		<th>사원 명</th>
		<td>${member.mem_name }</td>
	</tr>
	<tr>
		<th>부서 명</th>
		<td>${member.dept_name }</td>
	</tr>
	<tr>
		<th>직급 명</th>
		<td>${member.position_name }</td>
	</tr>
	<tr>
		<th>연차</th>
		<td>${member.mem_carear }</td>
	</tr>
	<tr>
		<th>휴가</th>
		<td>${member.vp_totalday }</td>
	</tr>
	<tr>
		<th>사용한 휴가</th>
		<td>${member.use_vacation }</td>
	</tr>
	<tr>
		<th>남은 휴가</th>
		<td>${member.nouse_vacation }</td>
	</tr>
</table>
<table class="table table-bordered">
	<tr>
		<th>급여 여부(y/n)</th>
		<th>휴가명</th>
		<th>휴가기간</th>
		<th>휴가일수</th>
	</tr>
	<c:forEach items="${vacationList}" var="vacation">
	<tr>
		<td>${vacation.vp_payonoff }</td>
		<td>${vacation.vp_kind }</td>
		<td>${vacation.vacation_start } ~ ${vacation.vacation_end }</td>
		<td>${vacation.vacation_during }</td>
	</tr>
	</c:forEach>
</table>





