<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

	<h2>주소록조회</h2>
	<p class="docTitleDescription">
		사원들의 주소록을 확인할 수 있습니다.
	</p>
	<form action="/sharingInformation/memberInformation/addressBook">
		<label>이름</label>
		<input type="text" name="mem_name" class="form-control docInputSearch">
		<input type="submit" value="검색" class="btn btn-default">
	</form>
	<br><br>
	<table class="table table-bordered">
		<tr>
			<th>부서</th>
			<th>이름</th>
			<th>직위</th>
			<th>메일주소</th>
			<th>연락처</th>
			<th>주소</th>
		</tr>
		<c:choose>
			<c:when test="${addressBookViewVO.memberTotalCount > 0 }">
				<c:forEach items="${addressBookViewVO.addressBookList }" var="addressBook">
					<tr>
						<td>${addressBook.mem_dept_name }</td>
						<td>${addressBook.mem_name }</td>
						<td>${addressBook.mem_position_name }</td>
						<td>${addressBook.mem_email }</td>
						<td>${addressBook.mem_tel }</td>
						<td>${addressBook.mem_addr1 } 부근</td>
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
	<div id="pageNum" class="textCenter pageBottoWrap">
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/sharingInformation/memberInformation/addressBook?page=${beginPage-1}&mem_name=${mem_name }"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/sharingInformation/memberInformation/addressBook?page=${pno}&mem_name=${mem_name }" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < addressBookViewVO.getPageTotalCount()}">
			<a href="<c:url value="/sharingInformation/memberInformation/addressBook?page=${endPage + 1}&mem_name=${mem_name }"/>">다음</a>
		</c:if>
		
		<!-- 아래부분 지우시고 
			 위에 부분 마지막 if 문 "addressBookViewVO.getPageTotalCount()" 부분하고
			 검색어 url parameter로 넘겨주는 부분만 자기 것으로 수정하시면 되요~-->	
			
		<%-- 
		<c:forEach begin="1" end="${addressBookViewVO.getPageTotalCount()}" step="1" var="i">
			<a href="/sharingInformation/memberInformation/addressBook?page=${i }">[${i}]</a>
		</c:forEach> 
		--%>
	</div>
</body>
</html>