<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.tableTd{
 		width: 17%;
 		height: 50px;
	}
	.tableSign{
 		height: 80px;
	}
	.memSign {
		display : block;
		width : 80px;
		height : 60px;
		margin :0 auto;
		background-size: contain;
		background-repeat: no-repeat;
		background-position : top center;
	}
</style>

<script type="text/javascript">
$(function(){
	 $('#editHistoryDialog').css('display', 'none');
	 
	/////////////////////////////////////////////////
	$("#editHistory_go").click(function(){
		$('#editHistoryDialog').dialog({
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
// 	$("#editDraft_go").click(function(){
// 		location.href="/electronicApproval/individualDocumentBox/editDraftForm?doc_number=${eaVO.ea_doc_number }&ea_number=${eaVO.ea_number }";
// 	});
	
})
</script>

<div id="editHistoryDialog">
	수신 및 참조 수정 내용
	<table class="table">
		<tr>
			<th>순서</th>
			<th>성명</th>
		</tr>
		<tr>
			<th colspan="2">날짜</th>
		</tr>
		<tr>
			<td><input type="radio" checked="checked">1<td>
			<td>정혜리<td> 
		</tr>
		<tr>
			<td colspan="2">2009-03-29 10:08:50</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<th>회사</th>
			<th>부서</th>
			<th>사용자</th>
		</tr>
		<tr>
			<td>회사</td>
			<td>부서</td>
			<td>사용자</td>
		</tr>
	</table>
</div>

개인문서함>상신문서함>디테일
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<c:if test="${indexA == 0 and indexB == 0}">
	<form name="hiddenForm">
		<input type="hidden" name="ea_number" value="${eaVO.ea_number }">
		<button type="button" onclick="editDraft_go(this.form)">수정</button>
	</form>
</c:if>


<div>
	<input type="hidden" name="ea_doc_number" value="${eaVO.ea_doc_number }">
	<table class="table table-bordered">
		<tr>
			<th class="tableTh">품의번호</th>
			<td colspan="5">${eaVO.ea_number }</td>
			<input type="hidden" name="ea_number" value="${eaVO.ea_number }">
		</tr>
		<tr>
			<th class="tableTh">작성일자</th>
			<td colspan="5">${eaVO.ea_writedate }</td>
		</tr>
		<tr>
			<th class="tableTh">기안자</th>
			<td colspan="5">${mem_name }</td>
		</tr>
		<!-- 결재자 이름-->   
		<tr id="approvalMember">
			<th rowspan="2">결재</th>
			<c:forEach items="${approvalMember}" var="member">
				<th class="tableTd">${member.mem_name }</th>
			</c:forEach>
			<c:forEach var="i" begin="1" end="${lastAstPriorityOfA }" step="1">
				<th class="tableTd"></th>
			</c:forEach>
		</tr>   
		<!-- 결재자 서명 -->                              
		<tr id="approvalStatus">                                 
			<c:forEach items="${approvalMem_sign}" var="sign" varStatus="index">
				<td id="approvalStatus${index.count}"><div class="tableTd tableSign memSign" style="background-image:url('/resources/memsign/${sign }')"></div></td>
			</c:forEach>
			<c:forEach var="i" begin="${noStartA }" end="${noEndA }" step="1">
				<td id="approvalStatus${i}" class="tableTd tableSign"></td>
			</c:forEach>
			<c:forEach var="i" begin="${emptyStartA }" end="5" step="1">
				<td id="approvalStatus${i}" class="tableTd tableSign"></td>
			</c:forEach>
		</tr>      
		<!-- 합의자 이름 -->                          
		<tr id="agreementMember">                                 
			<th rowspan="2">합의</th>        
			<c:forEach items="${agreementMember}" var="member">
				<th class="tableTd">${member.mem_name }</th>
			</c:forEach>
			<c:forEach var="i" begin="1" end="${lastAstPriorityOfB}" step="1">
				<th class="tableTd"></th>
			</c:forEach>
		</tr>   
		<!-- 합의자 서명 -->                               
		<tr id="agreementStatus"> 
			<c:forEach items="${agreementMem_sign}" var="sign" varStatus="index">  
				<td id="agreementStatus${index.count}"><div class="memSign tableTd tableSign" style="background-image:url('/resources/memsign/${sign }')"></div></td>                            
			</c:forEach>
			<c:forEach var="i" begin="${noStartB }" end="${noEndB}" step="1">
				<td id="agreementStatus${i}"  class="tableTd tableSign"></td>
			</c:forEach>
			<c:forEach var="i" begin="${emptyStartB }" end="5" step="1">
				<td id="agreementStatus${i}"  class="tableTd tableSign"></td>
			</c:forEach>
		</tr>
		<tr>
			<th class="tableTh">시행자</th>
			<td colspan="5">${implementMemberName}</td>
		</tr>
		<tr>
			<th class="tableTh">수신 및 참조</th>
			<td colspan="5">${referenceMemberName}</td>
		</tr>
		<tr>
			<th class="tableTh">시행일자</th>
			<td colspan="5">${eaVO.ea_startdate } ~ ${eaVO.ea_enddate}</td>
		</tr>
		<tr>
			<th class="tableTh">제목</th>
			<td colspan="5">${eaVO.ea_title }</td>
		</tr>
		<tr>
			<th colspan="6">내용</th>
		</tr>
		<tr>
			<td colspan="6" >
				<div class="textCenter aa">
					${eaVO.ea_content }
				</div>
			</td>	
		</tr>
	</table>
	<a href="/electronicApproval/approvalProgress/waitingApproval"><button type="button">뒤로</button></a>
</div>
<style>
	div.aa table {
		 margin : 0 auto;
	}
</style>

<script>
	function editDraft_go(form){
		form.method="get";
		form.action="/electronicApproval/individualDocumentBox/editDraftForm";
		form.submit();
	} 
</script>