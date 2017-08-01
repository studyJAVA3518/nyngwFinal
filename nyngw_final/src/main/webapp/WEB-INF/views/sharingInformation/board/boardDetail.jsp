<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script>
   function boardDelete(id){
      var con_test = confirm("게시물을 삭제 하시겠습니까?");
      
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
      var con_test = confirm("댓글을 삭제 하시겠습니까?");
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
   });
   
   function answerUpdateClick(board_number,comment_number,comment_mem_number,comment_content){
      var aa = "#"+comment_number;
      $(aa).prop('readonly',!$(aa).prop('readonly'));
      $(aa).focus();
      comment_content = document.getElementById(comment_number).value;
      
      if($(aa).attr('readonly')=='readonly'){
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
<h2>게시판 상세</h2>
<p class="docTitleDescription">
	게시판 문서의 상세보기입니다.
</p>
<div>
   <table class="table table-bordered tableGray">
      <tr>
         <th>제목</th>
         <td>${board.board_title}</td>
         <th>작성자</th>
         <td>${board.mem_name}</td>
      </tr>
      <tr>
         <th>작성일</th>
         <td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td>
         <th>조회수</th>
         <td>${board.board_count }</td>
      </tr>
      <tr>
         <th colspan="4">내용</th>
      </tr>
   </table>
      ${board.board_content }
   <table  class="table table-bordered tableGray">
      <tr>
         <th>첨부파일</th>
         <td><a href="/sharingInformation/board/boardDownload?fileName=${board.board_file_name}">${board.board_file_name }</a></td>
      </tr>
   </table>   
</div>
   <div class="textCenter">
	<c:choose>
		<c:when test="${mem.mem_number eq board.board_mem_number}">
	      	<button class="btn btn-default"><a href="/sharingInformation/board/updateForm?board_number=${board.board_number}">수정하기</a></button>
	      	<button class="btn btn-default" type="button" onclick="boardDelete('${board.board_number}');">삭제하기</button>
	    	<button class="btn btn-default"><a href="/sharingInformation/board/list?page=${page }">목록</a></button>
		</c:when>
		<c:otherwise>
			<button class="btn btn-default"><a href="/sharingInformation/board/list?page=${page }">목록</a></button>
		</c:otherwise>
	</c:choose>
</div>
   <br>
   <form action="/sharingInformation/board/answerUpdate">
      <table class="table table-bordered tableGray">
         <tr>
            <th>작성자</th>
            <th colspan="2">댓글</th>
            <th>작성시간</th>
            <th>기타</th>
         </tr>
         <c:forEach items="${comment}" var="i">
            <tr>
               <td><input id="${i.comment_mem_number}" type="hidden" value="${i.comment_mem_number }">${i.comment_mem_name }<input type="hidden" value="${i.comment_number }"></td>
               <td colspan="2"><textarea  id="${i.comment_number}" rows="2" cols="70" style="resize: none;border:0" readonly >${i.comment_content }</textarea></td>
               <td><fmt:formatDate value="${i.comment_date }" pattern="yyyy/MM/dd"/></td>
               <c:choose>
					<c:when test="${mem.mem_number eq board.board_mem_number}">
		               <td>
		                  <button class="btn btn-default" id="btn" type="button" onclick="answerUpdateClick('${board.board_number}','${i.comment_number}','${i.comment_mem_number}','${i.comment_content}');">수정</button>
		                  <button class="btn btn-default" type="button" onclick="answerDeleteClick('${i.comment_number}','${board.board_number}')">삭제</button>
		               </td>
               		</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
            </tr>
         </c:forEach> 
      </table>
   </form>
      
   <div class="insertJoinBtnWrap">   
	   <form action="/sharingInformation/board/answerWrite">
	      <table class="table table-bordered tableGray">
	         <tr>
	            <th colspan="4">
	               <textarea rows="5" cols="71" style="resize: none;" name="comment_content" ></textarea>
	            </th>
	            <td>
	               <input type="hidden" name="board_number" value="${board.board_number}">
	               <button class="btn btn-default" type="button" onclick="answerWriteClick('${board.board_number}',this.form)">등록</button>
	            </td>
	         </tr>
	      </table>
	   </form>
	</div>
