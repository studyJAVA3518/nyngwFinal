<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
그외문서함>시행문서함>디테일
   ① 시행관리 : 클릭 시 아래 그림과 같이 시행관리를 할 수 있는 팝업이 뜹니다.
      - 시행구분 : 미시행/시행준비/시행/반송 중 한 가지를 선택합니다.
      - 시행 사항을 입력 한 후 ‘저장’ 버튼을 클릭하면 시행 상태가 변경되고, 결재문서에서 '시행관리'
        버튼 클릭 시 확인/수정 할 수 있습니다.
   ②   결재이력 : 결재 처리 과정을 확인 할 수 있는 팝업이 뜹니다.
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>

<div id="approvalHistoryDialog">
	결재상태 이력보기
	<table class="table">
		<tr>
			<th>부서</th>
			<th>직급</th>
			<th>이름</th>
		</tr>
		<c:forEach items="${list}"  var="ea">
			<tr>
				<td>${ea.dept_name}</td>
				<td>${ea.position_name}</td>
				<td>${ea.mem_name}</td>
			</tr>
		</c:forEach>		
	</table>
</div>