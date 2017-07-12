<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.List"%>
<%@page import="com.nyngw.dto.VacationVO"%>
<%@page import="com.nyngw.mypage.myDalManagement.MyVacationListView"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
마이페이지 >> 나의 근태 관리 >> 휴가현황
<%
	Integer pageNumber = (Integer)request.getAttribute("pageNumber");
	MyVacationListView viewData = (MyVacationListView)request.getAttribute("viewData");
	%>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>휴가 번호</th>
			<th>휴가 시작일</th>
			<th>휴가 종료일</th>
			<th>휴가 종료예정일!</th>
			<th>사원 번호</th>
		</tr>
		<% if(viewData.getBoardCountPerPage()>0){
			List<VacationVO> boardList=viewData.getVacationList();
			for(int i =0; i<boardList.size();i++){
			%>
				<tr>
					<td><%=viewData.getFirstRow()+i%></td>
					<td><%=boardList.get(i).getVacation_number()%></td>
					<td><fmt:formatDate value="<%=boardList.get(i).getVacation_start()%>" pattern="yyyy/MM/dd"/></td>
					<td><fmt:formatDate value="<%=boardList.get(i).getVacation_end()%>" pattern="yyyy/MM/dd"/></td>
					<td><fmt:formatDate value="<%=boardList.get(i).getVacation_end_duedate()%>" pattern="yyyy/MM/dd"/></td>
					<td><%=boardList.get(i).getVacation_mem_number()%></td>
				</tr>
			<%
			}
			%>		
					
			<%}else{ %>
				<tr>
					<td style="text-align: center;">내용이 없습니다.</td>
				</tr>
			<%} %>
	</table>

	<div id="pageNum">
		<%for(int i=1;i<viewData.getPageTotalCount()+1;i++){ %>
			<a href="mypage/myDalManagement/vacation?page=<%=i%>">[<%=i%>]</a>
		<%} %>
	</div>


<div>
	<button><a href="#">휴가 신청</a></button>
</div>

