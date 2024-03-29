package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService { //implements MemberService 작성
	
	// 전역으로 mDao 선언해두고..
	private MemberDao mDao = new MemberDao();
	
	@Override
	public int insertMember(Member m) {
		
		/*
		 * Connection conn = JDBCTemplate.getConnection();
		 * int result = new MemberDao().insertMember(conn, m);
		 * 
		 * if(result > 0) {
		 * 		JDBCTemplate.commit(conn);
		 * }else{
		 * 		JDBCTemplate.rollback(conn);
		 * }
		 * 
		 * JDBCTemplate.close(conn);
		 * 
		 * return result;
		 * 
		 */
		
		SqlSession sqlSession = Template.getSqlSession();
								// 이때 mybatis-config.xml 문서를 읽어들임 ***
								// 이때 등록시킨 mapper.xml 문서들도 다 읽어들여짐 ***
								// 이때 등록시킨 별칭들도 다 읽어들여짐
								// (이때 DB에 접속해서 접근통로 생성)
		
		int result = mDao.insertMember(sqlSession, m); // dao에서 쿼리 돌려야하니까 sqlSession주고, 구멍 채우기 위해 m도 넘겨
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
		
	}

	@Override
	public Member loginMember(Member m) {

		SqlSession sqlSession = Template.getSqlSession();
		Member loginMember = mDao.loginMember(sqlSession, m);
		
		sqlSession.close();
		return loginMember;
	
	}

	@Override
	public Member updateMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = mDao.updateMember(sqlSession, m);
		
		Member updateMem = null;
		
		if(result > 0) {
			sqlSession.commit();
			
			// 갱신된 회원 객체 다시 조회 (id들고)
			updateMem = mDao.selectMember(sqlSession, m.getUserId());
		}
		
		sqlSession.close();
		
		return updateMem;
		
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	} //implements MemberService 작성

}
