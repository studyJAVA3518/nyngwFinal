<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
그외문서함>참조문서함
참조문서함은 본인이 수신참조인으로 지정된 결재문서를 확인 할 수 있는 메뉴입니다.
<form>
	<table class="table">
		<tr>
			<td>검색일자</td>
			<td>
				<select name="EADateOption">
					<option value="ea_writedate">기안일</option>
					<option value="ea_startdate">시작일</option>
					<option value="ea_enddate">종료일</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>문서검색</td>
			<td>
				<select name="docSearchOption">
					<option value="all">--선택--</option>
					<option value="ea_title">제목</option>
					<option value="ea_number">품의번호</option>
					<option value="doc_name">문서분류</option>
				</select>
			</td>
			<td>
				<input type="text" name="searchText">
			</td>	
		</tr>		
	</table>
	<button type="button" onclick="searchReferenceDocument_go(this.form);">검색</button>
</form>

<table class="table" border="1">
	<tr>
		<th>품의번호</th>
		<th>문서분류</th>
		<th>제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>시행일</th>
		<th>상태</th> 
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:forEach items="${EAList }" var="EA" varStatus="status">
		<tr>
			<td class="ea_number">${EA.ea_number }</td>
			<td>${EA.doc_name }</td>
			<td class="approvalHistory_go" style="color: blue;">${EA.ea_title }</td>
			<td>${EA.mem_name}</td>
			<td><fmt:formatDate value="${EA.ea_writedate}" pattern="yyyy/MM/dd"/>
			<td><fmt:formatDate value="${EA.ea_startdate}" pattern="yyyy/MM/dd"/>
					~
				<fmt:formatDate value="${EA.ea_enddate}" pattern="yyyy/MM/dd"/>
			</td>
			<td>${EA.ah_status }</td>
		</tr>
	</c:forEach>
</table>
<div id="approvalHistoryDialog">
	결재상태 이력보기
	<table class="table" id="historyList">
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
	function searchReferenceDocument_go(form){
		form.method="get";
		form.action="/electronicApproval/theRestDocumentBox/searchReferenceDocument";
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
	       		   var code = "";
	        	   $.each(res, function (i,value){
	        		   code+='<tr><td>'+value.dept_name+'</td>';
	        		   code+='<td>'+value.position_name+'</td>';
	        		   code+='<td>'+value.mem_name+'</td>';
	        		   code+='<td>'+value.ah_status+'</td>';
	        		   code+='<td>'+value.ah_time+'</td></tr>';
	        	   });
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
