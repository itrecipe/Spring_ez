package org.ezen.ex02.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.ezen.ex02.domain.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	
	private static final long serialVersionUID = 1L;	

	private MemberVO member;
	
	public CustomUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		//권한 authorities는Collection<? extends GrantedAuthority>(집합체)  
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {

		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList()));

		this.member = vo;
	}
}

/*
 Stream<AuthVO> java.util.Collection.stream()  //List를 Stream으로 변환
 <SimpleGrantedAuthority> Stream<SimpleGrantedAuthority> 
 java.util.stream.Stream.map(Function<? super AuthVO, ? extends SimpleGrantedAuthority> mapper)
 //map은 AuthVO객체를 받아서 SimpleGrantedAuthority를 반환하는 Stream
 collect()메서드는 파라메터로 Collector객체를 가져야 하고 Collectors.toList()는 미리 정의된 Collector
 객체를 반환한다
 collect()메서드는 최종 연산으로 Stram의 요소를 수집한다  
*/