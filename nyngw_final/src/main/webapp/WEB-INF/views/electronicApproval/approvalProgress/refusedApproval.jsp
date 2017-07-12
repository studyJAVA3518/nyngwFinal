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
					<option>반려일</option>
					<option>기안일</option>
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
	<button type="button" onclick="searchRefusedApproval_go(this.form);">검색</button>
</form>

<table class="table" border="1">
	<tr>
		<th>품의번호</th>
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>반려일</th>
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:forEach items="${EAList }" var="EA" varStatus="status">
		<tr>
			<td>${EA.ea_number }</td>
			<td>${code_nameList[status.index].code_name }</td>
			<td>${EA.ea_title }</td>
			<td>${EA.ea_mem_number }</td>
			<td><fmt:formatDate value="${EA.ea_startdate}" pattern="yyyy/MM/dd"/>
			<td><fmt:formatDate value="${EA.ea_refusaldate}" pattern="yyyy/MM/dd"/></td>
		</tr>
	</c:forEach>
</table>

<script>
	function searchRefusedApproval_go(form){
		form.method="get";
		form.action="/electronicApproval/approvalProgress/searchRefusedApproval";
		form.submit();
	} 
</script>