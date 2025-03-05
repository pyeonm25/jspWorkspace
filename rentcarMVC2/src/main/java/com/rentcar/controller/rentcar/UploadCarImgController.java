package com.rentcar.controller.rentcar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rentcar.frontController.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


public class UploadCarImgController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

         String savePath = request.getServletContext().getInitParameter("UPLOAD_DIR");
         System.out.println("savePath= " + savePath);
         
     	Path saveDirPath = Paths.get(savePath);
		if (!Files.isDirectory(saveDirPath)) {
			Files.createDirectories(saveDirPath);
		}

		  
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
            response.getWriter().print(sFileName);
        } catch (IOException e) {
            System.err.println("File upload failed: " + e.getMessage());
            response.getWriter().print("fail");
            return null;
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
