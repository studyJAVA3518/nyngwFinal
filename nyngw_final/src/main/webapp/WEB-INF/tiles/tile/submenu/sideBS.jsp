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
					
					<h2 class="blind">업무지원</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
							
								<li>
									<h3>업무일지</h3>
									<ul>
										<li><a href="/businessSupport/dutyDocument/department">&nbsp;&nbsp;&nbsp;└ 부서 업무 조회</a></li>
										<li><a href="/businessSupport/dutyDocument/personal">&nbsp;&nbsp;&nbsp;└ 개인 업무 조회</a></li>
										<li><a href="/businessSupport/dutyDocument/additional">&nbsp;&nbsp;&nbsp;└ 추가 업무 조회</a></li>
									</ul>
								</li>
							
								<li>
									<h3>업무보고상신</h3>                       
									<ul>                                 
										<li><a href="/businessSupport/dutyReport/dutyReport">&nbsp;&nbsp;&nbsp;└ 업무 보고 상신</a></li>  
									</ul>                                	
								</li>
								
								<li>
									<h3>회의 시설 정보</h3>                       
									<ul>                                 
										<li><a href="/businessSupport/meetingFacilitiesManagement/facilitiesBooking">&nbsp;&nbsp;&nbsp;└ 회의실 관리</a></li>  
									</ul>                                	
								</li>
								<li>
									<h3>회의 관리</h3>                       
									<ul>                                 
										<li><a href="/businessSupport/meetingManagement/meetingCalendar">&nbsp;&nbsp;&nbsp;└ 회의 일정</a></li>  
										<li><a href="/businessSupport/meetingManagement/meetingFile">&nbsp;&nbsp;&nbsp;└ 회의록 관리</a></li>  
									</ul>                                	
								</li>
								
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->