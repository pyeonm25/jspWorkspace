package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.Member;


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
		  Member vo=MemberDAO.getInstance().memberContent(num);
		// 객체바인딩
		request.setAttribute("vo", vo);
		return "memberContent"; //뷰의 이름만 리턴
		//return "/WEB-INF/member/memberContent.jsp";
	}


}
