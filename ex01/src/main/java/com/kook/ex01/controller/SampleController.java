package com.kook.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller //자동으로 bean으로 등록, MVC의 controller로 사용
@RequestMapping("/sample") 
//속성으로 지정된 요청 경로를 해당 메서드(바로 밑에 있다.)나 컨트롤러 클래스에서 처리한다.
//브라우저에서 요청시 /sample경로에 있는 요청을 처리한다.
@Log4j
public class SampleController {
	@RequestMapping("")
	public String basic() {
		
		log.info("basic........................"); //로그처리 객체
		return "sample/basic";
	}
	
	//4.3버전 이전 까지는 @RequestMapping을 사용, method는 안적으면 post방식인지 get방식인지 자동으로 판단한다.
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.............");
	}
	
	//4.3버전 이후 부터 method방식에 따른 전용 어노테이션을 지원한다.
	//get전용은 @GetMapping, post전용은 @PostMapping을 사용한다.
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		//리턴 타입이 void이면 요청 경로와 동일한 JSP 페이지로 이동한다.(sample/basicOnlyGet.jsp로 이동)
		log.info("basic get only get...................");
	}
}