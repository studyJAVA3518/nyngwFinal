<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
결재진행>반려문서
반려문서는 본인이 상신한 문서 중 결재자가 반려 처리한 문서를 확인, 처리할 수 있는 메뉴입니다.

<form>
	<table class="table">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption" class="form-control">
					<option>반려일</option>
					<option>기안일</option>
				</select>
			</td>
			<td>
				<input type="date" class="form-control" id="" name=""/>
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
		<th>상태</th>
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:if test="${empty ra_eaList }">
		<tr><td colspan="7">반려 문서가 없습니다!</td></tr>
	</c:if>
	<c:forEach items="${ra_eaList}" var="EA" varStatus="status">
		<tr>
		
			<td>${EA.ea_number }</td>
			<td>${code_nameList[status.index].code_name }</td>
			<td><a href="/electronicApproval/approvalProgress/refusedApprovalDetail?ea_number=${EA.ea_number}">${EA.ea_title }</a></td>
			<td>${memberList[status.index].mem_name}</td>
			<td>
				${EA.ea_startdate}
			</td>
			<td>
				${completeDateFormatList[status.index]}
			</td>
			<td>${statusResult}</td>
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