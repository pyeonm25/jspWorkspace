package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;

public class MemberDeleteController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx=request.getContextPath(); //MemberMVC3
		int cnt=MemberDAO.getInstance().memberDelete(request.getParameter("id"));
		
		if(cnt>0) {
			return "redirect:"+ctx+"/memberList.do";
	
		}else {
			throw new ServletException("not delete");	
		}
	}
}
