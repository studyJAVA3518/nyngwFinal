<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row middleBar">
	
	<!-- 내가 결제할 내용 확인 -->
	<div class="col-md-10 rowBar">
		<div class="row">
			<a href="/electronicApproval/approvalProgress/waitingApproval">
				<div class="col-md-4 textCenter marginEA">
					<h4>미결재 문서</h4>
					<h4>${eaListCount}</h4>
				</div>
			</a>
			<a href="/electronicApproval/individualDocumentBox/submitApprovalBox">
				<div class="col-md-4 textCenter marginEA">
					<h4>상신 문서</h4>
					<h4>${userUiApprovalCount}</h4>
				</div>
			</a>	
			<a href="/electronicApproval/theRestDocumentBox/referenceDocumentBox">
				<div class="col-md-4 textCenter marginEA">
					<h4>참조 문서</h4>
					<h4>${theRestDocumentBoxCount}</h4>
				</div>
			</a>	
		</div>
	</div>
	
	<!-- UI 설정 -->
	<div class="col-md-2 textRight uiBtn">
		<a href="/homeMain/appointedUI/appointedUI?menu1=${mUUSS.menu1}&menu2=${mUUSS.menu2}&menu3=${mUUSS.menu3}" class="btn btn-default"><i class="fa fa-cog" aria-hidden="true" style="color : #00a0c7;"></i> UI 설정</a>
	</div>
</div>
  					
<!-- 공지사항 -->
<div class="row">
	<div class="col-md-12">
		<h2 class="tableTitle textCenter">${mUUSS.menu1}<a href="${mUUSS.uriAddr1 }">more</a></h2>
		<table class="table textCenter">
			<tr>
				<c:forEach items="${mUUSS.title1}" var="title1">
					<th>${title1}</th>
				</c:forEach>
			</tr>
			<c:choose>
				<c:when test="${mUUSS.content1.size()>0}">
					<c:forEach items="${mUUSS.content1 }" var="i">
						<tr>
							<td>${i.content1}</td>
							<td><a href="${i.detailUri}">${i.content2}</a></td>
							<td>${i.content3}</td>
							<td>${i.content4}</td>
						</tr>
					</c:forEach>
					</c:when>
				<c:otherwise>
					<tr>
						<td class="textCenter" colspan="4">리스트에 조회할 정보가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<div class="col-md-6">
		<h2 class="tableTitle textCenter">${mUUSS.menu2}<a href="${mUUSS.uriAddr2 }">more</a></h2>
		<table class="table textCenter">
			<tr>
				<c:forEach items="${mUUSS.title2}" var="title2">
					<th>${title2}</th>
				</c:forEach>
			</tr>
			<c:choose>
				<c:when test="${mUUSS.content2.size()>0}">
			<c:forEach items="${mUUSS.content2 }" var="i">
						<tr>
					<td>${i.content1}</td>
					<td><a href="${i.detailUri}">${i.content2}</a></td>
					<td>${i.content3}</td>
					<td>${i.content4}</td>
						</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="textCenter" colspan="4">리스트에 조회할 정보가 없습니다.</td>
					</tr>
		</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<div class="col-md-6">
		<h2 class="tableTitle textCenter">${mUUSS.menu3}<a href="${mUUSS.uriAddr3 }">more</a></h2>
		<table class="table textCenter">
			<tr>
				<c:forEach items="${mUUSS.title3}" var="title3">
					<th>${title3}</th>
				</c:forEach>
			</tr>
			<c:choose>
				<c:when test="${mUUSS.content3.size()>0}">
			<c:forEach items="${mUUSS.content3 }" var="i">
				<tr>
					<td>${i.content1}</td>
					<td><a href="${i.detailUri}">${i.content2}</a></td>
					<td>${i.content3}</td>
					<td>${i.content4}</td>
				</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="textCenter" colspan="4">리스트에 조회할 정보가 없습니다.</td>
					</tr>
		</c:otherwise>
			</c:choose>
		</table>
	</div>
</div>
  			