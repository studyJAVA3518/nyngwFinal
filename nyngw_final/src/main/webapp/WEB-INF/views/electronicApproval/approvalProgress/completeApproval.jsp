<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>결재완료문서</h2>
<p class="docTitleDescription">
	결재완료는 본인이 상신했던 문서 중 결재 처리가 된 문서를 확인할 수 있는 메뉴입니다.
</p>
<form>
	<table class="table tableGray"  style="margin: 0 0 20px 0;">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption" class="form-control">
					<option>기안일</option>
					<option>결재일</option>
				</select>
			</td>
			<td>결재상태</td>
			<td>
				<select name="EAStatusOption" class="form-control">
					<option>--선택--</option>
					<option>전체</option>
					<option>종결</option>
					<option>전결</option>
					<option>반려</option>
				</select>
			</td>
		</tr>		
		<tr>
			<td>문서검색</td>
			<td>
				<select name="docSearchOption" class="form-control">
					<option>--선택--</option>
					<option>제목</option>
					<option>품의번호</option>
					<option>문서분류</option>
				</select>
			</td>
			<td colspan="2">
				<input type="text" name="searchText" class="form-control">
			</td>	
		</tr>		
	</table>
	<div class="textCenter">
		<button type="button" onclick="searchCompleteApproval_go(this.form);" class="btn btn-default">검색</button>
	</div>
</form>

<table class="table" border="1"  style="margin-top: 20px;">
	<tr>
		<th>품의번호</th>
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>결재일</th>
		<th>상태</th>
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:if test="${empty ca_eaList }">
		<tr><td colspan="7">결재완료 문서가 없습니다!</td></tr>
	</c:if>
	<c:forEach items="${ca_eaList}" var="EA" varStatus="status" begin="0" end="${ca_eaList.size()}">
		<tr>
			<td>${EA.ea_number }</td>
			<td>${code_nameList[status.index].code_name }</td>
			<td><a href="/electronicApproval/approvalProgress/completeApprovalDetail?ea_number=${EA.ea_number}">${EA.ea_title }</a></td>
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
	function searchCompleteApproval_go(form){
		form.method="get";
		form.action="/electronicApproval/approvalProgress/searchCompleteApproval";
		form.submit();
	} 
</script>