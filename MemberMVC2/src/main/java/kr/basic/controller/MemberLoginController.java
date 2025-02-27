package kr.basic.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.basic.model.MemberDAO;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/memberLogin.do")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		   String id = request.getParameter("id");
		   String pw = request.getParameter("pw");
		   
		   int log = MemberDAO.getInstance().checkLogin(id, pw);
			response.setContentType("text/html; charset=UTF-8");
			String ctx = request.getContextPath();
			PrintWriter writer = response.getWriter();
			//String msg = "<script> alert('%s'); location.href='%s%s' </script>";
		   if(log !=0) {
			   HttpSession session = request.getSession();
			   session.setAttribute("log", log );
			   writer.println("<script>alert('" + id + "님이 로그인하셨습니다'); location.href='"+ctx+"/memberList.do';</script>");
			  // String.format(msg, "로그인 성공" ,ctx , "/memberList.do");
		   }else {
				writer.println("<script>alert('로그인 실패'); location.href='member/memberLogin.jsp;</script>");
			 //  String.format(msg, "로그인 실패" ,"/member" , "/memberLogin.jsp");
		   }
		
		   writer.close();
		   
//		   RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//		   rd.forward(request, response);
		   
	}

}
