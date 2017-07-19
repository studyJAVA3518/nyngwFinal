<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	$(function(){
		$.ajax({
			url:'approvalLineManager',
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
				buttons: {
			       "취소": function() {
						$(this).dialog("close");
					}
				},
				close: function() {
					
				}
			});
		})
	})
</script>

<script type="text/javascript">
	function file_change(file){
	var str=file.lastIndexOf("\\")+1;   //파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
	file = file.substring(str, file.length);
// 	document.getElementsByName('board_file_name')[0].value=file;
	}
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

<div id="linePopup" style="height:100%;width:100%;">
	<jsp:include page="approvalLineManager.jsp" flush="false"/>
</div>

기안하기>결재상신페이지
<button type="button" id="linePopup_go">결재라인</button>
<button type="button" onclick="submitApproval_go()">상신하기</button>
<form enctype="multipart/form-data" name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="write" method="post" accept-charset="utf-8">
	<table class="table table-bordered">
		<tr>
			<th>품의번호</th>
			<td colspan="5">"새로 생성되는 번호"(ea_number)</td>
			<input type="hidden" name="" value="ea_number">
		</tr>
		
		<input type="hidden" name="ea_doc_number" value="ea_number">
		<tr>
			<th>작성일자</th>
			<td colspan="5">sysdate</td>
			<input type="hidden" name="" value="ea_enddate">
		</tr>
		<tr>
			<th>기안부서</th>
			<td colspan="5">"작성자의 부서"</td>
			<input type="hidden" name="" value="ea_mem_number->mem_dept_number->mem_dept_name">
		</tr>
		<tr>
			<th>기안자</th>
			<td colspan="5">"작성자의 이름"</td>
			<input type="hidden" name="ea_mem_number">
		</tr>
		<tr id="approvalMember">
			<th rowspan="2">결재</th>
			<td><input type="hidden" id="approvalMember1" name="approvalMember1"></td>
			<td><input type="hidden" id="approvalMember2" name="approvalMember2"></td>
			<td><input type="hidden" id="approvalMember3" name="approvalMember3"></td>
			<td><input type="hidden" id="approvalMember4" name="approvalMember4"></td>
			<td><input type="hidden" id="approvalMember5" name="approvalMember5"></td>
		</tr>                                
		<tr id="approvalStatus">                                 
			<td><input type="hidden" id="approvalStatus1" name="approvalStatus1"></td>
			<td><input type="hidden" id="approvalStatus2" name="approvalStatus2"></td>
			<td><input type="hidden" id="approvalStatus3" name="approvalStatus3"></td>
			<td><input type="hidden" id="approvalStatus4" name="approvalStatus4"></td>
			<td><input type="hidden" id="approvalStatus5" name="approvalStatus5"></td>
		</tr>                                
		<tr id="agreementMember">                                 
			<th rowspan="2">합의</th>        
			<td><input type="hidden" id="agreementMember1" name="agreementMember1"></td>
			<td><input type="hidden" id="agreementMember2" name="agreementMember2"></td>
			<td><input type="hidden" id="agreementMember3" name="agreementMember3"></td>
			<td><input type="hidden" id="agreementMember4" name="agreementMember4"></td>
			<td><input type="hidden" id="agreementMember5" name="agreementMember5"></td>
		</tr>                                
		<tr id="agreementStatus">                               
			<td><input type="hidden" id="agreementStatus1" name="agreementStatus1"></td>
			<td><input type="hidden" id="agreementStatus2" name="agreementStatus2"></td>
			<td><input type="hidden" id="agreementStatus3" name="agreementStatus3"></td>
			<td><input type="hidden" id="agreementStatus4" name="agreementStatus4"></td>
			<td><input type="hidden" id="agreementStatus5" name="agreementStatus5"></td>
		</tr>
		<tr>
			<th>수신 및 참조</th>
			<td colspan="5"><input type="hidden" name="" value=""></td>
		</tr>
		<tr>
			<th>시행일자</th>
			<td colspan="5"><input type="date" name="ea_startdate">~<input type="date" name="ea_enddate"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="5"><input type="text" name="ea_title"></td>
		</tr>
		<tr>
			<th colspan="6">내용</th>
		</tr>
	</table>
	<!-- ea_content -> content -->
	<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
	<table>
		<tr>
			<th>첨부파일</th>
			<td colspan="3"><input type="file" name="board_file_name" onchange="javascript:file_change(this.value);"></td>	
		</tr>
	</table>
</form>
<input type="hidden" value="${page}">
<button type="button" onclick="saveContent();">등록</button>
<input type="reset" value="초기화" />	
<button type="button"><a href="/sharingInformation/board/list?page=${page }">취소</a></button>