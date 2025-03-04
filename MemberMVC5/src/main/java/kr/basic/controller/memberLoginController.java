package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.basic.model.MemberDAO;

public class memberLoginController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
	      if(request.getParameter("id") == null) {
	        	return "memberLogin";
	        }
		String pw = request.getParameter("pw");

		String dbPw = MemberDAO.getInstance().checkMemberId(id);
		
		String ctx=request.getContextPath();

		if (dbPw == null || dbPw.equals(pw) == false) {
			return "redirect:"+ctx+"/memberLogin.do";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("log", MemberDAO.getInstance().getMemberNo(id));
			return "redirect:"+ctx+"/memberList.do";
		}
		
	}
}
