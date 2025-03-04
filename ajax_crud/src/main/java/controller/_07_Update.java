package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;
import member.MemberVO;

// ctrl + shfit + o 
@SuppressWarnings("serial")
@WebServlet("/update.do")
public class _07_Update extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String passwd = request.getParameter("passwd");

		MemberDAO manager = MemberDAO.getInstance();
		// 아이디와 비밀번호에 해당하는 사용자의 정보를 얻어냄
		MemberVO m = manager.getMember(id, passwd);
		request.setAttribute("member", m);
		
		RequestDispatcher dis = request.getRequestDispatcher("07_update2.jsp");
		dis.forward(request, response);

	}

}
