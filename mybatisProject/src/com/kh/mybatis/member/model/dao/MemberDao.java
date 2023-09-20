package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	public int insertMember(SqlSession sqlSession, Member m) {
		
		/*
		 * int result = 0;
		 * PreparedStatement pstmt = null;
		 * String sql = prop.getProperty("insertMember");
		 * 
		 * try{
		 * 		pstmt = conn.prepareStatement(sql);
		 * 		pstmt.setString(1, m.getUserId());
		 * 		pstmt.setString(2, m.getUserPwd());
		 * 		...
		 * 
		 * 		result = pstmt.executeUpdate();
		 * 
		 * } catch(xxx){
		 * 
		 * } finally{
		 * 		close(pstmt);
		 * }
		 * 
		 */
		
		/*
		 * sqlSession에서 제공하는 메소드를 통해서 sql문 찾아서 실행하고 바로 결과 받음
		 * 
		 * 결과 = sqlSession.sql문종류에맞는메소드("매퍼의별칭.해당sql문의고유한id", [그 sql문을 완성시킬 객체])
		 */
		
								// 이전에 mybatis-config.xml 에서 쫙 읽어들였어서 등록된 mapper중에 insertMember찾음, 구멍채워야하니까 m도 보내
		int result = sqlSession.insert("memberMapper.insertMember", m);
		return result;
		//(한줄로) return sqlSession.insert("memberMapper.insertMember", m);
		
	}
	
	
	public Member loginMember(SqlSession sqlSession, Member m) {
		
		// selectOne 메소드 : 조회결과가 만약 없다면 null 반환
		Member loginMember = sqlSession.selectOne("memberMapper.loginMember", m);
		return loginMember;
		//(한줄로) return sqlSession.selectOne("memberMapper.loginMember", m);
		
	}
	
	
}
