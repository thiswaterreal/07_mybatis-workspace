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
								// 안녕하세요 저는 이호우옌입니다
								// 저는 너무 귀여워서 고민이에호우예뉴ㅠ
		
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
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	} //implements MemberService 작성

}
