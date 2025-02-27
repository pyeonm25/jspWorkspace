<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSTL core 태그예제 - if </h3>
	
	<c:set var="country" value="korea" /> <!-- country = korea -->
	<c:if test="${ country ne null }">
		국가명: <c:out value="${country}" /> 		<br>
		국가명: ${country} 		<br>
	</c:if>
	
	<c:out value="${country}" />의 겨울은 춥다.		<br>
</body>
</html>



