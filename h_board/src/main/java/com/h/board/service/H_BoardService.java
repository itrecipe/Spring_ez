package com.h.board.service;

import java.util.List;

import com.h.board.domain.H_BoardVO;

//비즈니스 계층 인터페이스(느슨한 연결, 유지보수 용이, 확장성이 좋음)
public interface H_BoardService {

	//게시판 리스트 가져오기
	public List<H_BoardVO> getList();
	
	//c
	public void register(H_BoardVO board);
	
	//r
	public H_BoardVO get(Long bno);
	
	//u
	public boolean modify(H_BoardVO board);
	
	//d
	public boolean remove(Long bno);
	
}