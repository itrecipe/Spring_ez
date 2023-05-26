package org.ezen.ex02.security;

import org.ezen.ex02.domain.MemberVO;
import org.ezen.ex02.mapper.MemberMapper;
import org.ezen.ex02.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = { @Autowired })
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//파라메터 username은 form에서는 userid를 말함
		
		log.warn("Load User By UserName : " + username);
		
		MemberVO vo = memberMapper.read(username);

		log.warn("queried by member mapper: " + vo);

		return vo == null ? null : new CustomUser(vo);
		//리턴타입이 인터페이스UserDetails이므로 이를 구현한 클래스 객체여야 하는데
		//CustomUser객체는 UserDetails를 구현한 User클래스를 상속한 객체이다
		
	}

}
