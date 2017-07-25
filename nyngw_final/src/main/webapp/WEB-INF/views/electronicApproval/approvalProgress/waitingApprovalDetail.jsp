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
	$('#approvalDialog').css('display', 'none');
	$('#refuseDialog').css('display', 'none');
	$('#approvalHistoryDialog').css('display', 'none');
	/////////////////////////////////////////////////
	$("#approve_go").click(function(){
		$('#approvalDialog').dialog({
			width: 700,
			height: 500,
			modal: true,
			buttons:[{
				text: "등록",
				icon: "ui-icon-heart",
				click: function() {
					approvalSubmitCall();
					$( this ).dialog( "close" );
				}
			},{
				text: "취소",
				icon: "ui-icon-heart",
				click: function() {
					$( this ).dialog( "close" );
				}
			}],
			close: function() {
				$('#textArea').val('');
				$('#pwd').val('');
			}
		});
	})
     
	$("#refuse_go").click(function(){
		$('#refuseDialog').dialog({
			width: 700,
			height: 500,
			modal: true,
			buttons:[{
				text: "등록",
				icon: "ui-icon-heart",
				click: function() {
					refuseSubmitCall();
					$( this ).dialog( "close" );
				}
			},{
				text: "취소",
				icon: "ui-icon-heart",
				click: function() {
					$( this ).dialog( "close" );
				}
			}],
			close: function() {
				$('#textArea').val('');
				$('#pwd').val('');
			}
		});
	})
     
     
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
     
     
		$('#approvalHistory_go').click(function(){
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
		});
     
		var approvalSubmitCall = null;
		//결재하기//
		function approvalSubmit(){
			$.ajax({
				url:"/electronicApproval/approvalProgress/conformApproval",	// 결재처리 하는 컨트롤러 url
				type:"post",
				data: $("#approvalSubmitForm").serialize(),
				success: function(result){ // success
					if(result.check=='y'){
						if(result.al_number=='A'){
			 				var code="<div class='tableTd tableSign memSign' style='background-image:url(\"/resources/memsign/"+result.mem_sign+"\")'></div>";
			 				$("#approvalStatus"+result.priority).html(code);
			 				$("#approve_go").attr("disabled",true);
			 				$("#refuse_go").attr("disabled",true);
			 				alert("결재가 완료되었습니다.");
						}else if(result.al_number=='B'){
							var code="<div class='tableTd tableSign memSign' style='background-image:url(\"/resources/memsign/"+result.mem_sign+"\")'></div>";
			 				$("#agreementStatus"+result.priority).html(code);
			 				$("#approve_go").attr("disabled",true);
			 				$("#refuse_go").attr("disabled",true);
			 				alert("합의가 완료되었습니다.");
						}
					}else{
						alert("잘못된 비밀번호입니다.");
					}
				},
				dataType:"json"				// dataType
			});
		}	
		approvalSubmitCall=approvalSubmit;
		
		var refuseSubmitCall = null;
		//반려하기//
		function refuseSubmit(){
			$.ajax({
				url:"/electronicApproval/approvalProgress/conformApproval",	// 결재처리 하는 컨트롤러 url
				type:"post",
				data: $("#refuseSubmitForm").serialize(),
				success: function(result){ // success
					if(result.check=='y'){
						if(result.al_number=='A'){
		 				var code="<div class='tableTd tableSign memSign' style='background-image:url(\"/resources/memsign/refuse.jpg\")'></div>";
		 				$("#approvalStatus"+result.priority).html(code);
		 				$("#approve_go").attr("disabled",true);
		 				$("#refuse_go").attr("disabled",true);
		 				alert("반려가 완료되었습니다.");
						}else if(result.al_number=='B'){
							var code="<div class='tableTd tableSign memSign' style='background-image:url(\"/resources/memsign/refuse.jpg\")'></div>";
		 				$("#agreementStatus"+result.priority).html(code);
		 				$("#approve_go").attr("disabled",true);
		 				$("#refuse_go").attr("disabled",true);
		 				alert("거부가 완료되었습니다.");
						}
					}else{
						alert("잘못된 비밀번호입니다.");
					}
				},
				dataType:"json"				// dataType
			});
		}	
		refuseSubmitCall=refuseSubmit;
	///////////////////////////////////////////////
	})
})
</script>
결재진행>미결재문서함상세
<!-- 선택한 결재문서의 정보들을 가지고 들어와야하고 이 때 필요한 정보를 hidden에 담아 주자 -->
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>
<form name="hiddenForm">
	<input type="hidden" id="ea_number" value="${ea_number }">
	<c:choose>
		<c:when test="${mem_al_number eq 'A' }">
			<button type="button" id="approve_go">결재</button>
		</c:when>
		<c:otherwise>
			<button type="button" id="approve_go">합의</button>
		</c:otherwise>
	</c:choose>	
	<c:choose>
		<c:when test="${mem_al_number eq 'A' }">
			<button type="button" id="refuse_go">반려</button>
		</c:when>
		<c:otherwise>
			<button type="button" id="refuse_go">거부</button>
		</c:otherwise>
	</c:choose>	
