<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
%>
<script>
function insert_error_go(){
	
	//DatePicker사용시 정규식 사용
	var doc_lifetime = document.getElementById("doc_lifetime").value;
	var format = /^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	if(!format.test(doc_lifetime)){
	   alert("보존기간은 2010-02-01 형식으로 입력해야합니다.\r\n(1970-01-01부터 2099-12-31까지 검색 가능합니다.)");
	   return false;
	}
    
    if(!document.getElementById("doc_name").value){
       alert("제목을 입력하십시오.");
       return;
    }else if(!document.getElementById("doc_lifetime").value){
    	alert("보존기간를 입력하시오.");
    	return;
    }else{
    	saveContent();
    }
    
 }
</script>

<h2>전자문서 등록하기</h2>
<p class="docTitleDescription">
	전자 문서를 등록할 수 있습니다.
</p>

<form name="tx_editor_form" id="tx_editor_form" action="edocumentInsertComplete" method="POST" accept-charset="utf-8">
	<table class="table table-bordered tableGray">
		<caption>문서 작성</caption>
		<tbody>
			<tr>
				<th>문서종류</th>
				<td colspan="2"><select name="doc_code_number" class="form-control docInputSelect">
						<c:forEach items="${codeList2 }" var="codeList2">
							<option value="${codeList2.code_number }">${codeList2.code_name }</option>
						</c:forEach>
				</select></td>
				<th>전자결재문서<input type="hidden" name="doc_eadoc" id="doc_eadoc" class="docInputSearch form-control"></th>
			</tr>
			<tr>
				<th>보존기간</th>
				<td colspan="3"><input type="text" id="doc_lifetime" name="doc_lifetime" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td colspan="3"><input type="text" id="doc_mem_number" class="form-control"
					name="doc_mem_number" value="${mem.mem_name }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>문서명</th>
				<td colspan="3"><input type="text" id="doc_name" class="form-control" name="doc_name"></td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp"	flush="false" />

	<div class="insertJoinBtnWrap textCenter">
		<button type="button" onclick="insert_error_go();" class="btn btn-default">등록</button>
		<button class="btn btn-default">
			<a href="/documentManagement/documentManager/edocumentSelect"id="edocumentSelect">취소</a>
		</button>
	</div>
</form>
