package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload.do")
@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5)
public class uploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String applicationPath = request.getServletContext().getRealPath("");
	        String saveDirectory = applicationPath + "uploads";
			System.out.println("applicationPath = " + applicationPath);
			System.out.println("saveDirectory = " + saveDirectory);
	
			Path saveDirPath = Paths.get(saveDirectory);
			if (!Files.isDirectory(saveDirPath)) {
				Files.createDirectories(saveDirPath);
			}

	  
	        
	        Part filePart = null;
	        String oFileName = null; //  파일 업로드 했을때 파일 이름 
 	        String sFileName = null; // 파일 업로드 후 저장 파일 이름 
	        Long fileSize =0L;
	        
	        try {
	        	// part 객체 안에 이미 우리가 올린 파일 데이터 저장 
	        	filePart = request.getPart("uploadFile"); //  <input type="file" name="uploadFile">
	        	if (filePart == null || filePart.getSize() == 0) {
	                throw new Exception("파일 업로드 안됨 ");
	        	}
	        	oFileName = extractFileName(filePart);
	            if (oFileName.isEmpty()) {
	                throw new Exception("유효하지 않은 파일 이름");
	            }

	            
	            sFileName =  System.currentTimeMillis() +"_"+oFileName ; // 중복없이 파일이름 지정
	            filePart.write(saveDirPath.resolve(sFileName).toString()); // upload 폴더에 해당 파일저장
	            fileSize = filePart.getSize(); // 파일 사이즈 
	            System.out.println("fileType = " + filePart.getContentType());
	            System.out.println("저장된 파일 이름 " + sFileName);
	            
	            System.out.println("File size (bytes): " + fileSize);
	            System.out.println("File size (KB): " + (fileSize / 1024));
	            System.out.println("File size (MB): " + (fileSize / (1024 * 1024)));
	            
	        } catch (Exception e) {
	            System.err.println("File upload failed: " + e.getMessage());

	        }
	        
	        
	        MyFile file = new MyFile();
	        file.setOfileName(oFileName);
	        file.setSfileName(sFileName);
	        file.setFileSize(fileSize);
	        
	
            

	        request.setAttribute("file", file);
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp"); 
			rd.forward(request, response);
			
			
			
	    }

	    private String extractFileName(Part part) {
	        String contentDisposition = part.getHeader("content-disposition");
	        String[] items = contentDisposition.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	                
	                System.out.println("fileName = " + fileName);
	                return Paths.get(fileName).getFileName().toString();
	            }
	        }
	        return "";

	    }

}
