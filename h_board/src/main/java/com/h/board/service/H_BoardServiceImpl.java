package com.h.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h.board.domain.H_BoardVO;
import com.h.board.mapper.H_BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor //모든 멤버 변수를 갖는 생성자(생성자가 1개만 있을시 자동 주입이 된다)

public class H_BoardServiceImpl implements H_BoardService {

	private H_BoardMapper mapper;
	
	//목록보기
	@Override
	public List<H_BoardVO> getList() {
		log.info("getList 호출");
		return mapper.getList();
	}

	@Override
	public void register(H_BoardVO board) {
		log.info("register 호출!" + board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public H_BoardVO get(Long bno) {
		log.info("get 호출!" + bno);
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(H_BoardVO board) {

		log.info("modify 호출!" + board);
		
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove 호출!" + bno);

		return mapper.delete(bno) == 1;
	}

}
