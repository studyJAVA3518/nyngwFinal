<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>				
					
					<!-- 개인사원 프로필사진 -->
  					<div class="row leftTemplate textCenter">
  						<div class="col-md-5 personImg">
  							<img src="../resources/images/proflie_01.jpg" alt="profile image" class="img-responsive">
  						</div>
  						<div class="col-md-7">
	  						<p class="personCompany">${company.company_name}</p>
	  						<div class="personPosition">[${member.dept_name}&nbsp;${member.position_name}]</div>
	  						<div class="personPosition">${member.mem_name}님<br/>안녕하세요.</div>
  						</div>
  					</div>
  					
  					<div class="row leftTemplate">
  						<h3><i class="fa fa-tasks" aria-hidden="true"></i> 자주가는 카테고리</h3>
  						<ul>
  							<a href="#"><li>자주가는 메뉴 1</li></a>
  							<a href="#"><li>자주가는 메뉴 2</li></a>
  							<a href="#"><li>자주가는 메뉴 3</li></a>
  							<a href="#"><li>자주가는 메뉴 4</li></a>
  							<a href="#"><li>자주가는 메뉴 5</li></a>
  						</ul>
  					</div>