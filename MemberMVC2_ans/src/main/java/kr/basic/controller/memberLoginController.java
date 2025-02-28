package kr.basic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.basic.model.MemberDAO;

@WebServlet("/memberLogin.do")
public class memberLoginController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		String dbPw = MemberDAO.getInstance().checkMemberId(id);
		response.setContentType("text/html; charset=UTF-8");
		String ctx=request.getContextPath();
		PrintWriter writer = response.getWriter();
		if (dbPw == null || dbPw.equals(pw) == false) {
			writer.println("<script>alert('로그인 실패'); history.back();</script>");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("log", MemberDAO.getInstance().getMemberNo(id));
			writer.println("<script>alert('" + id + "님이 로그인하셨습니다'); location.href='"+ctx+"/memberList.do';</script>");
		}
		
		writer.close();

	}

}
