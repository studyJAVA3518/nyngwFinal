<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>반려문서함</h2>
<p class="docTitleDescription">
	반려문서는 본인이 반려한 문서 또는 본인이 결재한 문서를 다른 결재자가 반려한 문서들을 확인할 수 있는 메뉴입니다.
</p>
<div class="eaSearchDivMagin">
	<form>
		검색일자&nbsp;
			<select name="EADateOption" class="form-control docInputSelect">
				<option >반려일</option>
				<option >기안일</option>
			</select>&nbsp;&nbsp;
		문서검색&nbsp;
			<select name="docSearchOption" class="form-control docInputSelect">
				<option value="all">--선택--</option>
				<option value="ea_title">제목</option>
				<option value="ea_number">품의번호</option>
				<option value="doc_name">문서분류</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="searchText" class="form-control eaInputSearch">&nbsp;&nbsp;
		<button type="button" onclick="searchRefusedApproval_go(this.form);" class="btn btn-default">검색</button>
	</form>
</div>
<table class="table textCenter">
	<tr>
		<th>품의번호</th>
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>반려일</th>
		<th>상태</th>
	</tr>
	<c:if test="${empty EAList }">
		<tr><td colspan="7">반려 문서가 없습니다!</td></tr>
	</c:if>
	<c:forEach items="${EAList }" var="EA" varStatus="status">
		<tr>
			<td class="ea_number">${EA.ea_number }</td>
			<td>${EA.doc_name }</td>
			<td class="approvalHistory_go" style="color: blue;">${EA.ea_title }</td>
			<td>${EA.mem_name}</td>
			<td><fmt:formatDate value="${EA.ea_writedate}" pattern="yyyy/MM/dd"/>
			<td><fmt:formatDate value="${EA.ah_time}" pattern="yyyy/MM/dd"/>
			</td>
			<td>${EA.ah_status }</td>
		</tr>
	</c:forEach>
</table>
<div id="approvalHistoryDialog" class="textCenter">
	<h2>결재상태 이력보기</h2>
	<table class="table tableGray" id="historyList">
		<tr>
			<th>부서</th>
			<th>직급</th>
			<th>이름</th>
			<th>결재종류</th>
			<th>결재시간</th>
		</tr>
	</table>
</div>
<script>
	function searchRefusedApproval_go(form){
		form.method="get";
		form.action="/electronicApproval/individualDocumentBox/searchRefusedApproval";
		form.submit();
	} 
	
	$(function(){
		$('#approvalHistoryDialog').css('display', 'none');
		$(".approvalHistory_go").click(function(){
			var tmp = $(this).siblings('.ea_number').text();
	        $.ajax({
	           url:'/electronicApproval/individualDocumentBox/completeAllrovalDetail',
	           type:'get',
	           data: {'ea_number' : tmp},
	           success : function(res){
	       		   var code = "<tbody>";
	       			$("#historyList").html(code);
	        	   $.each(res, function (i,value){
	        		   code+='<tr><td>'+value.dept_name+'</td>';
	        		   code+='<td>'+value.position_name+'</td>';
	        		   code+='<td>'+value.mem_name+'</td>';
	        		   code+='<td>'+value.ah_status+'</td>';
	        		   code+='<td>'+value.ah_time+'</td></tr>';
	        	   });
	        	   code+="</tbody>";
					$("#historyList").append(code);
	           },
	           dataType : 'json'
	        })
			
			$('#approvalHistoryDialog').dialog({
				width: 700,
				height: 500,
				modal: true,
				buttons: {
			       "취소": function() {
						$(this).dialog("close");
					}
				},
				close: function() {
					$('#textArea').val('');
				}
		    });
	    })
	    ///////////////////////////////////////////////
		$("#editDraft_go").click(function(){
			location.href="/electronicApproval/individualDocumentBox/editDraftForm";
		});
		
	})
</script>
