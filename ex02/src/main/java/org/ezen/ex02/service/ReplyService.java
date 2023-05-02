package org.ezen.ex02.service;

import java.util.List;

import org.ezen.ex02.domain.Criteria;
import org.ezen.ex02.domain.ReplyVO;

public interface ReplyService {
	
	//ReplyMapper의 insert와 연결
	public int register(ReplyVO vo);

	//ReplyMapper의 read와 연결
	public ReplyVO get(Long rno);
	
	//ReplyMapperr의 update와 연결
	public int modify(ReplyVO vo);
	
	//ReplyMapper의 delete와 연결
	public int remove(Long rno);

	//ReplyMapper의 getListWithPaging(페이징)과 연결
	public List<ReplyVO> getList(Criteria cri, Long bno);

}
