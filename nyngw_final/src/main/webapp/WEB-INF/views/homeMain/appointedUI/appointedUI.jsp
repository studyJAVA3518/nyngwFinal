<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#allDiv{
	width: 100%;
	height: 500px;
}
#allDiv>div{
	width: 100%;
}
#bodyDiv{
	height: 90%;
}
#topDiv{
	height: 10%;
}
#bodyDiv>div{
	border:  1px solid black;
}
</style>
<script>
// $(function(){
//     $.ajax({
//        url : 'appointedUI',
//        type : 'get',
//        success : function(res){
//           var code = "";
//           $.each(res,function(i, bigMenu){
//              code += "<option value='" + ${bigMenu.big_number} + "'>" + ${bigMenu.big_name} +"</option>";
//           });
//           $('#bigMenu').html(code);
//           $('#bigMenu').trigger('change');
//        },
//        dataType : 'json'
//     });
//     ////////////////////////////////
//     $('#bigMenu').change(function(){
//        var guid = $(this).val();
//        $.ajax({
//           url : 'appointedUI' ,
//           type : 'post' ,
//           data : 'big_number=' + guid , 
//           success : function(res){
//              var code = "";
//              $.each(res,function(i, middleMenu){
//                 code += "<option value='" + ${middleMenu.mid_number} + "'>" + ${middleMenu.mid_name} +"</option>";
//              });
//              $('#middleMenu').html(code);
//              $('#middleMenu').trigger('change');
//           },
//           dataType : 'json'
//        });
//     });
// });
</script>
<div id="allDiv">
	<div id="topDiv">
		<button class="btn">자동</button>이 버튼을 누르면 밑에 화면1,2,3이 자주들어간 곳으로 설정됨.
	</div>
	<div id="bodyDiv">
		<div style="width: 100%; height: 50%;">
			<div style="width: 100%;">
				<select name="big_number">
				<c:forEach items="${bigMenu }" var="bigMenu">
					<option value="${bigMenu.big_number }">${bigMenu.big_name }</option>
				</c:forEach>
				</select>
<!-- 				<select name="bigMenu" id="bigMenu"> -->
<!-- 				</select> -->
				<select name="select2">
				<c:forEach items="${middleMenu }" var="middleMenu">
					<option value="${middleMenu.mid_number }">${middleMenu.mid_name }</option>
				</c:forEach>
				</select>
				<select name="middleMenu" id="middleMenu">
				</select>
			</div>
			<h1>화면1</h1>
		</div>
		<div style="width: 50%; height: 50%; float: left;">
			<div style="width: 100%;">
				<select name="select">
					<option>대분류목록</option>
				</select>
				<select name="select2">
					<option>중분류목록</option>
				</select>
			</div>
			<h1>화면2</h1>
		</div>
		<div style="width: 50%; height: 50%; float: left;">
			<div style="width: 100%;">
				<select name="select">
					<option>대분류목록</option>
				</select>
				<select name="select2">
					<option>중분류목록</option>
				</select>
			</div>
			<h1>화면3</h1>
		</div>
	</div>
	<div style="width: 100%;">
		<button class="btn">저장</button>
		<button class="btn">취소</button>
	</div>
</div>