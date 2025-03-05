package kr.basic.controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.basic.model.MemberDAO;
import kr.basic.model.MemberDAO2;
import kr.basic.model.MemberVO;

public class MemberListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	//	ArrayList<MemberVO> list = MemberDAO.getInstance().memberList();

		ArrayList<MemberVO> list = MemberDAO2.getInstance().memberList();
		request.setAttribute("list", list);
		HttpSession session = request.getSession();
		if (session.getAttribute("log") != null) {
			int num = (int) session.getAttribute("log");
			request.setAttribute("num", num);

		} else {
			request.setAttribute("num", null);
		}
		return "memberList";

	}
}
