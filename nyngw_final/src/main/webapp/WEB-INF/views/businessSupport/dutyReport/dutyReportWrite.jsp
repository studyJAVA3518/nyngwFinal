<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
a{
	display: none;
}
</style>
<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="dutyReportWrite" method="POST" accept-charset="utf-8">
	<table class="table table-bordered">
		<tr>
			<th>보고유형</th>
			<td>
				<select name="dr_code_number"> 
					<c:forEach items="${dr_codeList }" var="codeList">
						<option value="${codeList.code_number }">${fn:substring(codeList.code_name,0,2) }보고</option>
					</c:forEach>
				</select>
			</td>
			<th>보고일</th>
			<td><input type="date" value="dr_date"></td>
		</tr>
		<tr>
			<th>보고대상</th><td colspan="3"><input type="text" name="dr_to_mem_number" value=""><input type="button" value="선택"></td>
		</tr>
		<tr>
			<th>선택</th><td colspan="3"><input type="checkbox" name="dr_yesno">내 업무일지 동시등록</td>
		</tr>
		<tr>
			<th>제목</th><td colspan="3"><input type="text" name="dr_title"></td>
		</tr>
	</table>
	<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
	<button type="button" onclick="saveContent();" class="btn">등록</button>
	<button class="btn"><a href="/businessSupport/dutyReport/dutyReport"></a>취소</button>
</form>