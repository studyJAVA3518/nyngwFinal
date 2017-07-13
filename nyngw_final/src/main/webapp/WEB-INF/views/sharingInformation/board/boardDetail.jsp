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
// 		var con_test = confirm("어떤 값이 나올까요. 확인을 눌러보세요.");
// 		$.ajax({
//             type : "POST",
//             url : "/sharingInformation/board/answerUpdate",
//             data : {'id' : id},
//             success : function(){
// 	          },
//             dataType : 'json' 
// 		});
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
					<th colspan="5">댓글</th>
				</tr>
				<tr>
					<td>김병현</td>
					<td colspan="2">내용12333333333333333333333333333333333333333333333333333</td>
					<td>2017.07.13</td>
					<td>
						<button type="button" onclick="aa('${board.board_title}');">수정</button>
						<button type="button">삭제</button>
					</td>
				</tr>
			</table>
		</form>
			
		<form action="/sharingInformation/board/answerWrite">
			<table class="table table-bordered">
				<tr>
					<td colspan="4">
						<textarea rows="5" cols="71" style="resize: none;" name="comment_content"></textarea>
					</td>
					<td>
						<input type="hidden" name="board_number" value="${board.board_number}">
						<input type="submit" value="등록">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>