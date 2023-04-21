package org.ezen.ex02.service;

import java.util.List;

import org.ezen.ex02.domain.BoardVO;
import org.ezen.ex02.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j //로그를 보기 위해 사용
@Service
@AllArgsConstructor //모든 멤버 변수를 갖는 생성자
public class BoardServiceImpl implements BoardService {

	//@Setter(onMethod_= {@Autowired})
	//4.3이상 부터는 멤버변수 하나를 사용 생성자가 있으면 선언만 해줘도 자동으로 주입된다.
	private BoardMapper mapper;
	
	@Override
	//Read
	public void register(BoardVO board) {
		log.info("register...." + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get...."+ bno);
		return mapper.read(bno);

	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify....." + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove....." + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	//목록 보기(상세보기) select all
	public List<BoardVO> getList() {
		log.info("getList.........");
		return mapper.getList();
	}
}