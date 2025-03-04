package controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberDAO;
import member.MemberVO;

@WebServlet("/getJson.do")
public class _09_GetJsonTest extends HttpServlet {

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		request.setCharacterEncoding("utf-8");
//		// '{"id":"test6","passwd":"1234","name":"테스트6","address":"방배","tel":"010-111-6666"}'
//		String member = request.getParameter("member"); // jsonString 타입
//		MemberVO vo = new MemberVO();
//		try {
//		JSONParser jp = new JSONParser(); // json 데이터 객체로 해석해주는 클래스 객체 
//		JSONObject jb = (JSONObject) jp.parse(member); // // json 객체 타입
//		// {id:"test7",passwd:"1234", name:"테스트7", address:"사당", tel:"010-111-1234"};
//		
//		vo.setId((String)jb.get("id"));
//		vo.setPasswd((String)jb.get("passwd"));
//		vo.setName((String)jb.get("name"));
//		vo.setAddress((String)jb.get("address"));
//		vo.setTel((String)jb.get("tel"));
//		
//		int check = MemberDAO.getInstance().insertMember(vo);
//		response.getWriter().print(check);
//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("utf-8");
		// '{"id":"test6","passwd":"1234","name":"테스트6","address":"방배","tel":"010-111-6666"}'
		//String member = request.getParameter("member"); 
		
		String member = request.getReader().readLine();
		System.out.println(member);
		MemberVO vo = new MemberVO();
		try {
		JSONParser jp = new JSONParser(); // json 데이터 객체로 해석해주는 클래스 객체 
		JSONObject jb = (JSONObject) jp.parse(member); // // json 객체 타입
		// {id:"test7",passwd:"1234", name:"테스트7", address:"사당", tel:"010-111-1234"};
		
		vo.setId((String)jb.get("id"));
		vo.setPasswd((String)jb.get("passwd"));
		vo.setName((String)jb.get("name"));
		vo.setAddress((String)jb.get("address"));
		vo.setTel((String)jb.get("tel"));
		
		int check = MemberDAO.getInstance().insertMember(vo);
		response.getWriter().print(check);

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}