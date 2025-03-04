package kr.basic.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.basic.model.MemberDAO;
import kr.basic.model.MemberVO;

//@MultipartConfig(
//	    maxFileSize = 5 * 1024 * 1024, // 5MB
//	    maxRequestSize = 5 * 1024 * 1024, // 5MB
//	    fileSizeThreshold = 1024 * 1024 // 1MB
//	)
public class MemberAddController implements Controller {
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
		// 반드시 파일 업로드 할때는 MultipartRequest 만 사용 가능

		// 데이터 받기----<주의 하기> request 가 아닌 multi 로 값을 받는다.
		// 폼에서 전송한 파라미터의 이름을 인자로 받아서 그 파라미터의 값을 반환한다. // request 객체 사용할 수 없음


		String sFileName = null;
		String oFileName = null;
		Part filePart = request.getPart("uploadFile"); // input에 있는 file 이름 
        if (filePart != null && filePart.getSize() > 0) {
         	oFileName = extractFileName(filePart);
            sFileName =  System.currentTimeMillis() +"_"+oFileName ; // 중복없이 파일이름 지정
            
            filePart.write(saveDirPath.resolve(sFileName).toString()); // Save the file
            String fileType = filePart.getContentType(); // Get file type
            System.out.println("fileType= " + fileType);
        }
        
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        MemberVO vo = new MemberVO(id, pass, name, age, email, phone, oFileName, sFileName);
        System.out.println("savefileName= " + sFileName);
        System.out.println("originFileName= " + oFileName);

		String ctx = request.getContextPath();
		int cnt = MemberDAO.getInstance().memberInsert(vo);
		if (cnt > 0) {
			return "redirect:" + ctx + "/memberList.do";
		} else {
			throw new ServletException("not insert");
		}
	}
	
	// 파일 이름 추출
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        
        System.out.println("items =" + Arrays.toString(items));
        
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                String fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
                return Paths.get(fileName).getFileName().toString(); // Extract just the filename
            }
        }
        return null;
    }

}
