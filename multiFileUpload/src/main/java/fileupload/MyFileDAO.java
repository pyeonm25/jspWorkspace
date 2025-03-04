package fileupload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;


public class MyFileDAO  { 
	
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public void getConnect() {
		   String URL="jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		   String user="root";
		   String password="1234";
		  try {
			  Class.forName("com.mysql.cj.jdbc.Driver");		  
			  conn=DriverManager.getConnection(URL, user, password);
			  System.out.println("db 연동성공: " + conn );
		   } catch (Exception e) {
			  e.printStackTrace();
		  }		   
	}   

	
	
	// 새로운 게시물을 입력합니다.
    public int insertFile(MyFileDTO dto) {
        int applyResult = 0;
        try {
        	getConnect();
            String query = "INSERT INTO myfile ( "
                + " idx, title, cate, ofile, sfile, postdate) "
                + " VALUES ( "
                + "null, ?, ?, ?, ?,now())";
                        
            ps = conn.prepareStatement(query);
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getCate());
            ps.setString(3, dto.getOfile());
            ps.setString(4, dto.getSfile());
        
            applyResult = ps.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("INSERT 중 예외 발생");
            e.printStackTrace();
        }finally {
        	dbClose();
        }
        return applyResult;
    }

    public List<MyFileDTO> myFileList() {
        List<MyFileDTO> fileList = new Vector<MyFileDTO>();

        String query = "SELECT * FROM myfile ORDER BY idx DESC";
        try {
        	getConnect();
        	 ps = conn.prepareStatement(query);   
            rs =  ps.executeQuery();   

            while (rs.next()) {   
                MyFileDTO dto = new MyFileDTO();
                dto.setIdx(rs.getString(1));
                dto.setTitle(rs.getString(2));
                dto.setCate(rs.getString(3));
                dto.setOfile(rs.getString(4));
                dto.setSfile(rs.getString(5));
                dto.setPostdate(rs.getString(6));
                
                fileList.add(dto);  
            }
        }
        catch (Exception e) {
            System.out.println("SELECT 시 예외 발생");
            e.printStackTrace();
        }finally {
        	dbClose();
        }       
        
        return fileList;  
    }
    
    private void dbClose() {
  	  try { 
  	   if(rs!=null) rs.close();
  	   if(ps!=null) ps.close();
  	   if(conn!=null) conn.close();
  	  }catch(Exception e) {
  		  e.printStackTrace();
  	  }
  } 
}