<!-- 	<button type="button" id="insteadApprove_go">전결</button> -->
<!-- 	<button type="button" id="postpone_go">보류</button> -->
	<button type="button" id="approvalHistory_go">결재이력</button>
</form>

<!-- 결재하기 버튼 다이얼로그 -->
<c:choose>
	<c:when test="${mem_al_number eq 'A' }">
		<div id="approvalDialog">
			결재하기
			<form id="approvalSubmitForm">
				<%User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();%>
				<%=user.getUsername() %>님 결재하시겠습니까?<br>
				비밀번호 <input type="password" id="pwd" name="mem_pwd"><br>
				결재사유
				<input type="hidden" name="ah_ea_number" value="${eaVO.ea_number }">
				<input type="hidden" name="ah_code_number" value="code14">
				<input type="hidden" name="ah_ast_number" value="${ast_number}">
				<input type="hidden" name="ah_status" value="결재">
				<textarea id="textArea" name="ah_comment"></textarea>
			</form>   
		</div>
	</c:when>
	<c:otherwise>
		<div id="approvalDialog">
			합의하기
			<form id="approvalSubmitForm">
				<%User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();%>
				<%=user.getUsername() %>님 합의 하시겠습니까?<br>
				비밀번호 <input type="password" id="pwd" name="mem_pwd"><br>
				합의사유
				<input type="hidden" name="ah_ea_number" value="${eaVO.ea_number }">
				<input type="hidden" name="ah_code_number" value="code12">
				<input type="hidden" name="ah_ast_number" value="${ast_number}">
				<input type="hidden" name="ah_status" value="합의">
				<textarea id="textArea" name="ah_comment"></textarea>
			</form>   
		</div>
	</c:otherwise>
</c:choose>

<!-- 반려하기 버튼 다이얼로그 -->
<c:choose>
	<c:when test="${mem_al_number eq 'A' }">
		<div id="refuseDialog">
			반려하기
			<form id="refuseSubmitForm">
				<%User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();%>
				<%=user.getUsername() %>님 반려하시겠습니까?<br>
				비밀번호 <input type="password" id="pwd" name="mem_pwd"><br>
				반려사유
		<!-- 		code12	합의 -->
		<!-- 		code13	거부 -->
		<!-- 		code14	결재 -->
		<!-- 		code15	반대 -->
		<!-- 		code16	전결 -->
				<input type="hidden" name="ah_ea_number" value="${eaVO.ea_number }">
				<input type="hidden" name="ah_code_number" value="code15">
				<input type="hidden" name="ah_ast_number" value="${ast_number}">
				<input type="hidden" name="ah_status" value="반려">
				<textarea id="textArea" name="ah_comment"></textarea>
			</form>   
		</div>
		</c:when>
	<c:otherwise>
		<div id="refuseDialog">
			거부하기
			<form id="refuseSubmitForm">
				<%User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();%>
				<%=user.getUsername() %>님 거부하시겠습니까?<br>
				비밀번호 <input type="password" id="pwd" name="mem_pwd"><br>
				반려사유
		<!-- 		code12	합의 -->
		<!-- 		code13	거부 -->
		<!-- 		code14	결재 -->
		<!-- 		code15	반대 -->
		<!-- 		code16	전결 -->
				<input type="hidden" name="ah_ea_number" value="${eaVO.ea_number }">
				<input type="hidden" name="ah_code_number" value="code13">
				<input type="hidden" name="ah_ast_number" value="${ast_number}">
				<input type="hidden" name="ah_status" value="거부">
				<textarea id="textArea" name="ah_comment"></textarea>
			</form>   
		</div>
	</c:otherwise>
</c:choose>


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
			<td colspan="6">
				${eaVO.ea_content }
			</td>	
		</tr>
	</table>
</div>