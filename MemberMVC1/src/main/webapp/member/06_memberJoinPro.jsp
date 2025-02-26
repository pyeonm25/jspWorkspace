<%@page import="kr.basic.member.MemberDAO"%>
<%@page import="kr.basic.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id="qwer";
String pw="1234";
String name="아무개";
int age=12;
String email="test@test.com";
String phone="010-1111-1234";

int cnt = MemberDAO.getInstance().insertAMember(id, pw, name, age, email, phone);
System.out.println("cnt= " + cnt);
%>

