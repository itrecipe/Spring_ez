package org.ezen.ex02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ezen.ex02.domain.Criteria;
import org.ezen.ex02.domain.ReplyVO;

public interface ReplyMapper {

	//댓글 등록(작성)
	public int insert(ReplyVO vo);
	
	//댓글 조회
	public ReplyVO read(Long rno);
	
	//댓글 삭제
	public int delete(Long rno);
	
	//댓글 수정
	public int update(ReplyVO reply);
	
	//페이지 처리를 한 list
	//@Param은 2개 이상의 파라미터 사용시 사용 cri로 파라미터 Criteria cri 객체를 전달한다.
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	
}
