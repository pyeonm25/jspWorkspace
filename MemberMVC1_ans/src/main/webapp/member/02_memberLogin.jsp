<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "header.jsp" %>
  <div class="inner">
  <h1 > 로그인</h1>

<form action="02_memberLoginPro.jsp" method="post">
  <table>
  <tr>
    <td>아이디</td>
    <td><input type="text" name="id"/></td>
  </tr>
  <tr>
    <td>패스워드</td>
    <td><input type="password" name="pw"/></td>
  </tr>
  <tr>
    <td colspan="2">
      <button class="btn-submit"> 로그인 </button>
    </td>
  </tr>
</table>
</form>
</div>
<%@ include file= "footer.jsp" %>