<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<h2>근태현황</h2>
<form action="hrm">
	<input type="date" name="startdal_date" class="form-control"/>
	<input type="date" name="enddal_date" class="form-control"/> 
	<input type="text" name="mem_name" placeholder="사원 이름을 입력" class="form-control">
	<button class="btn btn-default">조회</button>
</form>

<h2>사원 현황</h2>
<table>
	<tr>
		<td>사원번호</td>
		<td>사원아이디</td>
		<td>사원명</td>
		<td>부서명</td>
		<td>직책</td>
		<td>날짜</td>
		<td>사유</td>
	</tr>
	<c:forEach items="${memdalList}" var="mem">
		<tr>
			<td>${mem.mem_number }</td>
			<td>${mem.mem_id }</td>
			<td>${mem.mem_name }</td>
			<td>${mem.dept_name }</td>
			<td>${mem.position_name }</td>
			<td>${mem.dal_date }</td>
			<td>${mem.dal_content }</td>
		</tr>
	</c:forEach>
</table>
<form action="excelMemberRank">
	<button class="btn btn-default">엑셀출력</button>
</form>

<h2>부서별 현황</h2>
<table>
	<tr>
		<td>부서</td>
		<td>직원수</td>
		<td>휴가</td>
		<td>조퇴</td>
		<td>외근</td>
		<td>야근</td>
		<td>결근</td>
	</tr>
	<c:forEach items="${countTotal}" var="mem">
		<tr>
			<td>${mem.dept_number }</td>
			<td>${mem.dept_total }</td>
			<td>${mem.dept_sf1 }</td>
			<td>${mem.dept_sf2 }</td>
			<td>${mem.dept_sf3 }</td>
			<td>${mem.dept_sf4 }</td>
			<td>${mem.dept_sf5 }</td>
		</tr>
	</c:forEach>
</table>
<form action="excelCountRank">
	<button class="btn btn-default">엑셀출력</button>
</form>