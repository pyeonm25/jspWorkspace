package kr.basic.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.basic.model.MemberDAO;
import kr.basic.model.MemberVO;
import kr.basic.utils.FileUtil;

public class MemberDeleteImgController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));

		MemberVO vo = MemberDAO.getInstance().memberContent(num);
	 	String delFileName = vo.getsFileName();
	
	 	if(!FileUtil.isFileDeleted(delFileName)) {
	 		response.getWriter().print("fail");
	 		return null;
	 	}
	 	
		int cnt = MemberDAO.getInstance().memberUploadPhoto(num, null, null);
		if (cnt > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		
	 	}
		return null;
	}

}
