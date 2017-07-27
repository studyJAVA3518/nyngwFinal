<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<script>
function noticeMatterDelete(id){
	var con_test = confirm("공지사항을 삭제하시겠습니까?");
	if(con_test==true){
		$.ajax({
            type : "POST",
            url : "/sharingInformation/noticeMatter/nmDelete",
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
</script>
<h2>공지사항</h2>
<p class="description">
	회사의 공지사항을 확인할 수 있습니다.
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
			<tr>
				<td colspan="4">${board.board_content }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3"><a href="/sharingInformation/board/boardDownload?fileName=${board.board_file_name}">${board.board_file_name }</a></td>
			</tr>
		</table>
		<div class="textCenter">
			<c:choose>
				<c:when test="${mem.mem_number eq board.board_mem_number}">
					<button class="btn btn-default"><a href="/sharingInformation/noticeMatter/nmUpdateForm?board_number=${board.board_number}">수정하기</a></button>
					<button type="button" onclick="noticeMatterDelete('${board.board_number}');">삭제하기</button>
					<button class="btn btn-default"><a href="/sharingInformation/noticeMatter/nmList?page=${page }">목록</a></button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-default"><a href="/sharingInformation/noticeMatter/nmList?page=${page }">목록</a></button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
