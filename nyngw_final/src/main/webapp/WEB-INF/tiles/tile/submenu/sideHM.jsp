<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>				
					
<!-- 개인사원 프로필사진 -->
<div class="row contentBorder">
	<div class="row leftTemplate textCenter">
		<div class="col-md-6 personImg">
			<img src="/resources/images/profile/${mem_img }" alt="profile image" class="img-responsive">
		</div>
		<div class="col-md-6 personInfo">
			<p class="personCompany">${company.company_name}</p>
			<div class="personPosition">[${member.dept_name}&nbsp;${member.position_name}]</div>
			<div class="personPosition">${member.mem_name}님<br/>안녕하세요.</div>
		</div>
	</div>
	
	<div class="row leftTemplate topBorderBar">
		<a href="<%=request.getContextPath()%>/resources/companyInfo/nyn_company_info.pdf" target="_blank">
			<div class="sideIcon textCenter">
				<i class="fa fa-building" aria-hidden="true"></i>
			</div>	
			<div class="companyInfoDown">
				회사 소개
				<br/>브로슈어 
				<br/>pdf 보기
			</div>
		</a>
		<a href="<%=request.getContextPath()%>/resources/companyInfo/최종_그룹웨어_사용자가이드.pdf" target="_blank">
			<div class="sideIcon textCenter">
				<i class="fa fa-building" aria-hidden="true"></i>
			</div>	
			<div class="companyInfoDown">
				그룹웨어
				<br/>사용자가이드 
				<br/>pdf 보기
			</div>
		</a>
	</div>
</div>

<div class="sideImg">
	
</div>