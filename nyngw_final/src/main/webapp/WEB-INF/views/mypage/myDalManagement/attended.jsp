<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.List"%>
<%@page import="com.nyngw.dto.DalViewVO"%>
<%@page import="com.nyngw.mypage.myDalManagement.MyAttendedListView"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

마이페이지 >> 나의 근태 관리 >> 출결관리

	<div>
		<button>출근 체크</button>
		<button>퇴근 체크</button>
	</div>

	<%
	Integer pageNumber = (Integer)request.getAttribute("pageNumber");
	MyAttendedListView viewData = (MyAttendedListView)request.getAttribute("viewData");
	%>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>출근 시간</th>
			<th>퇴근 시간</th>
			<th>결근 사유</th>
			<th>결근 사유</th>
		</tr>
		<% if(viewData.getBoardCountPerPage()>0){
			List<DalViewVO> boardList=viewData.getAttendedList();
			for(int i =0; i<boardList.size();i++){
			%>
				<tr>
					<td><%=viewData.getFirstRow()+i%></td>
					<td><%=boardList.get(i).getDalview_date()%></td>
					<td><%=boardList.get(i).getDalview_attend_time()%></td>
					<td><%=boardList.get(i).getDalview_off_time()%></td>
					<td><%=boardList.get(i).getDalview_content()%></td>
					<td><%=boardList.get(i).getDalview_sf_number()%></td>
					
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
			<a href="mypage/myDalManagement/attended?page=<%=i%>">[<%=i%>]</a>
		<%} %>
	</div>