package com.rentcar.controller.user;
import java.io.IOException;

import com.rentcar.frontController.Controller;
import com.rentcar.vo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserInfoController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userId");
		User user = com.rentcar.dao.UserDAO.getInstance().getOneUser(id);
		if(user!= null) {
		request.setAttribute("vo", user);
		return "user/userInfo";
		}else {
			System.out.println("db연동실패 UserDAO.getInstance().getOneUser(id)");
			return "main";
		}
	
	}

}
