package kr.basic.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.Member;
import kr.basic.model.MemberDAO;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Member> list = MemberDAO.getInstance().getMemberList();
		
		// memberList.jsp
		
		// 객체 바인딩 
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/memberlist.jsp");
		rd.forward(request, response);
		
		// 페이지 이동 
		// 1. respone.sendRedirect => 신규 request, respone 객체 
		// 2. RequestDispatcher => rd.forward => 기존에 바인딩한 request, respone 객체 
		
	}

}
