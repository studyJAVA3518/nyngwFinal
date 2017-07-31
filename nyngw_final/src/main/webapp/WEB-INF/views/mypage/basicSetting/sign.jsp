<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">

<h2>개인 정보 수정</h2>
<p class="docTitleDescription">
	자신의 정보를 수정할 수 있습니다.
</p>
<form>
<table class="table table-bordered">
	<tr>
		<th>사진</th>
		<td>
			<img src="/resources/images/profile/${MemberVoDetail.mem_img }" style="width: 150px; height: 200px">
		</td>
		
	</tr>
	<tr>
		<th>사원이름</th>
		<td>${MemberVoDetail.mem_name}</td>
	</tr>
	<tr>
		<th>연락처</th>
		<td>${MemberVoDetail.mem_tel }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${MemberVoDetail.mem_email }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${MemberVoDetail.mem_addr1 }</td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td>${MemberVoDetail.mem_addr2 }</td>
	</tr>
	<tr>
		<th>서  명</th>
		<td><img src="/resources/memsign/${MemberVoDetail.mem_sign }" style="width: 150px; height: 150px;"/></td>
	</tr>
	
					
</table>
</form>
<div class="textCenter insertJoinBtnWrap textCenter">
<button class="btn btn-default"><a href="/mypage/basicSetting/updateMemberForm">수정</a></button>
</div>
