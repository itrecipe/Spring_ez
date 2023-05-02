package org.ezen.ex02.domain;

import java.util.Date;

import lombok.Data;

@Data //생성자는 기본형만 만들어 준다.
public class ReplyVO {
	//tbl_reply 테이블의 컬럼명과 매핑한다.
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
