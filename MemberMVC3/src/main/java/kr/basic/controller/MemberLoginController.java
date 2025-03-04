package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberLoginController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("memberLoginController 실행~~");
	
		String ctx = request.getContextPath();
		
		if(request.getParameter("id") == null) {
		  return "memberLogin";
		}
		
		// 로그인 처리 ~~ 
		String id = request.getParameter("id");
		
		return "redirect:"+ctx+"/memberList.do";
	}

}
