package com.kh.mybatis.member.model.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService { // interface (class아님)
	// 인터페이스 : 상수필드(public static final) + 추상메소드(public abstract)
	
	// ***** 클래스 설계 *****
	
	/*public abstract*/ int insertMember(Member m);
	
	Member loginMember(Member m);
	
	Member updateMember(Member m);
	
	int deleteMember(String userId);
	
}
