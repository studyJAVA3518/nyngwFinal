<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>					
				
	<script>
    	$(function(){
    		
	    	
    		//아코디언
    		$("#accordion>ul>li").click(function(){
    			if($(this).children("ul").attr("style")=="display: block;"){
	    			$(this).children("ul").slideUp();
    			}else{
	    			$(this).children().slideDown();
    			}
    		
    		})
	    	
	    })
	    
	</script>
	
	<!-- 환경설정관리에 대한 서브메뉴 -->
					
					<h2 class="blind">환경 설정 관리</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
								<li>
									<h3>기획홍보부 설정</h3>
									<ul>
										<li><a href="/enovironmentSetting/planPublicRelationsSetting/workingDayForm">&nbsp;&nbsp;&nbsp;└ 근무일 및 출결정보 등록시간 설정</a></li>
										<li><a href="/enovironmentSetting/planPublicRelationsSetting/companyInfoForm">&nbsp;&nbsp;&nbsp;└ 회사 정보 설정</a></li>
										<li><a href="/enovironmentSetting/planPublicRelationsSetting/companyDepartForm">&nbsp;&nbsp;&nbsp;└ 회사 부서 설정</a></li>
										<li><a href="/enovironmentSetting/planPublicRelationsSetting/companyPositionForm/">&nbsp;&nbsp;&nbsp;└ 회사 직급 설정</a></li>
										<li><a href="/enovironmentSetting/planPublicRelationsSetting/organizationForm/">&nbsp;&nbsp;&nbsp;└ 조직도 설정</a></li>
									</ul>
									
								</li>
							
								<li>
									<h3>인사부 설정</h3>
									<ul>
										<li><a href="/enovironmentSetting/humanResourceSetting/vacationKindForm">&nbsp;&nbsp;&nbsp;└ 휴가 종류 설정</a></li>
										<li><a href="/enovironmentSetting/humanResourceSetting/vacationDaysForm">&nbsp;&nbsp;&nbsp;└ 휴가 일수 설정</a></li>
									</ul>
								</li>
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->