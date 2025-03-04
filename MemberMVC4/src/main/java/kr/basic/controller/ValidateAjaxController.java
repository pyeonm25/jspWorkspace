package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;

public class ValidateAjaxController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 자바스크립트로 비동기로 불러오는 메서드 
		String id = request.getParameter("id");
		System.out.println("id= " + id);
		
		String data = MemberDAO.getInstance().checkMemberId(id) == null? "valid" : "notValid";
		
		// 서버에서 자바스크립트로 보내주는 값 
		response.getWriter().print(data);
		

		return null; // 프론트 컨트트롤러에서 이동할 페이지가 없으면 null 로 처리
	}

}
