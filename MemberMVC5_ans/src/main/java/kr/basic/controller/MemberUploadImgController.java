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

public class MemberUploadImgController implements Controller {
	private static final String UPLOAD_DIR = "C:"+File.separator+"Uploads";
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		String saveDirectory = UPLOAD_DIR;
		Path saveDirPath = Paths.get(saveDirectory);
		if (!Files.isDirectory(saveDirPath)) {
			Files.createDirectories(saveDirPath);
		}
		System.out.println("saveDirectory = " + saveDirectory);

        
        Part filePart = null;
        String oFileName = null;
        String sFileName = null;
        try {
        	filePart = request.getPart("uploadFile");
        	if (filePart == null || filePart.getSize() == 0) {
                System.out.println("파일 업로드 안됨 ");
                response.getWriter().print("fail");
                return null;
        	}
        	oFileName = extractFileName(filePart);
            if (oFileName.isEmpty()) {
                System.out.println("유효하지 않은 파일 이름");
                response.getWriter().print("fail");
                return null;
            }

            
            sFileName =  System.currentTimeMillis() +"_"+oFileName ; // 중복없이 파일이름 지정
            filePart.write(saveDirPath.resolve(sFileName).toString()); // 파일저장
          
            System.out.println("fileType = " + filePart.getContentType());
            System.out.println("저장된 파일 이름 " + sFileName);
        } catch (IOException e) {
            System.err.println("File upload failed: " + e.getMessage());
            response.getWriter().print("fail");
            return null;
        }
        
		
        try {
            int num = Integer.parseInt(request.getParameter("num"));
            int cnt = MemberDAO.getInstance().memberUploadPhoto(num, oFileName, sFileName);
            if (cnt > 0) {
                response.getWriter().print(sFileName);
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
	
	private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                return Paths.get(fileName).getFileName().toString();
            }
        }
        return "";
    }

}
