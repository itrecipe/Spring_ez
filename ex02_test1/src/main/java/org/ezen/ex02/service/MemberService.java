package org.ezen.ex02.service;

import org.ezen.ex02.domain.MemberVO;

public interface MemberService {

	public String joinIdCheck(String userid); //ID 체크
	public int joinRegister(MemberVO vo); //회원 가입

}
