package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;
import com.kh.mybatis.common.template.Pagination;

/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/search.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 검색영역 클릭 		: mybatis/search.bo?cpage=1&condition=writer&keyword=aaaa
		// 검색 후 페이징바 클릭 	: mybatis/search.bo?cpage=2&condition=writer&keyword=aaaa
		// 페이지를 이동해도 condition과 keyword 유지하는게 관건!
		
		String condition = request.getParameter("condition");	// "writer"|"title"|"content"
		String keyword = request.getParameter("keyword");		// "사용자가 입력한 키워드 값"
		
		HashMap<String, String> map = new HashMap<>();			// HashMap<"키", 벨류> 형태로 저장
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		// 편하게 전역으로
		BoardServiceImpl bService = new BoardServiceImpl();
		int searchCount = bService.selectSearchCount(map);	// 현재 검색결과에 맞는 게시글 총 개수 (알아야 페이징처리하니까)
		int currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, 10, 5);
		//System.out.println(pi);
		ArrayList<Board> list = bService.selectSearchList(map, pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response); // 재요청해도 상관없음
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
