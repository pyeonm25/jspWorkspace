package kr.basic.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.basic.model.MemberDAO;
import kr.basic.utils.FileUtil;

public class MemberUploadImgController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
	
        try {
            int num = Integer.parseInt(request.getParameter("num"));
            
            String[] fnames = FileUtil.uploadFile(request,"uploadFile");
            
            if(fnames == null) {
            	response.getWriter().print("fail");
            	return null;
            }
            
            int cnt = MemberDAO.getInstance().memberUploadPhoto(num, fnames[0], fnames[1]);
            if (cnt > 0) {
                response.getWriter().print(fnames[1]);
            } else {
                System.err.println("num 이 올바르지 않은 형식 " + num);
                response.getWriter().print("fail");
            }
        } catch (NumberFormatException e) {
            System.err.println("error " + e.getMessage());
            response.getWriter().print("fail");
        }
        return null;
	}


}
