<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<style>
	.tableTd{
 		width: 17%;
 		height: 50px;
	}
	.tableSign{
 		height: 80px;
	}
</style>

<script>
//DatePicker사용시 정규식 사용
// var doc_lifetime = document.getElementById("doc_lifetime").value;
// var format = /^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
// if(!format.test(doc_lifetime)){
//    alert("시작일은 2010-02-01 형식으로 입력해야합니다.\r\n(1970-01-01부터 2099-12-31까지 검색 가능합니다.)");
//    return false;
// }
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
				width:1000,
				height: 750,
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

<h2>결재상신페이지</h2>
<div class="textCenter">
	<form enctype="multipart/form-data" name="tx_editor_form" id="tx_editor_form" action="/electronicApproval/draft/submitApproval" method="post" accept-charset="utf-8">
		<input type="hidden" name="ea_doc_number" value="${doc_number }">
		<table class="table table-bordered tableGray">
			<tr>
				<th>품의번호</th>
				<td colspan="5">${ea_number }</td>
				<input type="hidden" name="ea_number" value="${param_ea_number }">
				<input type="hidden" name="param_ea_number" value="${ea_number }">
			</tr>
			<tr>
				<th>작성일자</th>
				<td colspan="5">${ea_writedate }</td>
				<input type="hidden" name="param_ea_writedate" value="${ea_writedate }">
			</tr>
			<tr>
				<th>기안부서</th>
				<td colspan="5">${member.dept_name }</td>
			</tr>
			<tr>
				<th>기안자</th>
				<td colspan="5">${member.mem_name }</td>
				<input type="hidden" name="ea_mem_number" value="${member.mem_number }">
			</tr>
			<tr id="approvalMember">
				<th rowspan="2">결재<br><button type="button" id="linePopup_go" class="btn btn-default">결재라인</button></th>
				<th id="approvalMember1" class="tableTd"></th>
				<th id="approvalMember2" class="tableTd"></th>
				<th id="approvalMember3" class="tableTd"></th>
				<th id="approvalMember4" class="tableTd"></th>
				<th id="approvalMember5" class="tableTd"></th>
			</tr>                                
			<tr id="approvalStatus">                                 
				<td id="approvalStatus1" class="tableSign"></td>
				<td id="approvalStatus2" class="tableSign"></td>
				<td id="approvalStatus3" class="tableSign"></td>
				<td id="approvalStatus4" class="tableSign"></td>
				<td id="approvalStatus5" class="tableSign"></td>
			</tr>                                
			<tr id="agreementMember">                                 
				<th rowspan="2">합의</th>        
				<th id="agreementMember1" class="tableTd"></th>
				<th id="agreementMember2" class="tableTd"></th>
				<th id="agreementMember3" class="tableTd"></th>
				<th id="agreementMember4" class="tableTd"></th>
				<th id="agreementMember5" class="tableTd"></th>
			</tr>                                
			<tr id="agreementStatus">                               
				<td id="agreementStatus1" class="tableSign"><input type="hidden" name="agreementStatus1"></td>
				<td id="agreementStatus2" class="tableSign"><input type="hidden" name="agreementStatus2"></td>
				<td id="agreementStatus3" class="tableSign"><input type="hidden" name="agreementStatus3"></td>
				<td id="agreementStatus4" class="tableSign"><input type="hidden" name="agreementStatus4"></td>
				<td id="agreementStatus5" class="tableSign"><input type="hidden" name="agreementStatus5"></td>
			</tr>
			<tr>
				<th>시행자</th>
				<td id="implementMembers" colspan="5"></td>
			</tr>
			<tr>
				<th>수신 및 참조</th>
				<td id="referenceMembers" colspan="5"></td>
			</tr>
			<tr>
				<th>시행일자</th>
				<td colspan="5"><input type="text" name="param_ea_startdate" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01">&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="param_ea_enddate" class="form-control docInputSelect inputTypeDate" placeholder="2017-01-01"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="5"><input type="text" name="ea_title" class="form-control"></td>
			</tr>
			<tr>
				<th colspan="6">내용</th>
			</tr>
		</table>
		<!-- ea_content -> content -->
		<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
	<!-- 	<table> -->
	<!-- 		<tr> -->
	<!-- 			<th>첨부파일</th> -->
	<!-- 			<td colspan="3"><input type="file" name="board_file_name" onchange="javascript:file_change(this.value);"></td>	 -->
	<!-- 		</tr> -->
	<!-- 	</table> -->
	</form>
	<input type="hidden" value="${page}">
	<div class="textCenter insertJoinBtnWrap">
		<button type="button" onclick="saveContent();"  class="btn btn-default">등록</button>
		<input type="reset" value="초기화" class="btn btn-default" />	
		<button type="button"  class="btn btn-default"><a href="/electronicApproval/draft/draft">취소</a></button>
	</div>

</div>

<textarea id="text_content" style="display:none;">
${doc_content}
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