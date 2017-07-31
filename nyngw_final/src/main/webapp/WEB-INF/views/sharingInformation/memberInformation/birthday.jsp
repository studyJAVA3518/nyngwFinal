<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        

<script>
	$(function(){  
		$("#monthSelect option[value='${month}']").prop('selected',true);
	});
</script>

<h2>생일자 조회</h2>
	<p class="docTitleDescription">
		사원들의 생일을 확인할 수 있습니다.
	</p>
	<div style="text-align: right;">
	<form action="/sharingInformation/memberInformation/birthdayCheck">
	<label>날짜 : </label>
	<select name="month" id="monthSelect" class="form-control docInputSelect" style="width: 60px;">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select>
	월&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="submit" value="검색" class="btn btn-default">
</form>
</div>
<div style="text-align: center;">
<h2>${month} 월 생일자</h2>
</div>
<table class="table table-bordered textCenter">
	<tr>
		<th>날짜</th>
		<th>부서</th>
		<th>이름</th>
		<th>직위</th>
		<th>메일주소</th>
		<th>연락처</th>
	</tr>
	<c:choose>
		<c:when test="${birthdayViewVO.memberTotalCount > 0 }">
			<c:forEach items="${birthdayViewVO.birthdayList }" var="birthday">
				<tr>
					<td>${fn:substring(birthday.mem_birthday,0,10)}</td>
					<td>${birthday.mem_dept_name }</td>
					<td>${birthday.mem_name }</td>
					<td>${birthday.mem_position_name }</td>
					<td>${birthday.mem_email }</td>
					<td>${birthday.mem_tel }</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td style="text-align: center;" colspan="6">생일자가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>

</table>
<div id="pageNum" class="textCenter pageBottoWrap">
	<c:if test="${beginPage > perPage}">
		<a href="<c:url value="/sharingInformation/memberInformation/birthdayCheck?page=${beginPage-1}&month=${month }"/>">이전</a>
	</c:if>
	<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
		<a href="<c:url value="/sharingInformation/memberInformation/birthdayCheck?page=${pno}&month=${month }" />">[${pno}]</a>
	</c:forEach>
	<c:if test="${endPage < birthdayViewVO.getPageTotalCount()}">
		<a href="<c:url value="/sharingInformation/memberInformation/birthdayCheck?page=${endPage + 1}&month=${month }"/>">다음</a>
	</c:if>
</div>
