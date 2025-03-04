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

public class MemberDeleteImgController implements Controller {
	private static final String UPLOAD_DIR = "C:"+File.separator+"Uploads";
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));

		// 파일 삭제
		//String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		String saveDirectory = UPLOAD_DIR;
		MemberVO vo = MemberDAO.getInstance().memberContent(num);
		System.out.println("vo sname=" + vo.getsFileName());
		Path filePath = Paths.get(saveDirectory, vo.getsFileName());
		try {
			Files.deleteIfExists(filePath);
			System.out.println("파일 삭제 완료");
		} catch (IOException e) {
			e.printStackTrace();
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
