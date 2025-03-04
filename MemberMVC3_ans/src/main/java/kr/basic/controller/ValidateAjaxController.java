package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ValidateAjaxController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 자바스크립트로 비동기로 불러오는 메서드 
		String id = request.getParameter("id");
		System.out.println("id= " + id);
		String msg ="서버에서 값 전달 했음";
		// 자바스크립트 함수로 repsone 객체 리턴 
		response.getWriter().print(msg);
		return null; // 프론트 컨트트롤러에서 이동할 페이지가 없으면 null 로 처리
	}

}
