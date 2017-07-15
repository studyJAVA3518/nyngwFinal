<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="vmtm" method="post">
	<table class="table table-bordered">
		<tr>
			<th>휴가사용현황</th>
		</tr>
		<tr>
			<td>
				<input type=date name="startdate" /> 
				<input type=date name="enddate" /> 
				<select name="list" class="btn btn-default">
					<option value="mem_name" selected= "selected">이름</option>
					<option value="vp_kind">휴가종류</option>
				</select>
				<input type="text" name="search" />
				<button class="btn btn-default">검색</button>
			</td>
		</tr>
	</table>
</form>

<div class="container" style="max">
	<ul id="myTab" class="nav nav-tabs" role="tablist">
		<li class="active"><a data-target="#english" role="tab"
			id="eng-tab" data-toggle="tab" aria-controls="english"
			aria-expanded="true"> 최근 휴가 근황 </a></li>
		<li><a data-target="#computer" role="tab" id="com-tab"
			data-toggle="tab" aria-controls="computer" aria-expanded="false">
				직원 현황</a></li>
		<li><a data-target="#korean" role="tab" id="kor-tab"
			data-toggle="tab" aria-controls="korean" aria-expanded="false">
				부서 휴가 현황</a></li>
	</ul>

	<div id="myTabContent" class="tab-content">
		<div role="tabpanel" class="tab-pane fade active in" id="english"
			aria-labelledby="eng-tab">

			<table class="table table-bordered">
				<tr>
					<th>부서</th>
					<th>이름</th>
					<th>직급</th>
					<th>휴가일</th>
				</tr>
				<c:forEach items="${memberVacation }" var="member">
					<tr>
						<td>${member.dept_name }</td>
						<td>${member.mem_name }</td>
						<td>${member.position_name }</td>
						<td>${member.vacation_end }</td>
					</tr>
				</c:forEach>
			</table>
			<c:forEach begin="1" end="${page.finalPageNo}" step="1"	var="i">
					<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}">[${i}]</a>
			</c:forEach>
		</div>

		<div role="tabpanel" class="tab-pane fade" id="computer"
			aria-labelledby="com-tab">
			<table class="table table-bordered">
				<tr>
					<th>부서</th>
					<th>이름</th>
					<th>직급</th>
					<th>휴가일</th>
				</tr>
				<tr>
					<th>휴가</th>
					<th>휴가시작일</th>
					<th>휴가종료일</th>
					<th>급여여부</th>
				</tr>
				<c:forEach items="${memberVacation }" var="member">
					<tr>
						<td>${member.dept_name }</td>
						<td>${member.mem_name }</td>
						<td>${member.position_name }</td>
						<td>${member.vp_totalday }</td>
					</tr>
					<tr>
						<td>${member.vp_kind }</td>
						<td>${member.vacation_start }</td>
						<td>${member.vacation_end }</td>
						<td>${member.vp_payonoff }</td>
					</tr>
				</c:forEach>
			</table>
				<c:forEach begin="1" end="${page.finalPageNo}" step="1"	var="i">
					<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}">[${i}]</a>
				</c:forEach>
		</div>
		<div role="tabpanel" class="tab-pane fade" id="korean"
			aria-labelledby="kor-tab">
			<table class="table table-bordered">
				<tr>
					<th>부서</th>
					<th>휴가자</th>
					<th>연차</th>
					<th>결혼</th>
					<th>출산</th>
					<th>사망</th>
				</tr>
				<c:forEach items="${deptVacation }" var="member">
					<tr>
						<td>${member.dept_name }</td>
						<td>${member.dept_total }</td>
						<td>${member.dept_sf1 }</td>
						<td>${member.dept_sf2 }</td>
						<td>${member.dept_sf3 }</td>
						<td>${member.dept_sf4 }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

