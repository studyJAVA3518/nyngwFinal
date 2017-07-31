<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.tableTd1{
		 width:111px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd2{
		 width:75px;
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd3{
		 width:233px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd4{
		 width:160px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd5{
		 width:213px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd6{
		 width:700px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
</style>

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
	       		   var code = "<tr><th class='tableTd1'>부서</th><th class='tableTd2'>직급</th><th class='tableTd3'>이름</th><th class='tableTd4'>결재상태</th><th class='tableTd5'>결재일</th></tr>";
	        	   if(res == ""){
						code +="<tr><td colspan='5' class='tableTd6'>결재이력이 없습니다.</td></tr>";
	        	   }
	       		   $.each(res, function (i,value){
	        		   code+='<tr><td class="tableTd1">'+value.dept_name+'</td>';
	        		   code+='<td class="tableTd2">'+value.position_name+'</td>';
	        		   code+='<td class="tableTd3">'+value.mem_name+'</td>';
	        		   code+='<td class="tableTd4">'+value.ah_status+'</td>';
	        		   code+='<td class="tableTd5">'+value.ah_time+'</td></tr>';
	        	   });
					$("#historyList").html(code);
	           },
	           dataType : 'json'
	        })
			
			$('#approvalHistoryDialog').dialog({
				width: 700,
				height: 500,
				modal: true,
				buttons: {
			       "확인": function() {
						$(this).dialog("close");
					}
				},
				close: function() {
// 					$('#historyList').html('');
				}
		    });
	    })
	    ///////////////////////////////////////////////
		$("#editDraft_go").click(function(){
			location.href="/electronicApproval/individualDocumentBox/editDraftForm";
		});
		
	})
</script>

<div id="approvalHistoryDialog" class="textCenter">
	<h2>결재상태 이력보기</h2>
	<table class="table" id="historyList">
		<tr>
			<th>부서</th>
			<th>직급</th>
			<th>이름</th>
			<th>결재상태</th>
			<th>결재일</th>
		</tr>
	</table>
</div>
<h2>참조문서함</h2>
<p class="docTitleDescription">
	참조문서함은 본인이 수신참조인으로 지정된 결재문서를 확인 할 수 있는 메뉴입니다.
</p>
<div class="eaSearchDivMagin">
	<form>
		검색일자&nbsp;
			<select name="EADateOption" class="form-control docInputSelect">
				<option value="ea_writedate">기안일</option>
				<option value="ea_startdate">시작일</option>
				<option value="ea_enddate">종료일</option>
			</select>
			&nbsp;&nbsp;&nbsp;문서검색&nbsp;
			<select name="docSearchOption" class="form-control docInputSelect">
				<option value="all">--선택--</option>
				<option value="ea_title">제목</option>
				<option value="ea_number">품의번호</option>
				<option value="doc_name">문서분류</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="searchText" class="form-control eaInputSearch">&nbsp;&nbsp;
		<button type="button" onclick="searchReferenceDocument_go(this.form);" class="btn btn-default">검색</button>
	</form>
</div>

<div class="insertJoinBtnWrap textCenter">
	<table class="table textCenter" border="1">
		<tr>
			<th>품의번호</th>
			<th>문서분류</th>
			<th>제목</th>
			<th>기안자</th>
			<th>기안일</th>
			<th>시행일</th>
		</tr>
	
		<!-- EA=electronicApproval (전자결재) -->
		<c:if test="${empty EAList }">
			<tr><td colspan="7">참조자로 선정된 문서가 없습니다!</td></tr>
		</c:if>
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
			</tr>
		</c:forEach>
	</table>
</div>
