package kr.basic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.basic.model.MemberDAO;
import kr.basic.model.Member;

@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String ctx = request.getContextPath();

		String id = request.getParameter("id");

		if (!MemberDAO.getInstance().isValidId(id)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			String msg = "<script> alert('%s'); history.back() </script>";
			String data = String.format(msg, "이미 존재하는 아이디 입니다" );
			writer.println(data);
			writer.close(); // 리소스 닫기

			RequestDispatcher rd = request.getRequestDispatcher("/member/memberInsert.jsp");
			rd.forward(request, response);
			return;
		}

		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); // "40"->40
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		// 파라메터수집(VO)
		Member vo = new Member();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);

		int cnt = MemberDAO.getInstance().memberInsert(vo);
		if (cnt > 0) {
			// 가입 성공하면 페이지 요청
			response.sendRedirect(ctx + "/memberList.do");
		} else {
			// throw new ServletException("not insert");
			System.out.println("가입 실패");
		}

	}
}
