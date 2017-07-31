<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, java.text.*" %>
<script>
function meetingFileDelete(md_number){ 
	var con_test = confirm("해당 회의록을 삭제하시겠습니까?");
	if(con_test==true){
		$.ajax({
            type : "POST",
            url : "/businessSupport/meetingManagement/meetingFileDelete",
            data : {'md_number' : md_number},
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
<div>
			<table class="table table-border textCenter">
			<tr>
				<th>회의록명</th>
				<td>
					${meetingFile.md_name }
				</td>
				<th>작성일</th>
					<td>
					<fmt:formatDate value="${meetingFile.md_date}" pattern="yyyy/MM/dd"/>
					</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
						${meetingFile.md_writer }
				</td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
		</table>
			${meetingFile.md_content }
			<input type="hidden" value="${page}">
			<div style="text-align: center;">
			<button type="button" onclick="meetingFileDelete('${meetingFile.md_number}');" class="btn btn-default">삭제하기</button>
			<button><a href="/businessSupport/meetingManagement/meetingFileUpdateForm?md_number=${meetingFile.md_number}&param_md_date=<fmt:formatDate value="${meetingFile.md_date }" pattern="yyyy-MM-dd"/>">수정하기</a></button>
			<button><a href="/businessSupport/meetingManagement/meetingFile?page=${page }">목록</a></button>
			</div>
	</div>