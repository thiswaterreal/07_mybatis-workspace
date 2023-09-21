package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("bno")); //사용자가 누른 글번호
	
		// 편하게 전역으로
		BoardServiceImpl bService = new BoardServiceImpl(); // 둘다가능(근데 타면, serviceImpl 로 탐) 
		//BoardService bService = new BoardServiceImpl(); 	// 둘다가능(근데 타면, service     로 탐) <=정석
		
		// 1. 조회수 증가시키는 서비스
		int result = bService.increaseCount(boardNo);
		
		if(result > 0) { // **조회수 증가해야 상세글 조회 가능**
			// 2. 해당 게시글 상세 조회 서비스
			Board b = bService.selectBoard(boardNo);
			
			// 3. 해당 게시글 딸린 댓글 리스트 조회 서비스
			ArrayList<Reply> list = bService.selectReplyList(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
			
		}else {
			request.setAttribute("errorMsg", "상세조회 실패!");
			
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
