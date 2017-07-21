<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script>
   function answerWriteClick(id,form){
      var drc_content = document.getElementsByName('drc_content')[0].value;
      $.ajax({
            type : "POST",
            url : "/businessSupport/dutyReport/dutyReportCommentWrite",
            data : {'id' : id ,'drc_content' : drc_content},
            success : function(result){
               location.href=result.uri;
           }, 
           error : function(){
           },
            dataType : 'json' 
      });
   }
   function departmentCommentDelete(id,dr_number){
      var con_test = confirm("삭제할거에요?");
      if(con_test==true){
         $.ajax({
               type : "POST",
               url : "/businessSupport/dutyReport/dutyReportCommentDelete",
               data : {'id' : id , "dr_number":dr_number},
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
<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="/businessSupport/dutyReport/dutyReportWrite" method="POST" accept-charset="utf-8">
	<table class="table table-bordered">
		<tr>
			<th>보고유형</th>
			<td>${fn:substring(dutyReportVO.dr_code_name,0,2)}보고</td>
			<th>보고일 | 작성일</th>
			<td>${dr_date }</td><td>${dr_writedate}</td>
		</tr>
		<tr>
			<th>보고대상</th><td>${toUser }<input type="hidden" name="dr_yesno" value="${dutyReportVO.dr_yesno }"></td>
			<th>작성자</th><td colspan="2">${username }</td>
		</tr>
		<tr>
			<th>제목</th><td colspan="4">${dutyReportVO.dr_title }</td>
		</tr>
		<tr>
			<th colspan="5">내용</th>
		</tr>
		<tr>
			<td colspan="5" style="text-align: left;">${dutyReportVO.dr_content }</td>
		</tr>
	</table>
</form>
<form action="/businessSupport/dutyReport/dutyReportCommentWrite" style="width: 750px;">
	<table class="table table-bordered">
		<tr>
			<th>의견</th><td><textarea cols="70" rows="2" name="drc_content"></textarea></td>
			<td>
				<input type="hidden" name="dr_number" value="${dutyReportVO.dr_number}">
				<button type="button" onclick="answerWriteClick('${dutyReportVO.dr_number}',this.form)">등록</button>
			</td>
		</tr>
	</table>
	<input type="hidden" name = "dr_number" value="${dutyReportVO.dr_number }">
	<input type="hidden" value="${select.reportType}">
	<input type="hidden" value="${select.titleType}">
	<input type="hidden" value="${select.val}">
	<input type="hidden" value="${select.pageNumber}">
	<input type="hidden" value="${select.searchDate}">
</form>
<table style="background-color: lightgray; width: 750px; height: 30px; font-size: 10px; margin-bottom: 30px;">
	<c:forEach items="${comment}" var="comment">
		<tr style="border: solid; border-color: white;">
			<th>
				<input id="${comment.drc_mem_number}" type="hidden" value="${comment.drc_mem_number }">
				<input type="hidden" value="${comment.drc_number }">
			</th>
			<td>${comment.drc_mem_name }</td>
			<td style="width: 20%;"><fmt:formatDate value="${comment.drc_date }" pattern="yyyy/MM/dd"/></td>
			<td style="width: 65%;">${comment.drc_content }</td>
			<td>
				<c:if test="${loginUserID eq comment.drc_mem_number}">
					<button type="button" onclick="departmentCommentDelete('${comment.drc_number}','${dutyReportVO.dr_number}')">ⓧ</button>
				</c:if>
			</td>
			<td>
			</td>
		</tr>
	</c:forEach>
</table>
<c:choose>
	<c:when test="${mem_id eq loginUser}">
		<button class="btn"><a href="/businessSupport/dutyReport/dutyReport?page=${pageNumber }" >목록</a></button>
	</c:when>
	<c:otherwise>
		<button class="btn"><a href="/businessSupport/dutyReport/getDutyReportselect?page=${pageNumber }">목록</a></button>
	</c:otherwise>
</c:choose>