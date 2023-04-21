package org.ezen.ex02.mapper;

import org.ezen.ex02.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
//mybatis를 이용하는 테스트
public class BoardMapperTests {
	
	//Setter를 이용한 주입 (BoardMapper 객체를 주입 해달라는 의미)
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	/*
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
		  //mapper는 추상메서드(interface) 객체로서 참조 변수로 인터페이스를 구현을 안한다. 
		  //(즉, spring 또는 mybatis가 대신 자동으로 처리한다.)
		  //List<BoardVO)로 반환
		
	}
	
	@Test
	//BoardMapper 인터페이스의 insert(BoardVO vo)테스트
	public void testInsert() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	//BoardMapper 인터페이스의 InsertSelectKey(BoardVO vo) 테스트
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
	    board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	*/
/*
	@Test
	//BoardMapper 인터페이스의 public BoardVO Read(Long bno) 테스트
	public void testRead() {
		
		// 존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(5L);
		
		log.info(board);
	}
*/	
	/*
	@Test
	//BoardMapper 인터페이스의 public int delete(Long bno) 테스트
	public void testDelete() {
		
		log.info("DELETE COUNT : " + mapper.delete(3L));
	}
	*/

	@Test
	//BoardMapper 인터페이스의 public int update(BoardVO board) 테스트
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		//실행전 존재하는 번호인지 확인할것
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		//count는 update 성공한 개수
		int count = mapper.update(board);
		
		log.info("UPDATE COUNT : " + count);
	}
}