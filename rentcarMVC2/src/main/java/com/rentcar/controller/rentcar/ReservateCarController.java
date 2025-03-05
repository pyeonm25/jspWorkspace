package com.rentcar.controller.rentcar;

import java.io.IOException;

import com.google.gson.Gson;
import com.rentcar.dao.RentCarDAO;
import com.rentcar.dao.ReservationDAO;
import com.rentcar.frontController.Controller;
import com.rentcar.vo.Reservation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ReservateCarController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
		request.setCharacterEncoding("utf-8");

		String jsonData = request.getReader().readLine();
		System.out.println("jsonData=" + jsonData);
        Gson gson = new Gson();
		Reservation book = gson.fromJson(jsonData,com.rentcar.vo.Reservation.class);
		int cnt = ReservationDAO.getInstance().addReservation(book);
		RentCarDAO.getInstance().reduceTotalQty(book);

		if(cnt == 1) {
			response.getWriter().print(cnt);
			
		}else {
			response.getWriter().print("fali");
		}
		

	 	

		return null;
	 	}


}
