package org.ezen.ex02.service;

import java.util.List;

import org.ezen.ex02.domain.Criteria;
import org.ezen.ex02.domain.ReplyVO;
import org.ezen.ex02.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements  ReplyService{
	
	 private ReplyMapper mapper; 
	 //멤버변수 하나 이고 생성자 하나의 파라메터 가지므로 자동 주입
	 
	 @Override
	  public int register(ReplyVO vo) {

	    log.info("register......" + vo);

	    return mapper.insert(vo);

	  }

	  @Override
	  public ReplyVO get(Long rno) {

	    log.info("get......" + rno);

	    return mapper.read(rno);

	  }

	  @Override
	  public int modify(ReplyVO vo) {

	    log.info("modify......" + vo);

	    return mapper.update(vo);

	  }

	  @Override
	  public int remove(Long rno) {

	    log.info("remove...." + rno);

	    return mapper.delete(rno);

	  }

	  @Override
	  public List<ReplyVO> getList(Criteria cri, Long bno) {

	    log.info("get Reply List of a Board " + bno);

	    return mapper.getListWithPaging(cri, bno);

	  }
}
