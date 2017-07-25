<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
%>

<script type="text/javascript">
   function file_change(file){
   var str=file.lastIndexOf("\\")+1;   //파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
   file = file.substring(str, file.length);
   document.getElementsByName('doc_file_name')[0].value=file;
}
</script>
	<form enctype="multipart/form-data" name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="documentInsertComplete" method="POST" accept-charset="utf-8">
		<table class="table table-bordered">
			<caption>문서 작성</caption>
			<tbody>
				<tr>
					<th>문서종류</th>
					<td colspan="2">
						<select name="doc_code_number">
						<c:forEach items="${codeList }" var="codeList">
							<option value="${codeList.code_number }">${codeList.code_name }</option>
						</c:forEach>
						</select>
					</td>
					<th>일반문서<input type="hidden" name="doc_eadoc" id="doc_eadoc"></th>
				</tr>
				<tr>
					<th>보존기간</th>
					<td colspan="3"><input type="date" id="doc_lifetime" name="doc_lifetime"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td colspan="3"><input type="text" id="doc_mem_number" name="doc_mem_number" value="${mem.mem_name }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>문서명</th>
					<td colspan="3"><input type="text" id="doc_name" name="doc_name"></td>
				</tr>
				<tr>
					<th colspan="4">설명</th>
				</tr>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
		<table class="table table-bordered">
			<tr>
				<th>파일</th>
				<td colspan="3"><input type="file" name="doc_file_name" onchange="javascript:file_change(this.value);"></td>
			</tr>
		</table>
		
		<div>
			<button type="button" onclick="saveContent();" class="btn">등록</button>
			<button class="btn"><a href="/documentManagement/documentManager/documentSelect" id="documentSelect">취소</a></button>
		</div>
	</form>
