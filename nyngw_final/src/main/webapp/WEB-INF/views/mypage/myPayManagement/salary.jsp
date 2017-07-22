<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 <script>
 $(document).on("click",".guideBox > p",function(){
      if($(this).next().css("display")=="none"){
        $(this).next().show();
        $(this).children("span").text("[닫기]");
      }else{
        $(this).next().hide();
        $(this).children("span").text("[열기]");
      }
});
 </script>
 
 <script>
function print(printArea)
{
      win = window.open(); 
      self.focus(); 
      win.document.open();
      
      /*
         1. div 안의 모든 태그들을 innerHTML을 사용하여 매개변수로 받는다.
         2. window.open() 을 사용하여 새 팝업창을 띄운다.
         3. 열린 새 팝업창에 기본 <html><head><body>를 추가한다.
         4. <body> 안에 매개변수로 받은 printArea를 추가한다.
         5. window.print() 로 인쇄
         6. 인쇄 확인이 되면 팝업창은 자동으로 window.close()를 호출하여 닫힘
      */
      win.document.write('<html><'head'><title></title><style>');
      win.document.write('body, td {font-falmily: Verdana; font-size: 10pt;}');
      win.document.write('</style></haed><body>');
      win.document.write(printArea);
      win.document.write('</body></html>');
      win.document.close();
      
      win.print();
      win.close();
}
</script>

<!-- <div class="guideBox"> -->
<!--   <p><button class="btn btn-primary">급여확인하기!</button></p> -->
<!--   <div style="display:none" id="printArea"> -->
<!--     <div> -->
<!--       <dl> -->
<!--         <dd> -->
         <table class="table table-border">
			<tr>
				<th>부서명</th>
				<th>직책명</th>
				<th>사원명</th>
				<th>기본 급여</th>
				<th>개별 수당</th>
				<th>4대보혐료</th>
				<th>실수령액</th>
				<th>지급일자</th>
				
			</tr>
			<c:choose>
				<c:when test="${viewData.memberTotalCount > 0}">
				<c:forEach items="${viewData.memberPayViewList}" var="board" >
				<tr>
					<td>${board.dept_name }</td>
					<td>${board.position_name }</td>
					<td>${board.mem_name}</td>
					<td>${board.mp_basic_pay}</td>
					<td>${board.mp_bonus}</td>
					<td>${board.mp_insurance}</td>
					<td>${board.mp_final_salary}</td>
					<td><fmt:formatDate value="${board.mp_pay_date}" pattern="yy'년'MM'월'dd'일'"/></td>
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
			<div id="pageNum">
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/mypage/myPayManagement/salary?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/mypage/myPayManagement/salary?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/mypage/myPayManagement/salary?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
		</div>
		
<!--         </dd> -->
<!--       </dl> -->
<!--     </div> -->
 
<!--   </div> -->
<!-- </div> -->


<input type = "button" OnClick="print(document.getElementById('printArea').innerHTML)" value="프린트"/>

마이페이지 >> 나의 급여 관리 >> 급여명세서 보기




