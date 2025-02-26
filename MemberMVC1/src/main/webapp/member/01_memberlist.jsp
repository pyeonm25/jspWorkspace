<%@page import="kr.basic.member.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.basic.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./header.jsp" %>

<% ArrayList<Member> list =  MemberDAO.getInstance().getMemberList(); %>
<h1> 회원 전체 목록 </h1>


<%@include file="./footer.jsp" %>