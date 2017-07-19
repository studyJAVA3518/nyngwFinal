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
   function boardDelete(id){
      var con_test = confirm("게시물을 삭제하시겠습니까?");
      if(con_test==true){
         $.ajax({
               type : "POST",
               url : "/sharingInformation/board/boardDelete",
               data : {'id' : id},
               success : function(result){
                  location.href=result.uri;
              }, 
              error : function(){
              },
               dataType : 'json' 
         });
      }
   }
   
   function answerWriteClick(id,form){
      var comment_content = document.getElementsByName('comment_content')[0].value;
      alert(comment_content);
      alert(id);
      
      $.ajax({
            type : "POST",
            url : "/sharingInformation/board/answerWrite",
            data : {'id' : id ,'comment_content' : comment_content},
            success : function(result){
               location.href=result.uri;
           }, 
           error : function(){
           },
            dataType : 'json' 
      });
   }
   function answerDeleteClick(id,board_number){
      var con_test = confirm("삭제할거에요?");
      if(con_test==true){
         $.ajax({
               type : "POST",
               url : "/sharingInformation/board/answerDelete",
               data : {'id' : id , "board_number":board_number},
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
   
   function answerUpdateClick(board_number,comment_number,comment_mem_number,comment_content){
      var aa = "#"+comment_number;
      $(aa).prop('readonly',!$(aa).prop('readonly'));
//       $('#con').prop('readonly',!$('#con').prop('readonly'));
//       alert(board_number);
//       alert(comment_number);
//       alert(comment_mem_number);
      comment_content = document.getElementById(comment_number).value;
//       alert(comment_content);
      
      if($(aa).attr('readonly')=='readonly'){
//          alert("ㅁㅁㅁㅁ");
         $.ajax({
               type : "POST",
               url : "/sharingInformation/board/answerUpdate",
               data : {'comment_number' : comment_number , "board_number":board_number , 'comment_mem_number' : comment_mem_number , 'comment_content' : comment_content},
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
	부서업무조회상세페이지
	<table class="table table-bordered">
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
				<textarea rows="20" cols="110" style="resize: none;" readonly="readonly">
				</textarea>
			</td>
		</tr>
	</table>
 <form action="/sharingInformation/board/answerUpdate">
      <table class="table table-bordered">
         <tr>
            <th>작성자</th>
            <th colspan="2">댓글</th>
            <th>작성시간</th>
            <th>기타</th>
         </tr>
         <c:forEach items="${comment}" var="i">
            <tr>
               <td><input id="${i.comment_mem_number}" type="hidden" value="${i.comment_mem_number }">${i.comment_mem_name }<input type="hidden" value="${i.comment_number }"></td>
               <td colspan="2"><textarea  id="${i.comment_number}" rows="2" cols="70" style="resize: none;" readonly>${i.comment_content }</textarea></td>
               <td><fmt:formatDate value="${i.comment_date }" pattern="yyyy/MM/dd"/></td>
               <td>
                  <button id="btn" type="button" onclick="answerUpdateClick('${board.board_number}','${i.comment_number}','${i.comment_mem_number}','${i.comment_content}');">수정</button>
                  <button type="button" onclick="answerDeleteClick('${i.comment_number}','${board.board_number}')">삭제</button>
               </td>
            </tr>
         </c:forEach> 
      </table>
   </form>
      
   <form action="/sharingInformation/board/answerWrite">
      <table class="table table-bordered">
         <tr>
            <th colspan="4">
               <textarea rows="5" cols="71" style="resize: none;" name="comment_content"></textarea>
            </th>
            <td>
               <input type="hidden" name="board_number" value="${board.board_number}">
               <button type="button" onclick="answerWriteClick('${board.board_number}',this.form)">등록</button>
            </td>
         </tr>
      </table>
   </form>
</body>
</html>