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
		String url = "jdbc:mysql://localhost:3306/" + database + "?charaterEncoding=UTF-8&serverTimezone=UTC";
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
			
			while(rs.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
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
