package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.Member;

@WebServlet("/memberContent.do")
public class MemberContentController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			                                     throws ServletException, IOException {

	
		String ctx=request.getContextPath();
		  int num =-1;
		  if(request.getParameter("num")==null){
			  response.sendRedirect(ctx+"/memberList.do");
			  return;
			  
		  }
		num = Integer.parseInt(request.getParameter("num"));
		  
		  Member vo=MemberDAO.getInstance().memberContent(num);
		// 객체바인딩
		request.setAttribute("vo", vo);
		RequestDispatcher rd=request.getRequestDispatcher("member/memberContent.jsp");
		rd.forward(request, response); //-----------------------------------▲
	}
}
