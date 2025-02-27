
<%@page import="kr.basic.member.MemberDAO"%>
<%@page import="kr.basic.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "header.jsp" %>
<%
  int num =-1;
  if(request.getParameter("num")==null){
	  response.sendRedirect("01_memberlist.jsp");
	  return;
  }else{
	  num = Integer.parseInt(request.getParameter("num"));
  }
  
  Member vo = MemberDAO.getInstance().getMemberByNum(num);
%>    
  <div class="inner">

<%  if(vo==null){ %>
  <h1> 일치하는 회원이 없습니다 </h1>
<%}else{ %>
<h1 > <%=vo.getName()%> 회원의 상세보기 </h1>
<form action="07_memberUpdatePro.jsp" method="post">
<input type="hidden" name="num" value="<%=vo.getNum()%>"/>
<table>
  <tr>
    <td>번호</td>
    <td class="left"> <%=vo.getNum()%></td>
  </tr>
   <tr>
    <td>아이디</td>
    <td class="left"><%=vo.getId()%></td>
  </tr>
   <tr>
    <td>비밀번호</td>
    <td class="left"><%=vo.getPass()%></td>
  </tr>  
    <tr>
    <td>이름</td>
    <td class="left"><%=vo.getName()%></td>
  </tr> 
  <tr>
    <td>나이</td>
    <td><input type="number" name="age" value="<%=vo.getAge()%>" required /></td>
  </tr> 
     <tr>
    <td>이메일</td>
    <td><input type="text" name="email" value="<%=vo.getEmail()%>" required /></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input type="text" name="phone" value="<%=vo.getPhone()%>" required/></td>
  </tr> 

  <tr>
    <td colspan="2">
       <button class="btn-submit"> 수정 </button>
    </td>
  </tr>
</table>
</form>
  <% } %>
  </div>
  <%@ include file= "footer.jsp" %>