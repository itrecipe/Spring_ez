package org.ezen.ex02.mapper;

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
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
		/* mapper는 추상메서드(interface) 객체로서 참조 변수로 인터페이스를 구현을 안한다. 
		   (즉, spring 또는 mybatis가 대신 자동으로 처리한다.)
		 * List<BoardVO)로 반환
		*/
	}
}