package fileupload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {

	// 파일 업로드
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException {
		// Part 객체를 통해 서버로 전송된 파일명 읽어오기 : 톰켓 10버전만 가능 
		Part part = req.getPart("ofile");

		// Part 객체의 헤더값 중 content-disposition 읽어오기
		String partHeader = part.getHeader("content-disposition");
		// 출력결과 => form-data; name="attachedFile"; filename="파일명.jpg"
		System.out.println("partHeader=" + partHeader);

		// 헤더값에서 파일명 잘라내기
		String[] phArr = partHeader.split("filename=");
		System.out.println(Arrays.toString(phArr));

		String originalFileName = phArr[1].trim().replace("\"", "");
		System.out.println("originalFileName=" + originalFileName);

		// 폴더 없으면 생성하기
		createDirectoryIfNotExists(sDirectory);

		// 파일이름이 있다면

		if (!originalFileName.isEmpty()) {
			Path targetPath = Paths.get(sDirectory, originalFileName);
			Files.copy(part.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("targetPath =" + targetPath.toString());
		}

		// 원본 파일명 반환
		return originalFileName;
	}

	// 폴더 없으면 폴더 생성
	private static void createDirectoryIfNotExists(String directory) {

		Path dirPath = Paths.get(directory);
		if (Files.notExists(dirPath)) {
			try {
				Files.createDirectories(dirPath);
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	// 파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
		// 원본파일의 확장자 잘라내기
		String ext = fileName.substring(fileName.lastIndexOf("."));
		// 날짜 및 시간을 통해 파일명 생성
		//String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String now = DateTimeFormatter.ofPattern("yyyyMMdd_HmsS").format(LocalDateTime.now());
		// "날짜_시간.확장자" 형태의 새로운 파일명 생성
		String newFileName = now + ext;

		Path sourcePath = Paths.get(sDirectory, fileName);
		Path targetPath = Paths.get(sDirectory, newFileName);

		try {
			Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 변경된 파일명 반환
		return newFileName;
	}

	// multiple 속성 추가로 2개 이상의 파일 업로드
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory)
			throws ServletException, IOException {
		// 파일명 저장을 위한 컬렉션 생성
		ArrayList<String> listFileName = new ArrayList<>();

		// Part 객체를 통해 서버로 전송된 파일명 읽어오기
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {

			// 파일이 아니라면 업로드의 대상이 아니므로 무시
			if (!part.getName().equals("ofile"))
				continue;

			// Part 객체의 헤더값 중 content-disposition 읽어오기
			String partHeader = part.getHeader("content-disposition");
			// 출력결과 => form-data; name="attachedFile"; filename="파일명.jpg"
			System.out.println("partHeader=" + partHeader);

			// 헤더값에서 파일명 잘라내기
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");

			// 전송된 파일이름이 존재하면
			if (!originalFileName.isEmpty()) {
				Path targetPath = Paths.get(sDirectory, originalFileName);
				// 파일 생성
				Files.copy(part.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			}

			// 컬렉션에 추가
			listFileName.add(originalFileName);
		}

		// 원본 파일명 반환
		return listFileName;
	}

	// 파일 다운로드
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName,
			String ofileName) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		try {
			// 파일을 찾아 입력 스트림 생성
			Path filePath = Paths.get(sDirectory, sfileName);

			InputStream iStream = Files.newInputStream(filePath);

			// 한글 파일명 깨짐 방지
			String client = req.getHeader("User-Agent");
			if (client.indexOf("WOW64") == -1) {
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}

			// 파일 다운로드용 응답 헤더 설정
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + ofileName + "\"");
			resp.setHeader("Content-Length", "" + String.valueOf(Files.size(filePath)));

			// out.clear(); // 출력 스트림 초기화

			// response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream oStream = resp.getOutputStream();

			// 출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int) Files.size(filePath)];
			int readBuffer = 0;
			while ((readBuffer = iStream.read(b)) > 0) {
				oStream.write(b, 0, readBuffer);
			}

			// 입/출력 스트림 닫음
			iStream.close();
			oStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
	}

	// 파일 삭제
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		Path filePath = Paths.get(sDirectory, filename);
		try {
			Files.deleteIfExists(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
