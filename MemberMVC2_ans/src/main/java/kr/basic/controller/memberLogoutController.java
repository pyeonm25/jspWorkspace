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


@WebServlet("/memberLogout.do")
public class memberLogoutController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
	
		response.setContentType("text/html; charset=UTF-8");
		String ctx = request.getContextPath(); 
		PrintWriter writer = response.getWriter();
		String msg = "<script> alert('%s'); location.href='%s/%s' </script>";
		String data = String.format(msg, "로그아웃 성공" , ctx, "index.jsp");
		writer.println(data); 
		writer.close(); // 리소스 닫기 
	}

	}
