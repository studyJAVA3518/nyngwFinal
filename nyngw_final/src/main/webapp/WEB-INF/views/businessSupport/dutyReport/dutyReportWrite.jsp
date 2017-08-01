<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>내 업무 보고 등록</h2>
<p class="docTitleDescription">
	본인이 시행한 업무를 등록하는 페이지
</p>
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
				width:1100,
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
				$('#approvalMember'+(i+1)).html($(this).val()+'<input type="hidden" name="dr_to_mem_number" value="'+$(this).attr('id')+'">');
			});
		}
		submitLineCall=submitLine;
		
	});
</script>
<div id="linePopup" style="height:100%;width:100%;">
	<jsp:include page="toMemberSelect.jsp" flush="false"/>
</div>
<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="/businessSupport/dutyReport/dutyReportWrite" method="POST" accept-charset="utf-8">
	<table class="table table-bordered tableGray">
		<tr>
			<th>보고유형</th>
			<td>
				<select name="dr_code_number" class="form-control docInputSelect"> 
					<c:forEach items="${dr_codeList }" var="codeList">
						<option value="${codeList.code_number }">${fn:substring(codeList.code_name,0,2) }보고</option>
					</c:forEach>
				</select>
			</td>
			<th>보고일</th>
			<td><input type="text" class="inputTypeDate form-control" name="dr_date1"></td>
		</tr>
		<tr>
			<th id="approvalMember">보고대상</th><td colspan="3" id="approvalMember1"><button type="button" id="linePopup_go" class="btn btn-default">선택</button></td>
		</tr>
		<tr>
			<th>선택</th><td colspan="3"><input type="checkbox" name="dr_yesno" value="y">내 업무일지 동시등록</td>
		</tr>
		<tr>
			<th>제목</th><td colspan="3"><input type="text" name="dr_title" class="form-control"></td>
		</tr>
		<tr>
			<th colspan="4">내용</th>
		</tr>
	</table>
	<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
	<div class="insertJoinBtnWrap textCenter">
		<button type="button" onclick="saveContent();"  class="btn btn-default">등록</button>
		<button  class="btn btn-default"><a href="/businessSupport/dutyReport/dutyReport" id="dutyReport">취소</a></button>
	</div>
</form>