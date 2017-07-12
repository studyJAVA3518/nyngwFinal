<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form>
	<table class="table">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption">
					<option>기안일</option>
					<option>완료일</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>결재상태</td>
			<td>
				<select name="EAStatusOption">
					<option>--선택--</option>
					<option>상신</option>
					<option>진행</option>
					<option>종결</option>
					<option>반려</option>
<!-- 					<option>전결</option> -->
				</select>
			</td>
		</tr>		
		<tr>
			<td>결재분류</td>
			<td>
				<select name="EAClassificationOption">
					<option>--선택--</option>
					<option>미결</option>
					<option>예결</option>
					<option>후결</option>
				</select>
			</td>
		</tr>		
		<tr>
			<td>문서검색</td>
			<td>
				<select name="docSearchOption">
					<option>--선택--</option>
					<option>제목</option>
					<option>품의번호</option>
					<option>문서분류</option>
				</select>
			</td>
			<td>
				<input type="text" name="searchText">
			</td>	
		</tr>		
	</table>
	<button type="button" onclick="searchwaitingApproval_go(this.form);">검색</button>
</form>

<table class="table" border="1">
	<tr>
		<th>품의번호</th>
		<!-- <th>결재분류</th> -->
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>시행일</th>
		<th>상태</th>
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:forEach items="${EAList }" var="EA">
		<c:forEach items="${code_nameList}" var="CN">
			<tr>
				<td>${EA.ea_number }</td>
				<td>${CN.code_name}</td>
				<td>${EA.ea_title }</td>
				<td>${EA.ea_mem_number }</td>
				<td><fmt:formatDate value="${EA.ea_startdate}" pattern="yyyy/MM/dd"/>
				<td><fmt:formatDate value="${EA.ea_startdate}" pattern="yyyy/MM/dd"/>
					~
					<fmt:formatDate value="${EA.ea_enddate}" pattern="yyyy/MM/dd"/>
				</td>
				<td>${EA.ea_status }</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>

<script>
	function searchwaitingApproval_go(form){
		form.method="get";
		form.action="/electronicApproval/approvalProgress/searchWaitingApproval";
		form.submit();
	} 
</script>