package com.kook.ex01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice //AOP의 횡단 관심사를 처리하는 클래스임을 나타낸다.
@Log4j
//예외처리 클래스(공통으로 사용)
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	//예외 처리용 advice를 나타낸다.(advice는 횡단 관심사 처리 코드로 스프링은 메서드)
	//@Controller 형식을 취하고 있다.
	public String except(Exception ex, Model model) {
		
		log.error("Exception ......." + ex.getMessage());
		model.addAttribute("exception", ex); //jsp에 포함되는 속성으로
		log.error(model);
		return "error_page"; //jsp 페이지명
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {

		return "custom404"; //jsp페이지
	}
}