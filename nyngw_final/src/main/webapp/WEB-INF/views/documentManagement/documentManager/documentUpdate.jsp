<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	label{
		width:200px;
	}
</style>
 수정페이지입니다.
	 <div>
		<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form"  action="/documentManagement/documentManager/documentUpdate" method="post">
		<table class="table table-bordered">
			<tr>
				<th>문서구분번호</th><td>${document.doc_code_number }<input type="hidden" value="${document.doc_code_number}" name="doc_code_number" readonly="readonly"></td>
				<th>문서번호</th><td>${document.doc_number }<input type="hidden" value="${document.doc_number}" name="doc_number" readonly="readonly"></td>
			</tr>
			<tr>
				<th>문서명</th><td><input type="text" value="${document.doc_name }" name="doc_name"></td>
				<th>등록자</th><td>${document.doc_mem_number }<input type="hidden" value="${document.doc_mem_number}" name="doc_mem_number" readonly="readonly"></td>
			</tr>
			<tr>
				<th>등록일자</th><td><fmt:formatDate value="${document.doc_date}" pattern="yyyy/MM/dd"/></td>
				<th>보존기간</th><td><fmt:formatDate value="${document.doc_lifetime}" pattern="yyyy/MM/dd"/></td>
			</tr>
			<tr>
				<th colspan="4">설명</th>
			</tr>
		</table>
		<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
		<table class="table table-bordered">
			<tr>
				<th>파일이름</th>
				<td colspan="3">${document.doc_file_name }<input type="hidden" value="${document.doc_file_name}" name="doc_file_name" readonly="readonly"></td>
			</tr>
		</table>
		
			<input type="hidden" value="${page}">
			<button type="button" onclick="saveContent();" class="btn">수정</button>
			<input type="reset" value="초기화" />		
			<button type="button"><a href="/documentManagement/documentManager/documentSelect?page=${page }">취소</a></button>
		</form>
	</div> 
<textarea id="text_content" style="display:none;">
${document.doc_explanation}
</textarea>
<script>
$(function(){   
	var loadContent = function() {
		/* 저장된 컨텐츠를 불러오기 위한 함수 호출 */
		Editor.modify({
			"content": document.getElementById("text_content") /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
		});
	};
	
	loadContent();
});
</script>	
