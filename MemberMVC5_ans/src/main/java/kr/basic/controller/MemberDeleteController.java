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

public class MemberDeleteController implements Controller {
	private static final String UPLOAD_DIR = "C:"+File.separator+"Uploads";
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath();

		MemberVO vo = MemberDAO.getInstance().memberContent(Integer.parseInt(request.getParameter("num")));
		String delFileName = vo.getsFileName();

		int cnt = MemberDAO.getInstance().memberDelete(request.getParameter("id"));

		if (cnt > 0) {
			// 파일 삭제
			String saveDirectory = UPLOAD_DIR;
			//String saveDirectory = request.getServletContext().getRealPath("/Uploads");
			Path filePath = Paths.get(saveDirectory, delFileName);
			try {
				Files.deleteIfExists(filePath);
				System.out.println("파일 삭제 완료");
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "redirect:" + ctx + "/memberList.do";

		} else {
			throw new ServletException("not delete");
		}
	}
}
