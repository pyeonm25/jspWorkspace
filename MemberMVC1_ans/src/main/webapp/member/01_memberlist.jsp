<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.basic.member.MemberDAO"%>
<%@page import="kr.basic.member.Member"%>
<%@page import="java.util.ArrayList"%>

<%@ include file= "header.jsp" %>
<%
ArrayList<Member> list = MemberDAO.getInstance().getMemberList();
%>
  <div class="inner">
  <h1 > 회원 목록</h1>
  <table>
 <tr>
    <th>번호</th>
    <th>아이디</th>
    <th>비밀번호</th>
    <th>이름</th>
    <th>나이</th>
    <th>이메일</th>
    <th>전화번호</th>
    <th>삭제</th>
  </tr>
  <% for(Member vo : list){ %>
    	  <tr>
    	    <td><%=vo.getNum()%></td>
    	    <td><a href="05_memberContent.jsp?num=<%=vo.getNum()%>"><%=vo.getId()%></a></td>
    	    <td><%=vo.getPass()%></td>
    	    <td><%=vo.getName()%></td>
    	    <td><%=vo.getAge()%></td>
    	    <td><%=vo.getEmail()%></td>
    	    <td><%=vo.getPhone()%></td>
    	    <td><button class="btn-del" onclick="location.href='06_memberDeletePro.jsp?num=<%=vo.getNum()%>'">삭제 </button></td>
    	  </tr>    	 
  <% } %>

</table>
</div>
<%@ include file= "footer.jsp" %>