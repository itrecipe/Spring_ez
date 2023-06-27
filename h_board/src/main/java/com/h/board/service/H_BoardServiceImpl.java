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

	@Setter(onMethod_= @Autowired)
	private H_BoardMapper mapper;
	
	@Override
	public List<H_BoardVO> getList() {
		return null;
	}

	@Override
	public void register(H_BoardVO board) {
		// TODO Auto-generated method stub

	}

	@Override
	public H_BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(H_BoardVO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub
		return false;
	}

}
