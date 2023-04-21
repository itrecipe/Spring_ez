package org.ezen.ex02.service;

import java.util.List;

import org.ezen.ex02.domain.BoardVO;

//비즈니스 계층의 인터페이스 (서비스용)
public interface BoardService {

	public void register(BoardVO board); //Create
	
	public BoardVO get(Long bno); //Read
	
	public boolean modify(BoardVO board); //Update

	public boolean remove(Long bno); //Delete
	
	public List<BoardVO> getList(); //목록 Select
}