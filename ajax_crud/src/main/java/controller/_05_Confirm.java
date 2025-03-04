package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberDAO;
@SuppressWarnings("serial")
@WebServlet("/confirm.do")
public class _05_Confirm extends HttpServlet {
       
// ajax 
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		
		MemberDAO manager = MemberDAO.getInstance();
		int check= manager.confirmId(id);
		System.out.println("check=" + check);
		response.getWriter().print(check);
		
	}
	
}
