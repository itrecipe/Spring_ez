package org.ezen.ex02.domain;

import java.util.Date;

import lombok.Data;

//bean클래스(DTO, VO)이므로 getter, setter을 가진다.
@Data
public class BoardVO {

	private Long bno; //tbl_board에 데이터형이 number(10,0)이므로 Long(객체형)형이 커버해준다.
	private String title; //varchar2형
	private String content;
	private String writer;
	private Date regdate; //Date형
	private Date updateDate;
}
