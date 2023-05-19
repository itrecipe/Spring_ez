package org.ezen.ex02.mapper;

import java.util.List;

import org.ezen.ex02.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);
	
	public void deleteAll(Long bno);
	
	public List<BoardAttachVO> getOldFiles(); //어제의 첨부 파일 데이터를 반환한다.
}
