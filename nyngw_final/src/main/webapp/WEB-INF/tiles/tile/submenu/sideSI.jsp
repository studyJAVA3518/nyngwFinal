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
               <h2 class="blind">정보공유</h2>
               <article>
                  <div class="lst_snb" id="accordion">
                     <ul>
                        <li>
                           <h3>공지사항</h3>
                           <ul>
                              <li id="sideMenu1"><a href="/sharingInformation/noticeMatter/nmList">&nbsp;&nbsp;&nbsp;└ 공지사항조회</a></li>
                           </ul>
                        </li>
                        <li>
                           <h3>일정관리</h3>
                           <ul>
																						<!--SC_CODE_NUMBER
																							code4	개인일정관리
																							code5	부서일정관리
																							code6	회사일정관리 -->
                              <li id="sideMenu2"><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code4">&nbsp;&nbsp;&nbsp;└ 개인일정관리</a></li>
                              <li id="sideMenu3"><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code5">&nbsp;&nbsp;&nbsp;└ 부서일정관리</a></li>
                              <li id="sideMenu4"><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=code6">&nbsp;&nbsp;&nbsp;└ 회사일정관리</a></li>
                           </ul>
                        </li>
                     
                        <li>
                           <h3>게시판</h3>                       
                           <ul>                                 
                              <li id="sideMenu5"><a href="/sharingInformation/board/list">&nbsp;&nbsp;&nbsp;└ 게시판조회</a></li>  
                           </ul>                                   
                        </li>
                        
                        <li>
                           <h3>직원정보</h3>                       
                           <ul>                                 
                              <li id="sideMenu6"><a href="/sharingInformation/memberInformation/organizationChart">&nbsp;&nbsp;&nbsp;└ 조직도조회</a></li>  
                              <li id="sideMenu7"><a href="/sharingInformation/memberInformation/addressBook">&nbsp;&nbsp;&nbsp;└ 주소록조회</a></li>  
                              <li id="sideMenu8"><a href="/sharingInformation/memberInformation/birthdayCheck">&nbsp;&nbsp;&nbsp;└ 생일자확인</a></li>  
                           </ul>                                   
                        </li>
                     </ul>
                  </div><!-- side_list -->
               </article>
            <!-- side -->
