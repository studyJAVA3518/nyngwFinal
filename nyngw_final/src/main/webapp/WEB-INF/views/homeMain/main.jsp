<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  				
  					<div class="row middleBar">
  						
  						<!-- 내가 결제할 내용 확인 -->
  						<div class="col-md-10 rowBar">
  							<div class="row">
  								<div class="col-md-4 textCenter marginEA">
  									<p>결제할 문서</p>
  									<p>2</p>
  								</div>
  								<div class="col-md-4 textCenter marginEA">
  									<p>진행중 문서</p>
  									<p>2</p>
  								</div>
  								<div class="col-md-4 textCenter marginEA">
  									<p>참조된 문서</p>
  									<p>2</p>
  								</div>
  							</div>
  						
  						</div>
  						
  						<!-- UI 설정 -->
  						<div class="col-md-2 textRight">
  							<a href="#" class="btn btn-default">UI 설정</a>
  						</div>
  					</div>
  					
  					<!-- 공지사항 -->
  					<div class="row">
  						<div class="col-md-12">
	  						<h2 class="tableTitle">공지사항<a href="/sharingInformation/noticeMatter/nmList">more</a></h2>
  							<table class="table">
  								<tr>
  									<th>번호</th>
  									<th>제목</th>
  									<th>조회수</th>
  								</tr>
  								<c:forEach items="${boardList}" var="board" begin="0" end="4" step="1">
									
  									<tr>
		  									<td>${board.board_number.substring(5)}</td>
		  									<td><a href="/sharingInformation/board/detail?board_number=${board.board_number}">${board.board_title}</a></td>
		  									<td>${board.board_count}</td>
	  								</tr>
  									
  								</c:forEach>
  								
  							</table>
  						</div>
  						
  						<div class="col-md-6">
	  						<h2 class="tableTitle">결재진행<a href="#">more</a></h2>
  							<table class="table">
  								<tr>
  									<th>순서</th>
  									<th>제목</th>
  									<th>기안자</th>
  								</tr>
  								<tr>
  									<td>1</td>
  									<td>원자력 연구소 프로젝트 완료 보고</td>
  									<td>최시영</td>
  								</tr>
  								<tr>
  									<td>2</td>
  									<td>원자력 연구소 프로젝트 완료 보고</td>
  									<td>최시영</td>
  								</tr>
  								<tr>
  									<td>3</td>
  									<td>원자력 연구소 프로젝트 완료 보고</td>
  									<td>최시영</td>
  								</tr>
  								<tr>
  									<td>4</td>
  									<td>원자력 연구소 프로젝트 완료 보고</td>
  									<td>최시영</td>
  								</tr>
  								<tr>
  									<td>5</td>
  									<td>원자력 연구소 프로젝트 완료 보고</td>
  									<td>최시영</td>
  								</tr>
  							</table>
  						</div>
  						
  						<div class="col-md-6">
	  						<h2 class="tableTitle">Today 개인/부서일정<a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code4">more</a></h2>
  							<table class="table">
  								<tr>
  									<th>일정종류</th>
  									<th>일정제목</th>
  									<th>일정시간</th>
  								</tr>
  								<c:choose>
  									<c:when test="${size != 0 }">
  										<c:forEach items="${scheduleList}" var="scheduleList" begin="0" end="4" step="1">
		  									<tr>
												<%--<td>${scheduleList.sc_number.substring(2)}</td> --%>
			  									<c:choose>
			  										<c:when test="${scheduleList.sc_code_number != 'code4' }">
			  											<td>부서</td>
			  										</c:when>
			  										<c:otherwise>
			  											<td>개인</td>
			  										</c:otherwise>
			  									</c:choose>
			  									<td><a href="/sharingInformation/scheduleManagement/scheduleDetail?sc_number=${scheduleList.sc_number}">${scheduleList.sc_title}</a></td>
												<td>${scheduleList.sc_time}</td>
			  								</tr>
		  								</c:forEach>
  									</c:when>
  									<c:otherwise>
  										<tr><td colspan="3">오늘 일정이 없습니다.</td></tr>
	  								</c:otherwise>
  								</c:choose>
  							</table>
  						</div>
  					</div>
  			