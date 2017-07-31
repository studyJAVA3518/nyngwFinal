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
	$(function(){
		$.ajax({
			url:'/electronicApproval/draft/approvalLineManager',
			type:'get',
			success : function(result){
				$("#tree").html(result.sb);
			},
			dataType : 'json'
		})
		
		//부서 수정 창 숨기기
		$('#linePopup').css('display', 'none');
		
		//부서 수정 보여주기
		$("#linePopup_go").click(function(){
			$('#linePopup').dialog({
				width:950,
				height: 700,
				modal: true,
				buttons: [{
					text: "등록",
					icon: "ui-icon-heart",
					click: function() {
						submitLineCall();
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
					
				}
			});
		});
		
		var submitLineCall = null;
		//결재라인 등록하기//
		function submitLine(){
			$('#approval option').each(function(i){
				$('#approvalMember'+(i+1)).html($(this).val()+'<input type="hidden" name="approvalMember'+(i+1)+'" value="'+$(this).attr('id')+'">');
			});
			$('#agreement option').each(function(i){
				$('#agreementMember'+(i+1)).html($(this).val()+'<input type="hidden" name="agreementMember'+(i+1)+'" value="'+$(this).attr('id')+'">');
			});
			
			//시행자
			var addImplementMemberNumbers = "";	//시행자의 아이디를 담아주기 위한 변수
			var code = "";
			$('#implement option').each(function(i){
				if(i>0){
					code +=",";
					addImplementMemberNumbers+=',';
				}
				code +=$(this).val();
				addImplementMemberNumbers+=$(this).attr('id');
			});
			code += '<input type="hidden" name="implementMembers" value="'+addImplementMemberNumbers+'">';
			$('#implementMembers').html(code);
			
			//참조자
			var addReferenceMemberNumbers = "";
			code = "";
			$('#reference option').each(function(i){
				if(i>0){
					code +=",";
					addReferenceMemberNumbers+=',';
				}
				code +=$(this).val();
				addReferenceMemberNumbers += $(this).attr('id');
			});
			code += '<input type="hidden" name="referenceMembers" value="'+addReferenceMemberNumbers+'">';
			$('#referenceMembers').html(code);
		}
		submitLineCall=submitLine;
		
	});
</script>

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

기안하기>결재 재상신 페이지<br>


<div id="linePopup" style="height:100%;width:100%;">
	<jsp:include page="../draft/approvalLineManager.jsp" flush="false"/>
</div>
<button type="button" id="linePopup_go">결재라인</button>
<form enctype="multipart/form-data" name="tx_editor_form" id="tx_editor_form" action="/electronicApproval/individualDocumentBox/editDraft" method="post" accept-charset="utf-8">
	<input type="hidden" name="ea_doc_number" value="${eaVO.ea_doc_number }">
	<table class="table table-bordered">
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
			<c:forEach items="${approvalMember}" var="member" varStatus="index">
				<th id="approvalMember${index.count }" class="tableTd">${member.mem_name }</th>
			</c:forEach>
			<c:forEach var="i" begin="${emptyStartA }" end="5" step="1">
				<td id="approvalMember${i}" class="tableTd"></td>
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
			<c:forEach items="${agreementMember}" var="member" varStatus="index">
				<th id="agreementMember${index.count}" class="tableTd">${member.mem_name }</th>
			</c:forEach>
			<c:forEach var="i" begin="${emptyStartB }" end="5" step="1">
				<td id="agreementMember${i}"  class="tableTd"></td>
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
			<td id="implementMembers" colspan="5">${implementMemberName}</td>
		</tr>
		
		<tr>
			<th class="tableTh">수신 및 참조</th>
			<td id="referenceMembers" colspan="5">${referenceMemberName}</td>
		</tr>
		
		<tr>
			<th>시행일자</th>
			<td colspan="5"><input type="date" name="param_ea_startdate" value="${eaVO.ea_startdate }">~<input type="date" name="param_ea_enddate" value="${eaVO.ea_enddate }"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="5"><input type="text" name="ea_title" value="${eaVO.ea_title }"></td>
		</tr>
		<tr>
			<th colspan="6">내용</th>
		</tr>
	</table>
	<!-- ea_content -> content -->
	<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
</form>
<input type="hidden" value="${page}">
<button type="button" onclick="saveContent();">등록</button>
<input type="reset" value="초기화" />	
<a href="/sharingInformation/board/list?page=${page }"><button type="button">취소</button></a>


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