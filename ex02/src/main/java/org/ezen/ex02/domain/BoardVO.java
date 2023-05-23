package org.ezen.ex02.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

//빈클래스(DTO,VO)이므로 getter,setter를 가짐
@Data
public class BoardVO {

	private Long bno;  //게시글번호, tbl_board의 데이터형이 number(10,0)이므로 Long
	private String title; //게시글 제목, varchar2
	private String content; //게시글 내용
	private String writer; //게시글 작성자
	private Date regDate; //게시글 등록일, Date
	private Date updateDate; //게시글 수정일
	
	private int replyCnt; //댓글 조회수(count)
	
	private List<BoardAttachVO> attachList; //게시판 특정 게시글에 첨부되는 첨부파일 리스트
}
