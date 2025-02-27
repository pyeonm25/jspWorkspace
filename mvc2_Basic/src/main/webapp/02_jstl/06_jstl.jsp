<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSTL fmt 태그예제 - formatNumber</h3>
	
	<p>number  : <fmt:formatNumber value="12345.678" type="number" />
	
	<p>currency: <fmt:formatNumber value="12345.678" type="currency" currencySymbol="￦" />
	
	<p>percent : <fmt:formatNumber value="12345.678" type="percent" />
	
	<p>pattern=".0" : <fmt:formatNumber value="12345.678" pattern=".00000" />
</body>
</html>