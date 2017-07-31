<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
</head>
<body>
	<div style="background-image:/resources/images/error.jpg">
		<c:if test="${error == '404'}">
			<h2>${title}</h2>
			<h5>${message }</h5>
		</c:if>
		<c:if test="${error == '500'}">
			<h2>${title}</h2>
			<h5>${message }</h5>
		</c:if>
		<c:if test="${error == 'exception'}">
			<h2>${title}</h2>
			<h5>${message }</h5>
		</c:if>
		<br> <a href="/homeMain/main">메인으로</a> <a
			href="javascript:history.back();">뒤로</a>
	</div>
</body>
</html>

