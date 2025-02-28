<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.basic.model.MemberDAO"%>
<%@page import="kr.basic.model.Member"%>
<%@page import="java.util.ArrayList"%>

<%@ include file= "../../part/header.jsp" %>

<script type="text/javascript">
  function deleteFn(id){
	  location.href="${ctx}/memberDelete.do?id="+id; 
  }
</script>


<div class="inner">
  <h1>회원 목록</h1>
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

        <c:forEach var="vo" items="${list}">
          <tr>
            <td>${vo.num}</td>
            <td><a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
            <td>${vo.pass}</td>
            <td>${vo.name}</td>
            <td>${vo.age}</td>
            <td>${vo.email}</td>
            <td>${vo.phone}</td>
            <td><button class="btn-del" onclick="deleteFn('${vo.id}')"">삭제 </button></td>
          </tr>
        </c:forEach>
 
  </table>
</div>
<%@ include file= "../../part/footer.jsp" %>