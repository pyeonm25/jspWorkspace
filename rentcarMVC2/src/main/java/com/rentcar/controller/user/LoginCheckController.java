package com.rentcar.controller.user;

import java.io.IOException;

import com.rentcar.dao.UserDAO;
import com.rentcar.frontController.Controller;
import com.rentcar.vo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginCheckController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id"); 
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		User vo = new User();
		vo.setUserid(id);
		vo.setPwd(pw);
		
	
		String userid = dao.userLogin(vo);  // 값이 없으면 null 리턴함 
		System.out.println("userid=" + userid);
		
		HttpSession session = request.getSession();
		if(userid!=null) {
			session.setAttribute("userId", id);
			
			if(userid.equals("admin")) {
				session.setAttribute("admin", id);
			}
			response.getWriter().print(userid);
		}else {
			response.getWriter().print("null");
		}
		
	

		return null;
	}

}
