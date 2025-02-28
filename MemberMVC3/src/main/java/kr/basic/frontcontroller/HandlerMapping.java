package kr.basic.frontcontroller;

import java.util.HashMap;

import kr.basic.controller.Controller;
import kr.basic.controller.MemberListController;

//우리가 web.xml에게 *.do 모든 페이지 위임 
// 직접 url 맵핑을 시켜주기 
public class HandlerMapping {

	private HashMap<String,Controller> mappings;
	
	// 우리가 직접 pojo 클래스 맵핑을 해준다
	public HandlerMapping() {
		mappings = new HashMap<String,Controller>();
		mappings.put("/memberList.do", new MemberListController());
		
	}
	public Controller getController(String key) {
		return mappings.get(key);  // new MemberListController() 주소값 리턴
	}
	
	
}
