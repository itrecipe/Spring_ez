package org.ezen.ex02.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String userid; //security의 username
	private String userpw;
	private String userName; //사용자명
	private boolean enabled;
	private Date regDate;	
	private Date updateDate;

	private List<AuthVO> authList; //권한은 userid와 1:N이므로 list로 처리한다
}