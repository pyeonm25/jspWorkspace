package kr.basic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.Member;


@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			                                  throws ServletException, IOException {

		ArrayList<Member> list=MemberDAO.getInstance().memberList();		

		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("member/memberlist.jsp");
		rd.forward(request, response); //-----------------------------------▲
		
	}
}
