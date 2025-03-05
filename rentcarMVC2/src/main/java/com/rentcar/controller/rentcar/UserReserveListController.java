package com.rentcar.controller.rentcar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.rentcar.dao.ReservationDAO;
import com.rentcar.frontController.Controller;
import com.rentcar.vo.JoinCarView;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class UserReserveListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("userId")== null) {
			request.setAttribute("msg", "로그인 후 이용해 주세요 ");
			return "main";
		}
		
		String userid = (String)session.getAttribute("userId");
		
		ArrayList<JoinCarView> list = (ArrayList)ReservationDAO.getInstance().getJoinCarView(userid);
		if(list.size() == 0) {
			System.out.println("렌트한 차량이 없습니다 예약 먼저해주세요");
			request.setAttribute("msg", " 렌트한 차량이 없습니다 예약 먼저해주세요 ");
			return "main";
		}
		
	    for(JoinCarView vo : list) {
	    	if(vo == null) break;
			// 차량 총 금액
		 	int totalCar = vo.getPrice() * vo.getQty() * vo.getDday();
		 	
		 	// 옵션 금액
	        int option =0;
		 	// 선택 시(1), 10,000원 추가
		 	option = vo.getUsein()== 1 ?  option+10000 : option;
			option = vo.getUseseat()== 1 ?  option+10000 : option;
			option = vo.getUsewifi()== 1 ?  option+10000 : option;
		 	
		 	int totalOption = vo.getQty() * vo.getDday() *option;
		 	
		 	vo.setTotalPrice(totalOption + totalCar); 
		 	
		 	LocalDate rentDate = LocalDate.parse(vo.getRday());
		 	LocalDate endDate = rentDate.plusDays(vo.getDday());
		 	vo.setEndDate(endDate.toString());
	    }
		
	   request.setAttribute("list", list);
		
		return "rentcar/userReserveList";
	}

}
