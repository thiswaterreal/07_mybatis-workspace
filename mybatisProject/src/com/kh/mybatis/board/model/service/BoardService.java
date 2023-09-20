package com.kh.mybatis.board.model.service;

import java.util.ArrayList;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;

public interface BoardService { // interface임
	
	// ***** 클래스 설계 *****
	
	// 게시판 리스트 조회
	int selectListCount();
	
	ArrayList<Board> selectList(PageInfo pi);

	// 게시판 상세 조회
	int increaseCount(int boardNo);
	
	Board selectBoard(int boardNo);
	
	
}
