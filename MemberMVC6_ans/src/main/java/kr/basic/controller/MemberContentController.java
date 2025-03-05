package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.MemberVO;


public class MemberContentController implements Controller{


	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    String ctx=request.getContextPath();
		  int num =-1;
		  if(request.getParameter("num")==null){
			  return "redirect:"+ctx+"/memberLogin.do";
		  }else{
			  num = Integer.parseInt(request.getParameter("num"));
		  }
		  MemberVO vo=MemberDAO.getInstance().memberContent(num);
		request.setAttribute("vo", vo);
		return "memberContent"; 
	}


}
