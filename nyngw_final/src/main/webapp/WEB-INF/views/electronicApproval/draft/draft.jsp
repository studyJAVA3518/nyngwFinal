<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>기안하기</h2>
<p class="docTitleDescription">
기안하기는 상신할 결재문서를 작성하는 메뉴입니다.<br>
기안 폴더에 등록되어 있는 문서 종류를 클릭하면 기안문서를 작성할 수 있는 기안작성 팝업이 뜹니다. <br>
근태신청서는 조직원이 개별적으로 휴가, 출장, 훈련, 조퇴 등을 신청하는 메뉴입니다. 
</p>
<script>
$(function(){  
	$('#draftBoxOption option[value="${draftBoxOption}"]').prop('selected',true);
	$('#searchOption option[value="${searchOption}"]').prop('selected',true);
});
</script>
	<div>
		<form>
			<div class="eaSearchDivMagin">
			기안문서함&nbsp;
				<select name="draftBoxOption" id="draftBoxOption" class="form-control docInputSelect">
					<option value="">--선택--</option>
					<option value="code8">총무문서</option>
					<option value="code9">기획문서</option>
					<option value="code10">대외문서</option>
				</select>&nbsp;&nbsp;
				검색어&nbsp;
				<select name="searchOption" id="searchOption" class="form-control docInputSelect">
					<option value="">--선택--</option>
					<option value="doc_name">문서명</option>
					<option value="doc_explanation">문서설명</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="searchText"  class="form-control eaInputSearch">&nbsp;&nbsp;
				<button type="button" onclick="searchDraft_go(this.form);"  class="btn btn-default">검색</button>
			</div>
		</form>	
	</div>

	<div class="insertJoinBtnWrap textCenter">
		<table class="table table-bordered tableGray">
			<tr>
				<th>기안문서함</th>
				<th>문서명</th>
				<th>문서설명</th>
				<th>문서등록일</th>
			</tr>
			
			<c:forEach items="${documentList }" var="document" varStatus="status">
				<tr>
					<td>${code_nameList[status.index].code_name }</td>
					<td><a href="/electronicApproval/draft/createDraftForm?doc_number=${document.doc_number }">${document.doc_name }</a></td>
					<td>${document.doc_explanation }</td>
					<td><fmt:formatDate value="${document.doc_date}" pattern="yyyy/MM/dd"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>	

<script>
	function searchDraft_go(form){
		form.method="get";
		form.action="/electronicApproval/draft/searchDraftDocument";
		form.submit();
	} 
</script>
