package kr.basic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberLogoutController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("memberLogoutController 실행~~");
	
		response.setContentType("text/html; chareset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃'); history.back(); </script>");
		
		return null;
	}

}
