package com.kh.mybatis.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;
import static com.kh.mybatis.common.template.Template.*;

public class BoardServiceImpl implements BoardService { //implements BoardService 작성

	BoardDao bDao = new BoardDao();
	
	// ************* 게시글 리스트 **************
	
	@Override // 페이징바 listCount 값 구하기 위해
	public int selectListCount() {
		
		SqlSession sqlSession = /*Template.*/getSqlSession();
		int listCount = bDao.selectListCount(sqlSession);
		
		sqlSession.close();
		return listCount;
		
	}

	@Override // 한 페이지당 조회될 게시글리스트
	public ArrayList<Board> selectList(PageInfo pi) {

		SqlSession sqlSession = getSqlSession();
		ArrayList<Board> list = bDao.selectList(sqlSession, pi);
		
		sqlSession.close();
		return list;
	
	}

	// ************* 상세페이지 **************
	
	@Override
	public int increaseCount(int boardNo) {
		
		SqlSession sqlSession = getSqlSession();
		int result = bDao.increaseCount(sqlSession, boardNo);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
		
	}

	@Override
	public Board selectBoard(int boardNo) {
	
		SqlSession sqlSession = getSqlSession();
		Board b = bDao.selectBoard(sqlSession, boardNo);
		
		sqlSession.close();
		return b;
		
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {

		SqlSession sqlSession = getSqlSession();
		ArrayList<Reply> list = bDao.selectReplyList(sqlSession, boardNo);
		
		sqlSession.close();
		return list;
		
	}

	// ************* 게시글 검색 **************
	
	@Override // 현재 검색결과에 맞는 게시글 총 개수 (알아야 페이징처리하니까)
	public int selectSearchCount(HashMap<String, String> map) {

		SqlSession sqlSession = getSqlSession();
		int searchCount = bDao.selectSearchCount(sqlSession, map);
		
		sqlSession.close();
		return searchCount;
	
	}


	@Override
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi) {

		SqlSession sqlSession = getSqlSession();
		ArrayList<Board> list = bDao.selectSearchList(sqlSession, map, pi);
	
		sqlSession.close();
		return list;
	}
	


}
