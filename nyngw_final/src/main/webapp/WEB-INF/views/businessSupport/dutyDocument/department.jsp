<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script>
   $(function(){  
       $('#searchDate option[value=${select.searchDate}]').prop('selected',true);
       $('#reportType option[value=${select.reportType}]').prop('selected',true);
       $('#titleType option[value=${select.titleType}]').prop('selected',true);
   });
</script>
<body>
<h2>부서업무조회</h2>
<p class="docTitleDescription">
	로그인한 사원의 부서업무를 조회 할 수 있다.
</p>
   <div class="eaSearchDivMagin">
      <form action="/businessSupport/dutyDocument/department">
           검색기간
              <select name="searchDate" id="searchDate" class="form-control docInputSelect" style="width: 100px;">
                 <option value="today">금일</option>
                 <option value="week">1주일</option>
                 <option value="month">1개월</option>
                 <option value="trimester">3개월</option>
              </select>&nbsp;
           보고유형
              <select name="reportType" id="reportType" class="form-control docInputSelect" style="width: 120px;">
                 <option value="">전체</option>
                 <option value="code1">일일일지</option>
                 <option value="code2">주간일지</option>
                 <option value="code3">월간일지</option>
              </select>&nbsp;
           검색입력
              <select name="titleType" id="titleType" class="form-control docInputSelect" style="width: 100px;">
                 <option value="">전체</option>
                 <option value="dd_title">제목</option>
                 <option value="mem_name">작성자</option>
              </select>&nbsp;
            <input type="text" name="val" value="${select.val}" class="form-control eaInputSearch" style="width: 225px;">
            <input type="submit" value="검색"  class="btn btn-default">
      </form>
      <br>
      <br>
      <table class="table table-border textCenter">
         <tr>
            <th>번호</th>
            <th>업무시작일</th>
            <th>제목</th>
            <th>보고유형</th>
            <th>작성자</th>
            <th>등록일</th>
         </tr>
         <c:choose>
            <c:when test="${viewData.documentTotalCount > 0 }">
               <c:forEach items="${viewData.documentList }" var="board" varStatus="i">
                  <tr>
                     <td>${fn:substring(board.dd_number,2,10077777)}</td>
                     <td><fmt:formatDate value="${board.dd_date}" pattern="yyyy/MM/dd"/></td>
                     <td><a href="/businessSupport/dutyDocument/departmentDetail?dd_number=${board.dd_number}&page=${pageNumber}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}">${board.dd_title}</a></td>
                     <td>${board.dd_code_name }</td>
                     <td>${board.dd_name}</td>
                     <td><fmt:formatDate value="${board.dd_date}" pattern="yyyy/MM/dd"/></td>
                  </tr>
               </c:forEach>
            </c:when>
            <c:otherwise>
               <tr>
                  <td style="text-align: center;" colspan="6">내용이 없습니다.</td>
               </tr>
            </c:otherwise>
         </c:choose>
      </table>
      <div id="pageNum" class="insertJoinBtnWrap textCenter">
<%--          <c:forEach begin="1" end="${viewData.getPageTotalCount()}" step="1" var="i">  --%>
<%--             <a href="/businessSupport/dutyDocument/department?page=${i}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}">[${i}]</a>  --%>
<%--           </c:forEach>  --%>
          
         <c:if test="${beginPage > perPage}">
            <a href="<c:url value="/businessSupport/dutyDocument/department?page=${beginPage-1}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}"/>">이전</a>
         </c:if>
         <c:forEach var="i" begin="${beginPage}" end="${endPage}">
            <a href="/businessSupport/dutyDocument/department?page=${i}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}">[${i}]</a>
         </c:forEach>
         <c:if test="${endPage < viewData.getPageTotalCount()}">
            <a href="<c:url value="/businessSupport/dutyDocument/department?page=${endPage + 1}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}"/>">다음</a>
         </c:if>         
      </div>
   </div>
</body>
</html>