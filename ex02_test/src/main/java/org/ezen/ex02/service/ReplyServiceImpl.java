package org.ezen.ex02.service;

import java.util.List;

import org.ezen.ex02.domain.Criteria;
import org.ezen.ex02.domain.ReplyPageDTO;
import org.ezen.ex02.domain.ReplyVO;
import org.ezen.ex02.mapper.BoardMapper;
import org.ezen.ex02.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
//@AllArgsConstructor(멤버변수 2개로 되어 자동 주입 안되므로 제거)
public class ReplyServiceImpl implements ReplyService {

	// private ReplyMapper mapper;
	// 멤버변수 하나 이고 생성자 하나의 파라메터 가지므로 자동 주입

	//두개의 멤버변수를 자동 주입
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Transactional //2개의  sql문을 트랜젝션 처리
	@Override
	public int register(ReplyVO vo) {

		log.info("register......" + vo);
		
		//등록이므로 1을 amount로 보냄
		boardMapper.updateReplyCnt(vo.getBno(), 1);

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
	
	@Transactional
	@Override
	public int remove(Long rno) {

		log.info("remove...." + rno);
		
		ReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);

		return mapper.delete(rno);

	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {

		log.info("get Reply List of a Board " + bno);

		return mapper.getListWithPaging(cri, bno);

	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {

		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
	}
}
