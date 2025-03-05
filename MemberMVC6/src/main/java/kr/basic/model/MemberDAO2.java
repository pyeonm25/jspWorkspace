package kr.basic.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
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
	static {

		try {
			String resource = "org/mybatis/example/mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
