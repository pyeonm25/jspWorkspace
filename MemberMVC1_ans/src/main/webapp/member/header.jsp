<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mvc1 </title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>

  <header>
    <div class="inner">
          <div class="gnb">  

            <a href='01_memberlist.jsp'>회원 목록</a>
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