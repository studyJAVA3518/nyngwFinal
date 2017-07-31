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
    		
    		$("#${sideValue}").addClass('active');
	    	
	    })
	    
	</script>
					
					<h2 class="blind">마이페이지</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
								<li>
									<h3>나의 근태 관리</h3>
									<ul>
										<li id="sideMenu1"><a href="/mypage/myDalManagement/attended">&nbsp;&nbsp;&nbsp;└ 출결현황</a></li>
										<li id="sideMenu2"><a href="/mypage/myDalManagement/vacation">&nbsp;&nbsp;&nbsp;└ 휴가현황</a></li>
									</ul>
									
								</li>
							
								<li>
									<h3>나의 급여 관리</h3>
									<ul>
										<li id="sideMenu3"><a href="/mypage/myPayManagement/salary">&nbsp;&nbsp;&nbsp;└ 급여 명세서 보기</a></li>
									</ul>
								</li>
							
								<li>
									<h3>기본 설정</h3>                       
									<ul>                                 
										<li id="sideMenu4"><a href="/mypage/basicSetting/sign">&nbsp;&nbsp;&nbsp;└ 개인 정보 수정</a></li>  
									</ul>                                	
								</li>
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->