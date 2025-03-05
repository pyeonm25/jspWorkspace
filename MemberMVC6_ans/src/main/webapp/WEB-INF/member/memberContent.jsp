
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "../../part/header.jsp" %>
  <div class="inner">

<c:if test="${vo==null}">
  <h1> 일치하는 회원이 없습니다 </h1>
</c:if>
<c:if test="${vo ne null}">
<h1> ${vo.name}  회원의 상세보기 </h1>
<form action="${ctx}/memberUpdate.do" method="post">
<input type="hidden" name="num" id="num" value="${vo.num}"/>
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
    <td>프로필 이미지</td>
        <td>
    	      	   <c:if test="${vo.sFileName!=null}">
    	         <img src="/Uploads/${vo.sFileName}" id="photo"/>
    	   </c:if>
    	      	   <c:if test="${vo.sFileName==null}">
    	         <img src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg" id="photo" class="default">
    	   </c:if>
    	      </td>
  </tr> 
  <tr>
    <td colspan="2" align="center">
       <input type="button" value="사진 업로드" class='btn-submit' id="uploadBtn"  <c:if test="${loginId!=vo.id and loginId!='admin'}"> 
    	              disabled="disabled"
          	      </c:if>/> 
       <input type="submit" value="수정하기" class='btn-submit' <c:if test="${loginId!=vo.id and loginId!='admin'}"> 
    	              disabled="disabled"
          	      </c:if>/>
       <input type="button" value="사진 삭제" class='btn-cancel' id="deleteBtn"   <c:if test="${loginId!=vo.id and loginId!='admin'}"> 
    	              disabled="disabled"
          	      </c:if>/> 
    </td>
  </tr>
</table>


</form>

<div id="upload">
<form id="imgForm" enctype="multipart/form-data"">
<input id="uploadFile" type="file" name="uploadFile" accept="image/*">
<input type="hidden" name="num" value="${vo.num}"/>
</form>
</div>
</c:if>
</div>

<script src="${ctx}/js/content.js"></script>