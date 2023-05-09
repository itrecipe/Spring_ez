package org.ezen.ex04.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

//Advice로 사용되는 메서드를 정의한 Advice클래스
@Aspect //Advice들이 정의된 클래스
@Log4j
@Component
public class LogAdvice {

	//Advice의 실행 시점을 나타내는 @Before 어노테이션은 핵심(타겟)이 실행되기 전 실행하는 Advice
	//괄호안의 PointCut을 나타내는 표현식으로 execution은 메서드를 지정한다.
	@Before("execution(* org.ezen.ex04.service.SampleService*.*(..))") //Advice PointCut 표현식
	
	public void logBefore() {
	
		log.info("===================");
	}
	
	@Before("execution(* org.ezen.ex04.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	//핵심(타켓) 메서드의 사용 파라미터값을 어드바이스의 파라미터로 전달 받아 사용한다.
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	 @AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
	  public void logException(Exception exception) {
	    
	    log.info("Exception....!!!!");
	    log.info("exception: "+ exception);
	  
	  }
	  
	  
	  @Around("execution(* org.zerock.service.SampleService*.*(..))")
	  public Object logTime( ProceedingJoinPoint pjp) {
	    
	    long start = System.currentTimeMillis();
	    
	    log.info("Target: " + pjp.getTarget());
	    log.info("Param: " + Arrays.toString(pjp.getArgs()));
	    
	    
	    //invoke method 
	    Object result = null;
	    
	    try {
	      result = pjp.proceed();
	    } catch (Throwable e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    long end = System.currentTimeMillis();
	    
	    log.info("TIME: "  + (end - start));
	    
	    return result;
	  }
}
