<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "../part/header.jsp" %>
  <div class="inner">
  <h1 > 회원가입</h1>
<form action="${ctx}/memberInsert.do" method="post">
  <table>
  <tr>
    <td>아이디</td>
    <td><input type="text" name="id" required/></td>
  </tr>
  <tr>
    <td>패스워드</td>
    <td><input type="password" name="pass" required/></td>
  </tr>
  <tr>
    <td>이름</td>
    <td><input type="text" name="name" required/></td>
  </tr>
  <tr>
    <td>나이</td>
    <td><input type="number" name="age" required/></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input  type="text" name="email" required/></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input type="text" name="phone" required/></td>
  </tr>
  <tr>
    <td colspan="2" class="btn-group">
          <button class="btn-submit"> 가입 </button>
          <button class="btn-cancel"> 취소 </button>
    </td>
  </tr>
</table>
</form>
</div>
<%@ include file= "../part/footer.jsp" %>