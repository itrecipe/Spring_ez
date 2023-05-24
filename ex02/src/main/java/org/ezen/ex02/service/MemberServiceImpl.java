package org.ezen.ex02.service;

import org.ezen.ex02.domain.AuthVO;
import org.ezen.ex02.domain.MemberVO;
import org.ezen.ex02.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private BCryptPasswordEncoder passwordEncoder;
	
	@Setter(onMethod_ = @Autowired) 
	//4.3이상 부터는 멤버변수 하나를 사용하는 생성자가 있으면 선언만 해도 자동 주입 
	private MemberMapper mapper;
	
	@Override
	public String joinIdCheck(String userid) {
		MemberVO vo = mapper.read(userid);
		String result = null;
		
		if(vo == null) {
			result = "success";
		}
		else {
			result = "failed";
		}
		
		return result;
	}

	@Override
	@Transactional
	public int joinRegister(MemberVO vo) {
		
		String userid = vo.getUserid();
		
		String userpw = vo.getUserpw(); //평문 비밀번호
		
		String bcriptPw = passwordEncoder.encode(userpw); //비밀번호 암호화
		
		vo.setUserpw(bcriptPw); //vo객체의 userpw를 엄호화된 것으로 변경
		
		AuthVO auth = new AuthVO();
		
		auth.setAuth("ROLE_MEMBER");
		auth.setUserid(userid);			
		
		//tbl_member테이블에 insert
		mapper.memberJoin(vo);
		
		//tbl_auth에 insert
		int result = mapper.memberAuth(auth);
		
		return result;
	}

}
