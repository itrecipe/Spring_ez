package com.kook.ex00.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//테스트 클래스 생성
@RunWith(SpringJUnit4ClassRunner.class) //스프링용 테스트 클래스를 이용하여 테스트를 수행한다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") //스프링 응용 프로그램의 설정 파일을 지정한다.
@Log4j //lombok을 이용한 Logger객체 log를 등록한다.
public class SampleTests {

	@Setter(onMethod_ = {@Autowired}) //주입받아서 사용한다(new Restaurant()로 만든적이 없음)
	private Restaurant restaurant;
	
	@Test //Junit에서 테스트 대상임을 표시한다.
	public void testExist() {

		assertNotNull(restaurant); //assertNotNull(빈)은 주인된 빈이 null이 아니어야 테스트가 성공임을 표시한다.
		
		log.info(restaurant);
		log.info("-----------------------");
		log.info(restaurant.getChef());
	}
}