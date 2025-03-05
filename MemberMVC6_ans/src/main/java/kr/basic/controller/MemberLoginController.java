package kr.basic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.basic.model.MemberDAO;

public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (request.getParameter("id") == null) {
			return "memberLogin";
		}
		String pw = request.getParameter("pw");

		String ctx = request.getContextPath();

		if (MemberDAO.getInstance().checkLogin(id, pw)==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인 실패'); history.back();</script>");
			writer.close();
			return null;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("log", MemberDAO.getInstance().getMemberNo(id));
			System.out.println("sesstion log" + session.getAttribute("log"));
			session.setAttribute("loginId", id);
			return "redirect:" + ctx + "/memberList.do";
		}

	}
}
