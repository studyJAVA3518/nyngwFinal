<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        

<script>
	var current_date = new Date();
	var MM = current_date.getMonth()+1; // month
	$(function(){  
		$('#monthSelect option[value='+(${month})+']').prop('selected',true);
	});
	
</script>

<h1>생일자 조회</h1>
	<form action="/sharingInformation/memberInformation/birthdayCheck">
	<label>날짜 : </label>
	<select name="month" id="monthSelect">
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
	<input type="submit" value="검색">
</form>
<h1>${month} 월 생일자</h1>
<table class="table table-bordered">
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
<div id="pageNum">
	<c:forEach begin="1" end="${birthdayViewVO.getPageTotalCount()}" step="1"
		var="i">
		<a href="/sharingInformation/memberInformation/birthday?page=${i }">[${i}]</a>
	</c:forEach>
</div>
