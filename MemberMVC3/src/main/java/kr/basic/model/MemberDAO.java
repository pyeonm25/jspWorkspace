package kr.basic.model;


import java.sql.*;
import java.util.ArrayList;
public class MemberDAO {
	
	private MemberDAO() {}
	
	static private MemberDAO instance = new MemberDAO();
	static public MemberDAO getInstance() {
		return instance;
	}
		
	
private Connection conn;
private PreparedStatement ps;
private ResultSet rs;

// 데이터베이스 연결객체 생성(Connection)
public void getConnect() {
	   //데이터베이스접속URL
	   String URL="jdbc:mysql://localhost:3306/mvc1db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	   String user="root";
	   String password="1234";
	   // MySQL Driver Loading
	  try {
		  //동적로딩(실행시점에서 객체를 생성하는 방법)
		  Class.forName("com.mysql.cj.jdbc.Driver");		  
		  conn=DriverManager.getConnection(URL, user, password);
		  System.out.println("db 연동성공: " + conn );
	   } catch (Exception e) {
		  e.printStackTrace();
	  }		   
}   
// 회원저장 동작
public int memberInsert(Member vo) {
	  //                                                             ?(파라메터) 1 2 3 4 5 6   
	 final String SQL="insert into member(id, pass, name, age, email, phone) values(?,?,?,?,?,?)"; 
	  getConnect();
	  // SQL문장을 전송하는 객체 생성
	  int cnt=-1;
	  try {
		ps=conn.prepareStatement(SQL); //미리 컴파일을 시킨다.(속도가빠르기)
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPass());
		ps.setString(3, vo.getName());
		ps.setInt(4, vo.getAge());
		ps.setString(5, vo.getEmail());
		ps.setString(6, vo.getPhone());
		//  1,0
		cnt=ps.executeUpdate(); //전송(실행)
		
	   }catch (Exception e) {
		e.printStackTrace();
	   }finally {
		   dbClose();
	  }
	  return cnt; // 1 or 0
}
// 회원(VO)전체 리스트(ArrayList) 가져오기
public ArrayList<Member> memberList() {
	 final String SQL="select * from member";
	 getConnect();
	 ArrayList<Member> list=new ArrayList<Member>();
	 try {
	   ps=conn.prepareStatement(SQL);
	   rs=ps.executeQuery(); // rs->커서
	   while(rs.next()) {
		   int num=rs.getInt("num");
		   String id=rs.getString("id");
		   String pass=rs.getString("pass");
		   String name=rs.getString("name");		   
		   int age=rs.getInt("age");
		   String email=rs.getString("email");
		   String phone=rs.getString("phone");
		   // 묶고->담고
		   Member vo=new Member(num, id, pass, name, age, email, phone);
		   list.add(vo);		   
	   }
	 } catch (Exception e) {
    e.printStackTrace();
	 }finally {
		dbClose();
	 }	 
	 return list;
}//memberList


public String checkMemberId(String id) {
	 final String SQL="select pass from member where id=?";
	 getConnect();

	 try {
	   ps=conn.prepareStatement(SQL);
	   ps.setString(1, id);
		rs=ps.executeQuery();
		 if(rs.next()) {
			 return rs.getString("pass");
		 }
	 } catch (Exception e) {
		e.printStackTrace();
	 }finally {
		dbClose();
	}	   
	 return null;
}

public int getMemberNo(String id) {
	 final String SQL="select num from member where id=?";
	 getConnect();

	 try {
	   ps=conn.prepareStatement(SQL);
	   ps.setString(1, id);
		rs=ps.executeQuery();
		 if(rs.next()) {
			 return rs.getInt("num");
		 }
	 } catch (Exception e) {
		e.printStackTrace();
	 }finally {
		dbClose();
	}	   
	 return -1;
}

public boolean isValidId(String id ) {
	String sql = "select id from member where id =?";
	try {
		getConnect();
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		rs = ps.executeQuery();

		if(rs.next()) return false;

	}catch(SQLException e) {
		System.out.println("아이디 비번 찾기 실패");
		e.printStackTrace();
	}finally {
		dbClose();
	}

	return true;

}

public int memberDelete(String id) {
	 final String SQL="delete from member where id=?";
	 getConnect();
	 int cnt=-1;
	 try {
	   ps=conn.prepareStatement(SQL);
	   ps.setString(1,id);
	   cnt=ps.executeUpdate();// 1 or 0	   
	 } catch (Exception e) {
		e.printStackTrace();
	 }finally {
		dbClose();
	}	   
	 return cnt;
}//memberDelete


public Member memberContent(int num) {
	 final String SQL="select * from member where num=?";
	   getConnect();
	   Member vo=null;
	   try {
		 ps=conn.prepareStatement(SQL);
		  ps.setInt(1,num);
		 rs=ps.executeQuery();
		 if(rs.next()) {
			 //회원한명의 정보를 가져와서->묶고(VO)
			   String id=rs.getString("id");
			   String pass=rs.getString("pass");
			   String name=rs.getString("name");		   
			   int age=rs.getInt("age");
			   String email=rs.getString("email");
			   String phone=rs.getString("phone");
			   vo=new Member(num, id, pass, name, age, email, phone);
		 }
	   } catch (Exception e) {
		 e.printStackTrace();
	   }finally {
		  dbClose();
	   }	
	   return vo;
}
public int memberUpdate(Member vo) {
	 final String SQL="update member set age=?, email=?, phone=? where num=?";
	   getConnect();
	   int cnt=-1;
	   try {
	   	ps=conn.prepareStatement(SQL);
	   	ps.setInt(1, vo.getAge());
	   	ps.setString(2, vo.getEmail());
	   	ps.setString(3, vo.getPhone());
	   	ps.setInt(4, vo.getNum());	   	
	   	cnt=ps.executeUpdate();	   	
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		dbClose();
	}	   
	   return cnt;
}
// 데이터베이스 연결 끊기
public void dbClose() {
	  try { 
	   if(rs!=null) rs.close();
	   if(ps!=null) ps.close();
	   if(conn!=null) conn.close();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
}   
}
