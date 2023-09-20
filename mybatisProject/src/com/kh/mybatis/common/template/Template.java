package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template { //JDBCTemplate

	/*
	 * 기존 JDBC
	 * 
	 * public static Connection getConnection() {
	 * 		// driver.properties 파일 읽어들여서
	 * 		// 해당 DB와 접속된 Connection 객체 생성해서 반환
	 * }
	 * 
	 * public static void close(JDBC용 객체:pstmt,rset, ..) {
	 * 		// 전달받은 JDBC용 객체를 반납시키는 구문
	 * }
	 * 
	 * public static void commit|rollback(Conn) {
	 * 		// 트렌젝션 처리
	 * }
	 * 
	 */
	
	// *** 마이바티스
	public static SqlSession getSqlSession() {
		
		// mybatis-config.xml 파일을 읽어들여서
		// 해당 DB와 접속된 SqlSession 객체 생성해서 반환
		SqlSession sqlSession = null;
		
		// SqlSession 생성하기 위해서 => SqlSessionFactory 필요
		// SqlSessionFactory 생성하기 위해서 => SqlSessionFactoryBuilder 필요
		
		// 얘(resources)를 소스폴더로 만들었기때문에 가능한것!!(일반폴더로 맹글면x)
		// mybatis-config.xml 로 가는 경로를 담아둠
		String resource = "/mybatis-config.xml";
		
		try {
			
			//글쿤(늘 적는 것) : Resource소스폴더에 접근해서 mybatis-config.xml파일 읽어들이는 통로
			InputStream stream = Resources.getResourceAsStream(resource);
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
								// openSession() : 기본값은 false
								// openSession(boolean flag) : 자동커밋 여부 (true면 하겠다, false면 안하겠다) => 개발자가 autoCommit 여부를 직접 설정함
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
		
	}
	
	
	
	
	
	
	
}
