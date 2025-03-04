package kr.basic.frontcontroller;

import java.util.HashMap;

import kr.basic.controller.Controller;
import kr.basic.controller.*;


public class HandlerMapping {
  private HashMap<String, Controller> mappings;
  public HandlerMapping() {
	  // pojo => plan of java object => 일반 클래스 
	  mappings=new HashMap<String, Controller>();
	  mappings.put("/memberList.do", new MemberListController());
	  mappings.put("/memberInsert.do", new MemberInsertController());
	  mappings.put("/memberContent.do", new MemberContentController());
	  mappings.put("/memberUpdate.do", new MemberUpdateController());
	  mappings.put("/memberDelete.do", new MemberDeleteController());
	  mappings.put("/memberLogin.do", new memberLoginController());
	  mappings.put("/memberLogout.do", new memberLogoutController());
	  mappings.put("/validateAjax.do" , new ValidateAjaxController());
  }
  public Controller getController(String key) { // key=>/memberList.do
	  return mappings.get(key);
  }
}
