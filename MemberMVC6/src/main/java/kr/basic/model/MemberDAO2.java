package kr.basic.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO2 {
	
	private MemberDAO2() {}
	static private MemberDAO2 instance;
	static public MemberDAO2 getInstance() { 
		if(instance == null) {
			instance = new MemberDAO2();
		}
		return instance;
	}
	
	// static {} => 서버 실행될때 딱 한번 실행한다 
	
	
	private static SqlSessionFactory sqlSessionFactory ;
	
	static {

		try {
			String resource = "kr/basic/mybatis/config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public ArrayList<MemberVO> memberList() {

		ArrayList<MemberVO> list = null;
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			  
			list = (ArrayList)session.selectList("memberList");
			
		}catch(Exception e) {
			System.out.println(" memberList() 에러");
			e.printStackTrace();
		}
		return list;
	}


}
