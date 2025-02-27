<%@page import="kr.basic.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

int num = 4;
int age = 20;
String email="test@test444.com";
String phone="010-0000-1342";

MemberDAO.getInstance();
int cnt = 0;
if(cnt == 1){
%>

<script>
   alert('회원정보 수정 완료');
   location.href='01_memberlist.jsp';
</script>
<%}else{ %>
<script>
   alert('회원정보 수정 실패');
   history.back();
</script>
<% }  %>