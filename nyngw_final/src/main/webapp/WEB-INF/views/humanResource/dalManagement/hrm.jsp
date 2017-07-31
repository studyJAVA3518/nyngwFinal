<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>
    
	//DatePicker사용시 정규식 사용
   var startdal_date = document.getElementById("startdal_date").value;
   var enddal_date = document.getElementById("enddal_date").value;
   var format = /^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
   
   if(!format.test(startdal_date)){
      alert("시작일은 2010-02-01 형식으로 입력해야합니다.\r\n(1970-01-01부터 2099-12-31까지 검색 가능합니다.)");
      return false;
   }
   if(!format.test(enddal_date)){
      alert("시작일은 2010-02-01 형식으로 입력해야합니다.\r\n(1970-01-01부터 2099-12-31까지 검색 가능합니다.)");
      return false;
   }
	
    
    
	
</script>

<h2>근태 현황 페이지</h2>
<p class="docTitleDescription">
	사원들의 근태현황을 확인할 수 있습니다.
</p>

<form action="hrm" method="post">
	<div class="hrmTopSpace">
		날짜 선택
		<input type="text" name="startdal_date" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01"/>
		<input type="text" name="enddal_date" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01"/>
		<input type="text" name="mem_name" placeholder="사원 이름을 입력" class="form-control hrmInputSearch">
		<button class="btn btn-default">조회</button>
	</div>
</form>

<h4>사원별 근태현황</h4>

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
		<th>사원명</th>
		<th>부서명</th>
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
<div class="textCenter pageWrap">
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
<h4>부서별 근태현황</h4>
<div id="btngroup"></div>
<div class="insertJoinBtnWrap textCenter" >
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
</div>