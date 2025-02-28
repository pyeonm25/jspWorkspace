package kr.basic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.basic.model.MemberDAO;

@WebServlet("/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			                             throws ServletException, IOException {
		  String ctx=request.getContextPath();
		  
		  if(request.getParameter("id")== null) {
			  response.sendRedirect(ctx+"/memberList.do");
			  return;
		  }
		  
		int cnt=MemberDAO.getInstance().memberDelete(request.getParameter("id"));
		
		if(cnt>0) {
			  response.sendRedirect(ctx+"/memberList.do");
		}else {
			throw new ServletException("not delete");	
		}
	}
}
