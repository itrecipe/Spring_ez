package org.ezen.ex02.service;

import java.util.List;

import org.ezen.ex02.domain.BoardAttachVO;
import org.ezen.ex02.domain.BoardVO;
import org.ezen.ex02.domain.Criteria;

//비지니스 계층의 인터페이스
public interface BoardService {
	
	//기본 CRUD
	public void register(BoardVO board); //Create

	public BoardVO get(Long bno); //Read

	public boolean modify(BoardVO board); //Update

	public boolean remove(Long bno); //delete

	//R- 리스트 출력(목록 select - 페이징 처리 전)
	//public List<BoardVO> getList(); //목록 select

	//페이징을 위한 처리
	//R - 리스트 출력(목록 select - 페이징 처리 후)
	public List<BoardVO> getList(Criteria cri);
	
	//게시글 총 합계, 페이징을 위한 처리
	public int getTotal(Criteria cri);
	
	//파일 첨부를 위한 처리
	public List<BoardAttachVO> getAttachList(Long bno);

}
