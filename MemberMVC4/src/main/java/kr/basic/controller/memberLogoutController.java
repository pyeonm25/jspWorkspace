package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class memberLogoutController implements Controller{ 


	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("log");
		String ctx=request.getContextPath();
		  response.sendRedirect(ctx+"/memberList.do");
		return null;
	}

	}
