package kr.basic.frontcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.controller.Controller;
import kr.basic.controller.MemberListController;

import java.io.IOException;


@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getRequestURI();  // /MemberMVC3/test.do
		String ctx = request.getContextPath(); // /MemberMVC3
		String command = url.substring(ctx.length()); // /test.do
		System.out.println("command : " + command); // /memberList.do
		
		Controller controller = null;
		HandlerMapping mapping = new HandlerMapping(); // 우리 url-pattern 매핑값
		controller=mapping.getController(command); // new MemberListController() 주소값 
		
		controller.requestHandler(request, response);
	}

}
