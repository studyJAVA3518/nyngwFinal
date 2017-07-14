<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 %>

<script>
 $(document).on("click",".guideBox > p",function(){
      if($(this).next().css("display")=="none"){
        $(this).next().show();
        $(this).children("span").text("[닫기]");
      }else{
        $(this).next().hide();
        $(this).children("span").text("[열기]");
      }
});
 </script>
 
 <script>
function print(printArea)
{
      win = window.open(); 
      self.focus(); 
      win.document.open();
      
      /*
         1. div 안의 모든 태그들을 innerHTML을 사용하여 매개변수로 받는다.
         2. window.open() 을 사용하여 새 팝업창을 띄운다.
         3. 열린 새 팝업창에 기본 <html><head><body>를 추가한다.
         4. <body> 안에 매개변수로 받은 printArea를 추가한다.
         5. window.print() 로 인쇄
         6. 인쇄 확인이 되면 팝업창은 자동으로 window.close()를 호출하여 닫힘
      */
      win.document.write('<html><'head'><title></title><style>');
      win.document.write('body, td {font-falmily: Verdana; font-size: 10pt;}');
      win.document.write('</style></haed><body>');
      win.document.write(printArea);
      win.document.write('</body></html>');
      win.document.close();
      
      win.print();
      win.close();
}
</script>

마이페이지 >> 나의 급여 관리 >> 퇴직금 현황
<div class="guideBox">
  <p><button class="btn btn-primary" id="printArea">퇴직금확인하기!</button></p>
  <div style="display:none">
          <table class="table table-bordered" >
   		<tr>
   			<th>성명</th>
   			<td><%=user.getUsername() %></td>
   			<th>부서</th>
   			<td>유저의 부서</td>
   		</tr>
   		<tr>
   			<th>직책</th>
   			<td>유저의 직책</td>
   			<th>부서</th>
   			<td>유저의 부서</td>
   		</tr>
   		<tr>
   			<th>근무연수</th>
   			<td>1년</td>
   			<th>퇴직금</th>
   			<td>1원</td>
   		</tr>
   	</table>
  </div>
</div>

<input type = "button" OnClick="print(document.getElementById('printArea').innerHTML)" value="프린트"/>