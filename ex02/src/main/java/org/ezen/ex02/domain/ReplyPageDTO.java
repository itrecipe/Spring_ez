package org.ezen.ex02.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter

//댓글의 데이터와 페이지 정보를 가진 VO
public class ReplyPageDTO {

	private int replyCnt; //특정 페이지(게시글)의 댓글 수
	private List<ReplyVO> list; //특정 게시글에 대한 리스트 목록
}
