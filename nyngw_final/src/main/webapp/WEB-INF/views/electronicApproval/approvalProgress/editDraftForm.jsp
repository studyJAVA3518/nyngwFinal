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

<script>
	function approvalLine_go(){
		location.href="/electronicApproval/draft/approvalLineManager";
	}
	//텍스트 데이터를 가져가야 한다
	//upload를 하던지
	function submitApproval_go(){
		location.href="/electronicApproval/draft/submitApproval";
	}
</script>
<h2>결재 재상신 페이지</h2>
<form enctype="multipart/form-data" name="tx_editor_form" id="tx_editor_form" action="/electronicApproval/approvalProgress/editDraft" method="post" accept-charset="utf-8">
	<input type="hidden" name="ea_doc_number" value="${eaVO.ea_doc_number }">
	<table class="table table-bordered tableGray">
		<tr>
			<th>품의번호</th>
			<td colspan="5">
				${eaVO.ea_number }
				<input type="hidden" name="ea_number" value="${eaVO.ea_number }">
			</td>
			
		</tr>
		<tr>
			<th>작성일자</th>
			<td colspan="5">
				${eaVO.ea_writedate }
			</td>
		</tr>
		<tr>
			<th>기안부서</th>
			<td colspan="5">${member.dept_name }</td>
		</tr>
		<tr>
			<th>기안자</th>
			<td colspan="5">
				${member.mem_name }
				<input type="hidden" name="ea_mem_number" value="${member.mem_number }">
			</td>
			
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
			<th>시행일자</th>
			<td colspan="5"><input type="text" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01" name="param_ea_startdate" value="${eaVO.ea_startdate }">&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01" name="param_ea_enddate" value="${eaVO.ea_enddate }"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="5"><input type="text" name="ea_title" class="form-control" value="${eaVO.ea_title }"></td>
		</tr>
		<tr>
			<th colspan="6">내용</th>
		</tr>
	</table>
	<!-- ea_content -> content -->
	<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
</form>

<div class="insertJoinBtnWrap textCenter">
	<input type="hidden" value="${page}">
	<button type="button" onclick="saveContent();" class="btn btn-default">등록</button>
	<input type="reset" value="초기화"  class="btn btn-default"/>	
	<a href="/electronicApproval/approvalProgress/refusedApprovalDetail?ea_number=${eaVO.ea_number }"><button type="button" class="btn btn-default">취소</button></a>
</div>


<textarea id="text_content" style="display:none;">
${eaVO.ea_content}
</textarea>
<script type="text/javascript">
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