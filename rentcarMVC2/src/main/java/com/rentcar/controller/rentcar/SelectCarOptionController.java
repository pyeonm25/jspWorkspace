package com.rentcar.controller.rentcar;

import java.io.IOException;

import com.rentcar.dao.RentCarDAO;
import com.rentcar.frontController.Controller;
import com.rentcar.vo.Rentcar;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectCarOptionController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		System.out.println("qty= " + qty);
		Rentcar car =  RentCarDAO.getInstance().getOneCar(num);
		request.setAttribute("car", car);
		request.setAttribute("qty", qty);
		return "rentcar/carOption";
	}

}
