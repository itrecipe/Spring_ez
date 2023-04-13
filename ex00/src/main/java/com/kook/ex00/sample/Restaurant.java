package com.kook.ex00.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.Data;

@Component //스프링에서 빈으로 등록하려는 어노테이션이다.
@Data
public class Restaurant {
//Data어노테이션은 equals, canEqual, hashCode toString Chef(), getter 자동 생성

	//@Setter는 아래 멤버변수의 set메서드 생성
	//onMethod_=@Autowired는 set 메서드 위에 =@Autowired 어노테이션을 추가한다.
	//@Autowired는 그 아래에 있는 멤버변수를 빈으로 주입한다.
	//@Setter는 대표적인 빈 주입 방식이다.
@Setter(onMethod_=@Autowired)
private Chef chef;

}
