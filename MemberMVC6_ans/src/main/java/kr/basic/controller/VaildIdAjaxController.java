package kr.basic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;

public class VaildIdAjaxController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// $.ajax();
		String id=request.getParameter("id"); // { "id" : id  }
		String passData = MemberDAO.getInstance().isValidId(id)? "notValid" : "valid";
	
		// ajax() 함수에 만들어놓은 callback함수 응답
		response.getWriter().print(passData); // "notValid" : "valid";
		
		return null;
	}

}
