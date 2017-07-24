<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


			<!-- 헤더 가운데 감싸는 부분 -->
  			<div class="container-fliud headerTopColor">
  				<div class="container textRight">
	  				<p class="headerTop">
						${memberName} 사원님 안녕하세요. 
						<a href="/homeMain/main" class="btn btn-default btn-header">
							<i class="fa-lg fa fa-home" aria-hidden="true"></i>홈으로
						</a>
						<a href="/user/logout" class="btn btn-default btn-header">
							<i class="fa-lg fa fa-sign-out" aria-hidden="true"></i>로그아웃
						</a>
					</p>
  				</div>
  			</div>
  			
  			
  			<div class="container">
  				<div class="row">
  					<!-- 로고 (이동시)-->
  					<div class="col-md-2">
  						<!-- image를 사용할 때에는 클래스에 img-responsive -->
  						<a href="/homeMain/main">
  							<img src="<%=request.getContextPath()%>/${companyLogo}" alt="company logo" class="img-responsive">
  						</a>
  					</div>
  					<div class="col-md-10">
						<div class="row">
							<div class="headerBottom">
								
								<div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	문서관리 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li class="topMenuTitle">문서 관리</li>
						            <li><a href="/documentManagement/documentManager/documentSelect">문서 조회</a></li>
						            <li><a href="/documentManagement/documentManager/edocumentSelect">전자문서 조회</a></li>
						            <li><a href="/documentManagement/documentManager/documentInsert">문서 등록</a></li>
						          </ul>
						        </div>
								
								
								<div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	정보공유 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li class="topMenuTitle">공지사항</li>
									<li><a href="/sharingInformation/noticeMatter/nmList">공지사항조회</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">일정관리</li>
									<li><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code4">개인일정관리</a></li>
                              		<li><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code5">부서일정관리</a></li>
                              		<li><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code6">회사일정관리</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">게시판</li>
						            <li><a href="/sharingInformation/board/list">게시판조회</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">직원정보</li>
						            <li><a href="/sharingInformation/memberInformation/organizationChart">조직도조회</a></li>  
									<li><a href="/sharingInformation/memberInformation/addressBook">주소록조회</a></li>  
                              		<li><a href="/sharingInformation/memberInformation/birthdayCheck">생일자확인</a></li>  
						          </ul>
						        </div>
						        
						        <div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	업무지원 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li class="topMenuTitle">업무일지</li>
									<li><a href="/businessSupport/dutyDocument/department">부서 업무 조회</a></li>
									<li><a href="/businessSupport/dutyDocument/personal">개인 업무 조회</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">업무보고상신</li>
									<li><a href="/businessSupport/dutyReport/dutyReport">내 업무 보고</a></li>
									<li><a href="/businessSupport/dutyReport/getDutyReportselect">받은 업무 보고</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">회의 시설 정보</li>
									<li><a href="/businessSupport/meetingFacilitiesManagement/meetingRoomBooking">회의실 관리</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">회의 관리</li>
									<li><a href="/businessSupport/meetingManagement/meetingCalendar">회의 일정</a></li>
									<li><a href="/businessSupport/meetingManagement/meetingFile">회의록 관리</a></li>
						          </ul>
						        </div>
						        
						        <div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	전자결재 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li><a href="/electronicApproval/draft/draft">기안하기</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">결재진행</li>
						            <li><a href="/electronicApproval/approvalProgress/waitingApproval">미결재문서</a></li>
						            <li><a href="/electronicApproval/approvalProgress/completeApproval">결재완료문서</a></li>
						            <li><a href="/electronicApproval/approvalProgress/refusedApproval">반려문서</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">개인문서함</li>
						            <li><a href="/electronicApproval/individualDocumentBox/submitApprovalBox">상신문서함</a></li>
						            <li><a href="/electronicApproval/individualDocumentBox/outbox">임시보관함</a></li>
						            <li><a href="/electronicApproval/individualDocumentBox/completeApprovalBox">결재완료문서함</a></li>
						            <li><a href="/electronicApproval/individualDocumentBox/refusedApprovalBox">반려문서함</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">그외문서함</li>
									<li><a href="/electronicApproval/theRestDocumentBox/implementDocumentBox">시행문서함</a></li>  
									<li><a href="/electronicApproval/theRestDocumentBox/referenceDocumentBox">참조문서함</a></li>  
									<li><a href="/electronicApproval/theRestDocumentBox/overallDocumentBox">전체문서함</a></li>  
						            <li class="divider"></li>
									<li><a href="/electronicApproval/approvalLineManagement/approvalLineManagement">결재라인관리</a></li>  
						         
						          </ul>
						        </div>
						        
						        <div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	인사관리 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li class="topMenuTitle">사원 관리</li>
						            <li><a href="/humanResource/dalManagement/hrm">근태관리</a></li>
						            <li><a href="/humanResource/vacationManagement/vmtm">휴가관리</a></li>
						            <li><a href="/humanResource/payManagement/pmm">급여관리</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">인사 관리</li>
						            <li><a href="/humanResource/memberJoin/mjm">사원 가입</a></li>
						            <li><a href="/humanResource/joinMemberList/jlm">사원 목록</a></li>
						            <li><a href="/humanResource/retiredMemberList/rmm">퇴사자 목록</a></li>
						          </ul>
						        </div>
						        
						        <div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	마이페이지 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li class="topMenuTitle">나의 근태 관리</li>
									<li><a href="/mypage/myDalManagement/attended">출결현황</a></li>
									<li><a href="/mypage/myDalManagement/vacation">휴가현황</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">나의 급여 관리</li>
						            <li><a href="/mypage/myPayManagement/salary">급여 명세서 보기</a></li>
									<li><a href="/mypage/myPayManagement/severance">퇴직금 현황</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">기본 설정</li>
						            <li><a href="/mypage/basicSetting/sign">개인 정보 수정</a></li>
						          </ul>
						        </div>
						        
						        <div class="dropdown">
						          <a href="#" class="dropdown-toggle menuDetail" data-toggle="dropdown" role="button" aria-expanded="false">
						          	환경설정관리 <span class="caret"></span>
						          </a>
						          <ul class="dropdown-menu" role="menu">
						            <li class="topMenuTitle">기획홍보부 설정</li>
									<li><a href="/enovironmentSetting/planPublicRelationsSetting/workingDayForm">근무일 및 출결정보 등록시간 설정</a></li>
									<li><a href="/enovironmentSetting/planPublicRelationsSetting/companyInfoForm">회사 정보 설정</a></li>
									<li><a href="/enovironmentSetting/planPublicRelationsSetting/companyDepartForm">회사 부서 설정</a></li>
									<li><a href="/enovironmentSetting/planPublicRelationsSetting/companyPositionForm/">회사 직급 설정</a></li>
									<li><a href="/enovironmentSetting/planPublicRelationsSetting/payKindForm">급여종류 및 퇴직금 설정</a></li>
									<li><a href="/enovironmentSetting/planPublicRelationsSetting/payPolicyForm">급여정책 설정</a></li>
						            <li class="divider"></li>
						            <li class="topMenuTitle">인사부부 설정</li>
									<li><a href="/enovironmentSetting/humanResourceSetting/vacationKindForm">휴가 종류 설정</a></li>
									<li><a href="/enovironmentSetting/humanResourceSetting/vacationDaysForm">휴가 일수 설정</a></li>
						          </ul>
						        </div>
							</div>
						</div>
  					</div>
  				</div>
  			</div>
