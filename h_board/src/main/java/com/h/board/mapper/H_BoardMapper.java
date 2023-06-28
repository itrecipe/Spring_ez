package com.h.board.mapper;

import java.util.List;

import com.h.board.domain.H_BoardVO;

public interface H_BoardMapper {

	public List<H_BoardVO> getList();
	
	//c
	public void insert(H_BoardVO board);
	
	//bno(글번호) 시퀀스 nextval 값을 미리 알아야 하는 경우 사용
	public Integer insertSelectKey(H_BoardVO board);
	
	//r
	public H_BoardVO read(Long bno);
	
	//u
	public int update(H_BoardVO board);
	
	//d
	public int delete(Long bno);
}