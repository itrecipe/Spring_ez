package com.h.board.domain;

import java.util.Date;
import lombok.Data;

@Data
public class H_BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String phone;
	private Date regdate;
	private Date updatedate;
}
