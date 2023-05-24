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

	@Setter(onMethod_= @Autowired)
	private BCryptPasswordEncoder passwordEncoder;
	
	@Setter(onMethod_= @Autowired)
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
	@Transactional //한개라도 실패시 모두 취소하고 다시 작업을 수행
	public int joinRegister(MemberVO vo) {
		String userid = vo.getUserid();
		
		String userpw = vo.getUserpw(); //평문 비번
		
		String bcriptPw = passwordEncoder.encode(userpw); //비번 암호화
		
		vo.setUserpw(bcriptPw); //vo객체의 userpw를 암호화된 것으로 변경한다 
		
		AuthVO auth = new AuthVO();
		
		auth.setAuth("ROLE_MEMBER");
		auth.setUserid(userid);
		
		//tbl_member테이블에 insert
		mapper.memberJoin(vo);
		
		int result = mapper.memberAuth(auth);
		
		return result;
	}
}