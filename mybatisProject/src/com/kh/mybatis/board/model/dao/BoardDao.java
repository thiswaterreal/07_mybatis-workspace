package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

public class BoardDao {

	public int selectListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
		// result를 바로 한줄로~~
	}
	
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		
		
		//sqlSession.selectList("boardMapper.selectList"); <= 전체리스트 모두 나옴
		
		// 마이바티스에서는 페이징 처리를 위해 RowBounds 라는 클래스 제공
		
		// * offset : 몇 개의 게시글 건너뛰고 조회할건지에 대한 값
		
		/*
		 * ex) boardLimit : 5
		 * 
		 *  						offset(건너뛸숫자)		limit(조회할숫자)
		 *  currentPage : 1	 1~5		  0					  5
		 *  currentPage : 2	 6~10		  5					  5
		 *  currentPage : 3	 11~15		  10				  5
		 *  ...
		 */
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();  // (현재페이지 -1 ) * 조회할숫자
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// 준비완료~ 인자 쿼리돌리러가불~ (형변환해서)
		// ArrayList<Board> list = (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds); //두번째자리:완벽한쿼리라서 보내줄게 없으니까 null
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
		
	}
	
	
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.update("boardMapper.increaseCount", boardNo);
		
	}
	
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
		
	}
	
	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);		
		
	}
	
	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
		
	}
	
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String, String> map, PageInfo pi) {
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();  // (현재페이지 -1 ) * 몇개씩보여줄건지
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
		
	}
	
	
}
