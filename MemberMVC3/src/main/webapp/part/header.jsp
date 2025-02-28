<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberMVC3</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>

	<header>
		<div class="inner">
			<div class="gnb">

				<a href='${ctx}/memberList.do'>회원 목록</a>
				<c:if test="${log eq null}">
					<a href="${ctx}/member/memberInsert.jsp">회원 가입</a>
                    <a href="${ctx}/member/memberLogin.jsp">로그인</a>
				</c:if>
				<c:if test="${log ne null}">
					<a href='${ctx}/memberContent.do?num=${log}'> 내정보</a>
					<a href='${ctx}/memberLogout.do'> 로그아웃</a>
				</c:if>

			</div>
		</div>

	</header>
	<main>