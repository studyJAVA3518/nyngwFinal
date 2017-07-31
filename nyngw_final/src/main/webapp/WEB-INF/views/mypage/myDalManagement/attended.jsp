<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.nyngw.dto.DalViewVO"%>
<%@page import="com.nyngw.mypage.myDalManagement.MyAttendedListView"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript">
$(function(){
	
	 $('#dialogg').css('display', 'none');
		$("#company_in").click(function(){
		$('#dialogg').dialog({
			width: 700,
			height: 500,
			modal: true,
			buttons: {
		       "취소": function() {
					$(this).dialog("close");
				}
			},
			close: function() {
				$('#textArea').val('');
			}
	    });
    })
})
</script>

<!-- 	<form name="hiddenForm"> -->
<%-- 	<input type="hidden" id="ea_number" value="${ea_number }"> --%>
<!-- 	<button type="button" id="company_in">출근</button> -->
<!-- 	<button type="button" id="editDraft_go">퇴근</button> -->
<!-- 	</form> -->
<style>
	.attended1{
		display : inline-block;
		width:15%; 
	}
	.attended{
	display : inline-block;
		width: 70%;
	}
</style>
<div id="dialogg">
	출근하셔쎼여 ?
</div>
<h2>나의 출결현황</h2>
<p class="docTitleDescription">
	자신의 근태현황을 확인할 수 있습니다.
</p>
	<table class="table table-bordered ">
			<tr>
			<th>검색</th>
					<td colspan="4">
					<form action="/mypage/myDalManagement/attended">
						<select name="index"  class="form-control docInputSelect attended1">
							<option value="dalview_date">날짜</option>
							<option value="dalview_content">결근사유</option>
						</select>
					<input type="hidden" value="${select.index}">
					<input type="text" name="val" class="form-control attended" value="${select.val}">
					<input type="submit" value="검색" class="btn btn-default">
					</form> 
					</td>
					
			</tr>		
			<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>출근 시간</th>
			<th>퇴근 시간</th>
			<th>결근 사유</th>
		</tr>
			<c:choose>
				<c:when test="${viewData.boardCountPerPage > 0}">
				<c:forEach items="${viewData.attendedList}" var="board" >
				<tr class= "textCenter">
					<td>${board.dalview_number}</td>
					<td>${board.dalview_date}</td>
					<td>${board.dalview_attend_time}</td>
					<td>${board.dalview_off_time}</td>
					<td>${board.dalview_content}</td>
				</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td style="text-align: center;">내용이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
	</table>

	
	<div id="pageNum" class= "textCenter">
			<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/mypage/myDalManagement/attended?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/mypage/myDalManagement/attended?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/mypage/myDalManagement/attended?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
	
	</div>
	
	