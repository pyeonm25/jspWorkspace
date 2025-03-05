package com.rentcar.controller.rentcar;

import java.io.IOException;

import com.rentcar.dao.RentCarDAO;
import com.rentcar.frontController.Controller;
import com.rentcar.vo.Rentcar;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
	

public class CarInfoController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int num = Integer.parseInt(request.getParameter("num"));
		Rentcar vo = RentCarDAO.getInstance().getOneCar(num);
		
	     System.err.println("vo=" + vo);

		request.setAttribute("vo", vo);
		return "rentcar/rentcarInfo";
	}

}
