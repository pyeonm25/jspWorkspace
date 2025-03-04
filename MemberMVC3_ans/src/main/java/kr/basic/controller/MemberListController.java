package kr.basic.controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.Member;

public class MemberListController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POJO가 해야할 일의 범위
		// 1. Model 연동
		ArrayList<Member> list=MemberDAO.getInstance().memberList();	
		// 2. 객체바인딩
		request.setAttribute("list", list);
		// member/memberList.jsp
		// 다음페이지는 
		// 3.다음페이지정보(View)
		return "memberList";

	}
}
