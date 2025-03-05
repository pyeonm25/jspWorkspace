package kr.basic.model;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {

	private MemberDAO() {
	}

	static private MemberDAO instance = new MemberDAO();

	static public MemberDAO getInstance() {
		return instance;
	}

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = "kr/basic/mybatis/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);// 읽기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> memberList() {
		List<MemberVO> list = null;
		// 컨넥션 객체 + sql 쿼리문 실행해누느 객체
		try (SqlSession session = sqlSessionFactory.openSession()) {
			list = session.selectList("memberList");
		} catch (Exception e) {
			System.out.println("list 에러");
			e.printStackTrace();
		}
		return list;
	}

	public int memberInsert(MemberVO vo) {

		int cnt = 0;

		try (SqlSession session = sqlSessionFactory.openSession()) {
			cnt = session.insert("memberInsert", vo);
		} catch (Exception e) {
			System.out.println("memberInsert에러");
			e.printStackTrace();
		}
		return cnt;
	}

	public boolean isValidId(String id) {
		int cnt = 0;

		try (SqlSession session = sqlSessionFactory.openSession()) {
			cnt = session.selectOne("validMemberId", id);
		} catch (Exception e) {
			System.out.println("isValidId 에러");
			e.printStackTrace();
		}
		return cnt == 1 ? true : false;
	}

	public String checkLogin(String id, String pw) {
		String name = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPass(pw);

			name = session.selectOne("checkLogin", vo);

		} catch (Exception e) {
			System.out.println("checkLogin 에러");
			e.printStackTrace();
		}
		return name;
	}

	public int getMemberNo(String id) {
		int cnt = 0;

		try (SqlSession session = sqlSessionFactory.openSession()) {
		cnt = session.selectOne("getMemberNo", id);
		} catch (Exception e) {
			System.out.println("getMemberNo 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	public int memberDelete(String id) {
		int cnt = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
		cnt = session.delete("memberDelete", id);
		} catch (Exception e) {
			System.out.println("memberDelete 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	public MemberVO memberContent(int num) {
		MemberVO vo = null;

		try (SqlSession session = sqlSessionFactory.openSession()) {
		vo = session.selectOne("memberContent", num);
		} catch (Exception e) {
			System.out.println("memberContent 에러");
			e.printStackTrace();
		}
		return vo;
	}

	public int memberUpdate(MemberVO vo) {
		int cnt = 0;

		try (SqlSession session = sqlSessionFactory.openSession()) {
		cnt = session.update("memberUpdate", vo);
		} catch (Exception e) {
			System.out.println("memberUpdate 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	public int memberUploadPhoto(int num, String oFileName, String sFileName) {

		int cnt = 0;

		MemberVO vo = new MemberVO();
		vo.setNum(num);
		vo.setoFileName(oFileName);
		vo.setsFileName(sFileName);

		try (SqlSession session = sqlSessionFactory.openSession()) {
		cnt = session.update("memberUploadPhoto", vo);
		} catch (Exception e) {
			System.out.println("memberUploadPhoto 에러");
			e.printStackTrace();
		}

		return cnt;
	}

	/*
	 * 기존메서드 public int memberInsert(MemberVO vo) { return 0; }
	 * 
	 * public List<MemberVO> memberList() { return null; } public boolean
	 * isValidId(String id) { return false; } public boolean checkLogin(String id,
	 * String pw) { return false; } public int getMemberNo(String id) { return 0; }
	 * public int memberDelete(String id) { return 0; } public MemberVO
	 * memberContent(int num) { return null; } public int memberUpdate(MemberVO vo)
	 * { return 0; } public int memberUploadPhoto(int num, String oFileName, String
	 * sFileName) { return 0; }
	 */

}
