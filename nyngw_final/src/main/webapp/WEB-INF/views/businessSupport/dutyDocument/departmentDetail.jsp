<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
   function answerWriteClick(id,form){
      var comment_content = document.getElementsByName('comment_content')[0].value;
      $.ajax({
            type : "POST",
            url : "/businessSupport/dutyDocument/departmentCommentWrite",
            data : {'id' : id ,'comment_content' : comment_content},
            success : function(result){
               location.href=result.uri;
           }, 
           error : function(){
           },
            dataType : 'json' 
      });
   }
   function departmentCommentDelete(id,dd_number){
      var con_test = confirm("삭제하시겠습니까?");
      if(con_test==true){
         $.ajax({
               type : "POST",
               url : "/businessSupport/dutyDocument/departmentCommentDelete",
               data : {'id' : id , "dd_number":dd_number},
               success : function(result){
                  location.href=result.uri;
              }, 
              error : function(){
              },
               dataType : 'json' 
         });
      }
   }
   $(function(){
//       $('#btn').click(function(){
// //          $('#content').attr("readonly",true);
//          var tf = $('#con').prop('readonly',!$('#con').prop('readonly'));
// //          alert($('#con').attr('readonly'));
         
//          if($('#con').attr('readonly')=='readonly'){
//             alert(tf);
//          }
//          if($('#con').attr('readonly')=='undefined'){
//             alert(tf);
//          }
   });
   
   function answerUpdateClick(dd_number,ddc_number,ddc_mem_number,ddc_content){
      var aa = "#"+ddc_number;
      $(aa).prop('readonly',!$(aa).prop('readonly'));
//       $('#con').prop('readonly',!$('#con').prop('readonly'));
//       alert(board_number);
//       alert(comment_number);
//       alert(comment_mem_number);
      ddc_content = document.getElementById(ddc_number).value;
//       alert(comment_content);
      
      if($(aa).attr('readonly')=='readonly'){
//          alert("ㅁㅁㅁㅁ");
         $.ajax({
               type : "POST",
               url : "/businessSupport/dutyDocument/departmentCommentUpdate",
               data : {'dd_number' : dd_number , "ddc_number":ddc_number , 'ddc_mem_number' : ddc_mem_number , 'ddc_content' : ddc_content},
               success : function(result){
                  location.href=result.uri;
              }, 
              error : function(){
              },
               dataType : 'json' 
         });
      }
   }
//          #con
</script>
<body>
<h2>부서업무 상세</h2>
<p class="docTitleDescription">
	부서업무의 상세 정보를 확인할 수 있는 페이지
</p>
	<table class="table table-bordered tableGray">
		<tr>
			<th >제목</th>
			<td colspan="3">${dutyDocument.dd_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dutyDocument.mem_name}</td>
			<th>업무시작일</th>
			<td><fmt:formatDate value="${dutyDocument.dd_start_date}" pattern="yyyy/MM/dd"/></td>
		</tr>
		<tr>
			<th>작성일자<input type="hidden" value="${page}"><input type="hidden" value="${reportType}"><input type="hidden" value="${searchDate}"></th>
			<td><fmt:formatDate value="${dutyDocument.dd_date}" pattern="yyyy/MM/dd"/></td>
			<th>공개여부</th>
			<td>
				<c:choose>
					<c:when test="${dutyDocument.dd_public eq 'y'}">
						&nbsp;&nbsp;<input type="checkbox" checked="checked" disabled="disabled">&nbsp;내용공개
					</c:when>
					<c:otherwise>
						&nbsp;&nbsp;<input type="checkbox" disabled="disabled">&nbsp; 내용공개
					</c:otherwise>
				</c:choose>		
		</tr>
		<tr>
			<th>업무일지</th>
			<td>${dutyDocument.dd_select_name}</td>
			<th>보고유형</th>
			<td>${dutyDocument.dd_name}</td>
		</tr>
		<tr>
			<td colspan="4">
				${dutyDocument.dd_content}
			</td>
		</tr>
	</table>
	<!-- 		select.put("reportType", reportType); -->
<!-- 		select.put("titleType", titleType); -->
<!-- 		select.put("val", val); -->
<!-- 		select.put("pageNumber", pageNumber); -->
<!-- 		select.put("searchDate", searchDate); -->
	<input type="hidden" value="${select.reportType}">
	<input type="hidden" value="${select.titleType}">
	<input type="hidden" value="${select.val}">
	<input type="hidden" value="${select.pageNumber}">
	<input type="hidden" value="${select.searchDate}">
	<div class="textCenter divALM">
		<button class="btn btn-default"><a href="/businessSupport/dutyDocument/department?page=${select.pageNumber}&reportType=${select.reportType}&searchDate=${select.searchDate}&val=${select.val}&titleType=${select.titleType}">목록</a></button>
	</div>
	
     <div class="businessReplay">
        <form>
	        <c:forEach items="${comment}" var="i">
				<div class="replyWholeWrap">
					<div class="replyWrap">
						<input id="${i.ddc_mem_number}" type="hidden" value="${i.ddc_mem_number }">${i.ddc_name }
						<input type="hidden" value="${i.ddc_number }">
						&nbsp;&nbsp;
						<fmt:formatDate value="${i.ddc_date }" pattern="yyyy/MM/dd"/>
						&nbsp;&nbsp;
						<c:if test="${user eq i.ddc_mem_number}">
							<button id="btn" type="button" onclick="answerUpdateClick('${dutyDocument.dd_number}','${i.ddc_number}','${i.ddc_mem_number}','${i.ddc_content}');" class="btn btn-default replyBtn">수정</button>
							<button type="button" onclick="departmentCommentDelete('${i.ddc_number}','${dutyDocument.dd_number}')" class="btn btn-default replyBtn">삭제</button>
						</c:if>
					</div>
					<textarea id="${i.ddc_number}" rows="3" cols="60" class="replyTextArea" readonly>${i.ddc_content }</textarea>
				</div>
			</c:forEach> 
		</form>
  	 	<div class="insertJoinBtnWrap replyWholeWrap">
  	 		<form>
  	 			<div class="replyWrap2">
  	 				댓글 등록하기
  	 			</div>
  	 			<textarea rows="4" cols="70" style="resize: none;" name="comment_content" class="replyTextWriteArea"></textarea>
				<input type="hidden" name="board_number" value="${dutyDocument.dd_number}">
				<div class="replyRightWrap">
					<button type="button" onclick="answerWriteClick('${dutyDocument.dd_number}',this.form)" class="btn btn-default replyEnrollBtn">등록</button>
  	 			</div>
  	 		</form>
  	 	</div>
  	 </div>
	
      
</body>
</html>