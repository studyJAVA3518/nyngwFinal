<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	function boardDelete(id){
		var con_test = confirm("어떤 값이 나올까요. 확인을 눌러보세요.");
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
	function aa() {
	}
	$(function(){
		$('#btn').click(function(){
// 			$('#content').attr("readonly",true);
			$('#con').prop('readonly',!$('#con').prop('readonly'));
			if($('#con').attr('readonly')==true){
				alert($('#con').val());
			}
		});
	});
</script>
<body>
	<div>
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td>${board.board_title}</td>
				<th>작성자</th>
				<td>${board.board_mem_number}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td>
				<th>조회수</th>
				<td>${board.board_count }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" cols="71" style="resize: none;" readonly="readonly">${board.board_content }</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">${board.board_file_name }</td>
			</tr>
		</table>
			<button><a href="/sharingInformation/board/updateForm?board_number=${board.board_number}">수정하기</a></button>
			<button type="button" onclick="boardDelete('${board.board_number}');">삭제하기</button>
			<button><a href="/sharingInformation/board/list?page=${page }">목록</a></button>
	</div>
	<div>
		
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
						<td>${i.comment_mem_number }<input type="hidden" value="${i.comment_number }"></td>
						<td colspan="2"><textarea  id="con" rows="2" cols="50" style="resize: none;" readonly>${i.comment_content }</textarea></td>
						<td><fmt:formatDate value="${i.comment_date }" pattern="yyyy/MM/dd"/></td>
						<td>
							<button id="btn"type="button" onclick="aa();">수정</button>
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
	</div>
</body>
</html>