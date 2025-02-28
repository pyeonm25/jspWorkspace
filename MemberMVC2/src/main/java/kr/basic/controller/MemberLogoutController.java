package kr.basic.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/memberLogout.do")
public class MemberLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 응답 바디 
		response.setContentType("text/html; charset=UTF-8");
		String ctx = request.getContextPath(); // 프로젝트 이름 
		//PrintWriter html 문서 바디에 값을 넣어준은 객체 
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String msg = "<script> alert('%s'); location.href='%s/%s' </script>";
		
		String data = String.format(msg, "로그아웃 성공" , ctx, "index.jsp");
		
		writer.println(data); 
		writer.close(); // 리소스 닫기 
		
		
	}

}
