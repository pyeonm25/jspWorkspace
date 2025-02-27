<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mvc2 </title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>

  <header>
    <div class="inner">
          <div class="gnb">  

            <a href='${ctx}/memberlist.do'>회원 목록</a>
      <%if(session.getAttribute("log")==null){ %>
            <a href='04_memberInsert.jsp'>회원 가입</a>
            <a href='02_memberLogin.jsp'> 로그인</a>
               <%}else{ %>
            <a href='05_memberContent.jsp?num=<%=(int)session.getAttribute("log") %>'> 내정보</a>
            <a href='03_memberLogoutPro.jsp'> 로그아웃</a>

            
                 <%} %>
            
        </div>
      </div>

</header>
<main>