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
	  						<h2 class="tableTitle">공지사항<a href="#">more</a></h2>
  							<table class="table">
  								<tr>
  									<th>번호</th>
  									<th>제목</th>
  									<th>조회수</th>
  								</tr>
  								<c:forEach items="${boardList}" var="board" begin="0" end="4" step="1">
  									<tr>
	  									<td>${board.board_number.substring(5)}</td>
	  									<td>${board.board_title}</td>
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
  								
<%--   								<c:forEach items="${boardList}" var="board" begin="0" end="4" step="1"> --%>
<!--   									<tr> -->
<%-- 	  									<td>${board.board_number.substring(5)}</td> --%>
<%-- 	  									<td>${board.board_title}</td> --%>
<%-- 	  									<td>${board.board_count}</td> --%>
<!-- 	  								</tr> -->
<%--   								</c:forEach> --%>
  								
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
	  						<h2 class="tableTitle">공지사항<a href="#">more</a></h2>
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
  					</div>
  			