<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script>

function search_go(){
	
	//DatePicker사용시 정규식 사용
	var startdate = document.getElementsByName[0]("startdate").value;
	var enddate = document.getElementsByName[0]("enddate").value;
	var format = /^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	if(!format.test(startdate)){
		alert("시작일은 2010-02-01 형식으로 입력해야합니다.\r\n(1970-01-01부터 2099-12-31까지 검색 가능합니다.)");
		return false;
	}
	if(!format.test(enddate)){
		alert("종료일은 2010-02-01 형식으로 입력해야합니다.\r\n(1970-01-01부터 2099-12-31까지 검색 가능합니다.)");
		return false;
	}
	
	document.vmtmForm.method="post";
	document.vmtmForm.action="<%=request.getContextPath()%>/humanResource/vacationManagementpost/vmtm";
	document.vmtmForm.submit();

}


$(function(){  
    
	
    $('#vacMyTab a').click(function (e) {
		e.preventDefault()
		$(this).tab('show')
	})
	
	$('#vacMyTab a[href="#resentVac"]').css('background-color','#ccc');
	$('#vacMyTab a[href="#resentVac"]').click(function(){
		$('#vacMyTab a[href="#resentVac"]').css('background-color','#ccc');
		$('#vacMyTab a[href="#personVac"]').css('background-color','rgba( 255, 255, 255, 0)');
		
	});
	$('#vacMyTab a[href="#personVac"]').click(function(){
		$('#vacMyTab a[href="#resentVac"]').css('background-color','rgba( 255, 255, 255, 0)');
		$('#vacMyTab a[href="#personVac"]').css('background-color','#ccc');
	});
	$('#list option[value=${list}]').prop('selected',true);
});


</script>

<h2>휴가현황</h2>
<p class="docTitleDescription">
	사원들의 휴가현황을 확인할 수 있습니다.
</p>
<br>
<form method="post" name="vmtmForm">
	<div class="hrmTopSpace">
		<h4>직원별 휴가 사용 현황</h4>
		<input type="text" name="startdate" value="${startdate }" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01"/> 
		<input type="text" name="enddate" value="${enddate }" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01"/> 
		<select id="list" name="list" class="btn btn-default">
			<option value="mem_name" selected= "selected">이름</option>
			<option value="vp_kind">휴가종류</option>
		</select>
		<input type="text" name="search" class="form-control docInputSelect"/>
		<button class="btn btn-default">검색</button>
	</div>
</form>

<div class="row">
	<ul id="vacMyTab" class="nav nav-tabs" role="tablist">
		<li class="active" role="presentation">
			<a data-target="#resentVac" href="#resentVac" aria-controls="resentVac" role="tab" data-toggle="tab" aria-expanded="true" id="resentVacA"> 
				최근 휴가 근황 
			</a>
		</li>
		<li role="presentation">
			<a data-target="#personVac" href="#personVac" aria-controls="personVac" role="tab" data-toggle="tab" aria-expanded="false">
				직원 현황
			</a>
		</li>
	</ul>

	<div id="myTabContent" class="tab-content"  style="border-bottom:1px solid #ddd;padding-bottom:45px;">
		
		<!-- 최근휴가현황 -->
		<div role="tabpanel" class="tab-pane fade active in" id="resentVac">
			
			<div class="hrTapWrap">
				<table class="table table-bordered tableGray textCenter">
					<tr>
						<th>부서</th>
						<th>이름</th>
						<th>직급</th>
						<th>휴가 종료일</th>
					</tr>
					<c:forEach items="${memberVacation }" var="member">
						<tr>
							<td>${member.dept_name }</td>
							<td>${member.mem_name }</td>
							<td>${member.position_name }</td>
							<c:set var = "string1" value = "${member.vacation_end}"/>	
							<c:set var = "string2" value = "${fn:substring(string1, 0,10)}" />
							<td>${string2 }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="textCenter">
					<c:if test="${page.currentPageNo >= page.firstPageNo + page.sizeOfPage}">
						<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}&list=${list}">이전</a>
					</c:if>
					<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" step="1"	var="i">
						<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}&list=${list}">[${i}]</a>
					</c:forEach>
					<c:if test="${page.startPageNo+page.sizeOfPage < page.finalPageNo}">
						<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}&list=${list}">다음</a>
					</c:if>
				</div>
			</div>
		</div>
		
		<!-- 직원 현황 -->
		<div role="tabpanel" class="tab-pane fade" id="personVac">
			<div class="hrTapWrap">
				<table class="table table-bordered tableGray">
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
							<td>
								${member.vp_totalday}
							</td>
						</tr>
						<tr class="tableBottomBar">
							<td>${member.vp_kind }</td>
							<td>
								
								<fmt:parseDate var="dateString2" value="${member.vacation_start }" pattern="yyyymmdd" />
								<fmt:formatDate value="${dateString2}" pattern="yyyy년 MM월 dd일"/>
							</td>
							<td>
								<fmt:parseDate var="dateString3" value="${member.vacation_end }" pattern="yyyymmdd" />
								<fmt:formatDate value="${dateString3}" pattern="yyyy년 MM월 dd일"/>
							</td>
							<td>${member.vp_payonoff }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="textCenter">
					<c:if test="${page.currentPageNo >= page.firstPageNo + page.sizeOfPage}">
						<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}">이전</a>
					</c:if>
					<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" step="1"	var="i">
						<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}">[${i}]</a>
					</c:forEach>
					<c:if test="${page.startPageNo+page.sizeOfPage < page.finalPageNo}">
						<a href="/humanResource/vacationManagement/vmtm?page=${i}&startdate=${startdate}&enddate=${enddate}">다음</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 부서 휴가 현황 -->
	<h4>부서별 휴가 사용 현황</h4>
	<div class="insertJoinBtnWrap textCenter">
		<table class="table table-bordered tableGray">
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

