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
<h2>급여명세서 페이지</h2>
<p class="docTitleDescription">
	자신의 급여명세서를 확인할 수 있습니다.
</p>
  <div id="printArea">
         <table class="table table-border textCenter">
<!-- 			<tr> -->
<!-- 				<th>부서명</th> -->
<!-- 				<th>직책명</th> -->
<!-- 				<th>사원명</th> -->
<!-- 				<th>기본 급여</th> -->
<!-- 				<th>개별 수당</th> -->
<!-- 				<th>4대보혐료</th> -->
<!-- 				<th>실수령액</th> -->
<!-- 				<th>지급일자</th> -->
				
<!-- 			</tr> -->
			<c:choose>
				<c:when test="${viewData.memberTotalCount > 0}">
				<c:forEach items="${viewData.memberPayViewList}" var="board" >
<!-- 					<tr> -->
<%-- 						<td>${board.dept_name }</td> --%>
<%-- 						<td>${board.position_name }</td> --%>
<%-- 						<td>${board.mem_name}</td> --%>
<%-- 						<td>${board.mp_basic_pay}</td> --%>
<%-- 						<td>${board.mp_bonus}</td> --%>
<%-- 						<td>${board.mp_insurance}</td> --%>
<%-- 						<td>${board.mp_final_salary}</td> --%>
<%-- 						<td>${board.mp_pay_date}</td> --%>
<!-- 					</tr> -->
					<tr>
						<th>이름</th>
						<td>${board.mem_name}</td>
						<th>지급일</th>
						<td>${board.mp_pay_date}</td>
					</tr>
					<tr>
						<th>부서</th>
						<td>${board.dept_name }</td>
						<th>직책</th>
						<td>${board.position_name }</td>
					</tr>
					<tr>
						<td colspan="3">기본급</td>
						<td><fmt:formatNumber value="${board.mp_basic_pay}" type="currency" currencySymbol="￦"/></td>
					</tr>
					<tr>
						<td colspan="3">개별수당</td>
						<td><fmt:formatNumber value="${board.mp_bonus}" type="currency" currencySymbol="￦"/></td>
					</tr>
					<tr>
						<td colspan="3">(4대보험)</td>
						<td>-<fmt:formatNumber value="${board.mp_insurance}" type="currency" currencySymbol="￦"/></td>
					</tr>
					<tr>
						<td colspan="3">실수령액</td>
						<td><fmt:formatNumber value="${board.mp_final_salary}" type="currency" currencySymbol="￦"/></td>
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
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/mypage/myPayManagement/salary?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">이전달</a>
		</c:if>
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/mypage/myPayManagement/salary?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">다음달</a>
		</c:if>
<%-- 		<c:forEach var="pno" begin="${beginPage}" end="${endPage}"> --%>
<%-- 			<a href="<c:url value="/mypage/myPayManagement/salary?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a> --%>
<%-- 		</c:forEach> --%>
		</div>
    </div>
		
<!--         </dd> -->
<!--       </dl> -->
 
<!--   </div> -->
<!-- </div> -->


<input type = "button" class="btn btn-default" OnClick="print(document.getElementById('printArea').innerHTML)" value="프린트"/>






