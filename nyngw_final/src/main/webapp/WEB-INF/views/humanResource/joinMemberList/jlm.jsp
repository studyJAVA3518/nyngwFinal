<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>사원 정보 페이지</h2>
<p class="docTitleDescription">
	그룹웨어에 등록된 사원을 확인할 수 있습니다.
</p>

<div class="insertDocBtnWrap textRight">
<form action="excelMemberRank">
	<button class="btn btn-default">엑셀출력</button>
</form>
</div>

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
		<tr onclick="location.href='/humanResource/joinMemberList/detail?mem_id=${member.mem_id }'" style="cursor:pointer;">
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

<div class="textCenter">
	<c:if test="${page.currentPageNo >= page.firstPageNo + page.sizeOfPage}">
		<a href="/humanResource/joinMemberList/jlm?page=${page.startPageNo-1}">이전</a>
	</c:if>
	<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" step="1" var="i">
			<a href="/humanResource/joinMemberList/jlm?page=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.startPageNo+page.sizeOfPage < page.finalPageNo}">
		<a href="/humanResource/joinMemberList/jlm?page=${page.endPageNo+1}">다음</a>
	</c:if>
</div>
