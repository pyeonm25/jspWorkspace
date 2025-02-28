package kr.basic.controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.Member;
import kr.basic.model.MemberDAO;

// POJO => 일반 자바클래스 
// POJO에서 하는일 : pro 에서 하는 거와 동일 
//1. model 연동
//2. 객체바인딩
//3. 페이지이동 => 리턴값 1.redirect   2.foward
public class MemberListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("memberListContoller 실행~~");
		//1. model 연동
		ArrayList<Member> list=MemberDAO.getInstance().memberList();	
		//2. 객체바인딩
		request.setAttribute("list", list);
		//3. 페이지이동 => 리턴값 1.redirect   2.foward
		return "memberlist";
	}

}
