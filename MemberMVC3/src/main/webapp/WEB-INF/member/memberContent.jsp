<%@page import="kr.basic.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "../../part/header.jsp" %>
   
  <div class="inner">
<h1> ${vo.name}  회원의 상세보기 </h1>
<form action="${ctx}/memberUpdate.do" method="post">
<input type="hidden" name="num" value="${vo.num}"/>
<table>
  <tr>
    <td>번호</td>
     <td class="left"> ${vo.num}</td>
  </tr>
   <tr>
    <td>아이디</td>
     <td class="left">${vo.id}</td>
  </tr>
   <tr>
    <td>비밀번호</td>
   <td class="left">${vo.pass}</td>
  </tr>  
    <tr>
    <td>이름</td>
   <td class="left">${vo.name}</td>
  </tr> 
  <tr>
    <td>나이</td>
    <td><input type="number" name="age" value="${vo.age}" required/></td>
  </tr> 
     <tr>
    <td>이메일</td>
    <td><input type="text" name="email" value="${vo.email}" required"/></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input type="text" name="phone" value="${vo.phone}" required/></td>
  </tr> 

  <tr>
    <td colspan="2">
       <button class="btn-submit"> 수정 </button>
    </td>
  </tr>
</table>
</form>
  </div>
<%@ include file= "../../part/footer.jsp" %>