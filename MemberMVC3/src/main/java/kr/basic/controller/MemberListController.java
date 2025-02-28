package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// POJO => 일반 자바클래스 
// POJO에서 하는일 : pro 에서 하는 거와 동일 
//1. model 연동
//2. 객체바인딩
//3. 페이지이동
public class MemberListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("memberListContoller 실행~~");
		
		return null;
	}

}
