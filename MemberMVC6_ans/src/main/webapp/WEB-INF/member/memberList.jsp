<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "../../part/header.jsp" %>
<script type="text/javascript">
  function deleteFn(id, num){
	  location.href="${ctx}/memberDelete.do?num="+num+"&id="+id; 
  }
  
  </script>
  <div class="inner">
    <h1>회원 목록</h1>

 <table>
  <tr>
    <td>번호</td>
    <td>아이디</td>
    <td>비밀번호</td>
    <td>이름</td>
    <td>나이</td>
    <td>이메일</td>
    <td>전화번호</td>
    <th>이미지</th>
    <td>삭제</td>
  </tr>
  <c:forEach var="vo" items="${list}">
      <tr>
    	    <td>${vo.num}</td>
    	    <td>  
    	    <a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
    	    <td>${vo.pass}</td>
    	    <td>${vo.name}</td>
    	    <td>${vo.age}</td>
    	    <td>${vo.email}</td>
    	    <td>${vo.phone}</td>
    	      <td>
    	      	   <c:if test="${vo.sFileName!=null}">
    	         <img src="/Uploads/${vo.sFileName}" width="60px" height="60px"/>
    	   </c:if>
    	      	   <c:if test="${vo.sFileName==null}">
    	         <img src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg" width="60px" height="60px">
    	   </c:if>
    	      </td>
    	    <td><input type="button" value="삭제" class="btn-del"  onclick="deleteFn('${vo.id}','${vo.num}')"
    	      	    <c:if test="${loginId!=vo.id and loginId!='admin'}"> 
    	              disabled="disabled"
          	      </c:if>/></td>
    	  </tr>    	 
  </c:forEach>

</table>
</div>
<%@ include file= "../../part/footer.jsp" %>