<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<form action="hrm" method="post">
	<table class="table table-bordered">
		<tr>
			<th colspan='4'>근태현황</th>
		</tr>
		<tr>
			<td>
				<input type="date" name="startdal_date" class="form-control" />
			</td>
			<td>
				<input type="date" name="enddal_date" class="form-control" />
			</td>
			<td>
				<input type="text" name="mem_name" placeholder="사원 이름을 입력" class="form-control"></td>
			<td>
				<button class="btn btn-default">조회</button>
			</td>
		</tr>
	</table>
</form>


<table class="table table-bordered">
	<tr>
		<th colspan='5'>사원현황</th>
		<th colspan='3'>
			<form action="excelMemberRank">
				<button class="btn btn-default">엑셀출력</button>
			</form>
		</th>
	</tr>
	<tr>
		<th>사원번호</th>
		<th>사원아이디</th>
		<th>사원명</h>
		<th>부서명</h>
		<th>직책</th>
		<th>날짜</th>
		<th>사유</th>
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
<div >
		<c:if test="${page.currentPageNo >= page.firstPageNo + page.sizeOfPage}">
			<a href="/humanResource/dalManagement/hrm?page=${page.startPageNo-1}&startdal_date=${startdal_date}&enddal_date=${enddal_date}">이전</a>
		</c:if>
		<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" step="1" var="i">
				<a href="/humanResource/dalManagement/hrm?page=${i}&startdal_date=${startdal_date}&enddal_date=${enddal_date}">[${i}]</a>
		</c:forEach>
		<c:if test="${page.startPageNo+page.sizeOfPage < page.finalPageNo}">
			<a href="/humanResource/dalManagement/hrm?page=${page.endPageNo+1}&startdal_date=${startdal_date}&enddal_date=${enddal_date}">다음</a>
		</c:if>
</div>
<div id="btngroup"></div>
<table class="table table-bordered">
	<tr>
		<th colspan='5'>부서별현황</th>
		<th colspan='3'><form action="excelCountRank">
				<button class="btn btn-default">엑셀출력</button>
			</form>
		</th>
	</tr>
	<tr>
		<th>부서</th>
		<th>직원수</th>
		<th>휴가</th>
		<th>조퇴</th>
		<th>외근</th>
		<th>야근</th>
		<th>결근</th>
	</tr>
	<c:forEach items="${countTotal}" var="mem">
		<tr>
			<td>${mem.dept_name }</td>
			<td>${mem.dept_total }</td>
			<td>${mem.dept_sf1 }</td>
			<td>${mem.dept_sf2 }</td>
			<td>${mem.dept_sf3 }</td>
			<td>${mem.dept_sf4 }</td>
			<td>${mem.dept_sf5 }</td>
		</tr>
	</c:forEach>
</table>
