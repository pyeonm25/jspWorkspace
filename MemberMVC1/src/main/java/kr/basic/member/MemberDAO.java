package kr.basic.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 싱글톤 : 객체를 단 한개만 만들어주는 것! 

public class MemberDAO {
	private MemberDAO() {
	}

	static private MemberDAO instance;

	static public MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}

	// db 연동에 필요한 객체
	private Connection conn; // db 객체 => mysql
	private PreparedStatement ps; // db 쿼리 명령문 객체
	private ResultSet rs; // 결과 테이블 객체 => select 할때만 필요한 객체

	private void getConnection() {
		String database = "mvc1db";
		
		String url = "jdbc:mysql://localhost:3306/" + database + 
				"?charaterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
		String user = "root";
		String password = "1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password); // mysql db 객체 생성
			System.out.println(conn);
		} catch (Exception e) {
			System.out.println("db연동실패");
			e.printStackTrace();
		}

	}
	
	public ArrayList<Member> getMemberList(){
		ArrayList<Member> list = new ArrayList<Member>();
		String sql ="select * from member";
		try {
			getConnection(); // db 객체 생성 
			ps= conn.prepareStatement(sql); // 쿼리 명령문 넣어주기 
			rs = ps.executeQuery(); // select 문은 resultSet 객체를 리턴한다 
			// rs.next() => 다음줄(row)이 없다면 false 있으면 true 
			while(rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String emil = rs.getString("email");
				String phone = rs.getString("phone");
				
				Member member = new Member(num, id, pass, name, age, emil, phone);
				list.add(member);
				System.out.println(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return list;
	}
	
	public boolean isLoginPass(String id, String pass) {
		String sql="select num from member where id=? and pass=?"; // ? 시작 1
		int num =0;
		try {
			getConnection(); // db 객체 생성 
			ps = conn.prepareStatement(sql); // 쿼리객체 
			// 미완성 쿼리 완성 
			ps.setString(1, id);
			ps.setString(2, pass);
			
			// 쿼리문 
			rs = ps.executeQuery(); // 
			if(rs.next()) { // rs.next() 실행 쿼리 row 한줄 읽어옴
				num = rs.getInt("num");
			}
			
			
		} catch (SQLException e) {
			System.out.println(" id pass 조회 실패");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return num!=0;
	}
	
// select => 테이블 수정 x , 결과테이블 나온다 
// insert, update , delete => 테이블 수정 명령문 
	public int insertAMember(String id,String pw,String name,int age,String email,String phone) {
		String sql="insert into member(id,pass,name,age,email,phone) values(?,?,?,?,?,?)";
		int rowCnt =0;
		try {
			getConnection(); // db 객체 생성 
			ps = conn.prepareStatement(sql); // 쿼리객체 
			
			// ? 있으면 항상 채워준다
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, age);
			ps.setString(5, email);
			ps.setString(6, phone);
			
			rowCnt = ps.executeUpdate(); // insert , update, delete => 영향을 끼친 row 갯수 int 반환
			
		} catch (SQLException e) {
			System.out.println(" 회원 추가 실패");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return rowCnt;
	}
	
	
	private void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
