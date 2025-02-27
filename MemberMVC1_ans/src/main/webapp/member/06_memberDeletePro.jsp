<%@page import="kr.basic.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//int cnt=MemberDAO.getInstance().memberDelete(request.getParameter("id"));
int num =-1;
if(request.getParameter("num")==null){
	  response.sendRedirect("01_memberlist.jsp");
	  return;
}else{
	  num = Integer.parseInt(request.getParameter("num"));
}

int cnt =MemberDAO.getInstance().deleteOneMember(num);	
if(cnt>0) {

		%>
		   <script>
alert('회원 삭제 성공 ')
location.href="01_memberlist.jsp";
</script>
		<% 
		
	}else {
		%>
		   <script>
		alert('회원 삭제 실패 ')
		location.href="01_memberlist.jsp";
		</script>
		<% 	
	}
%>    