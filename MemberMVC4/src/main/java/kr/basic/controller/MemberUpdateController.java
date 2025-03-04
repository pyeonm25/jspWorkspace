package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.Member;

public class MemberUpdateController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    if(request.getParameter("num")== null) {
	    	return "memberContent";
	    }
		int num=Integer.parseInt(request.getParameter("num"));
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");

		Member vo=new Member();
		vo.setNum(num);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		String ctx=request.getContextPath();
		int cnt=MemberDAO.getInstance().memberUpdate(vo);
		if(cnt>0) {
			return "redirect:"+ctx+"/memberList.do";
			 
		 }else {
		    	throw new ServletException("not update");	    	
		 }	
	}
}
