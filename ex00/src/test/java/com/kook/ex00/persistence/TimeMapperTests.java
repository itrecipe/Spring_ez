package com.kook.ex00.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kook.ex00.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private TimeMapper timeMapper; //인터페이스 참조 변수 주입
	
	@Test
	//mybatis @Select 어노테이션 사용
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime()); //getTime추상메서드의 어노테이션 @Select("SELECT sysdate FROM dual")을 실행한다.
	}
	
	@Test
	//mybatis xml사용
	public void testGetTime2() {
		log.info("getTime2");
		log.info(timeMapper.getTime2()); //getTime추상메서드의 어노테이션 @Select("SELECT sysdate FROM dual")을 실행한다.
	}
}
