package com.kook.ex00.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //컴파일된 클래스를 지정해서 사용하겠다는 의미
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") //스프링 응용 프로그램의 설정 파일을 지정한다.
@Log4j //lombok을 이용한 Logger객체 log를 등록한다.
public class HotelTests {
	
	@Setter(onMethod_={@Autowired}) //아래 hotel객체를 주입 받는다.
	private SampleHotel hotel;
	
	@Test
	public void testExist() {
		assertNotNull(hotel);
		log.info("hotel");
		log.info("------------------------");
		log.info(hotel.getChef());
	}
}