package com.kook.ex00.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component //현 클래스를 빈으로 등록해라 라는 의미
@ToString
@Getter
@RequiredArgsConstructor
/* 지정된 멤버변수를 파라미터로 갖는 생성자를 생성한다.
 * 멤버변수 지정은 @NonNull
 */
public class SampleHotel {
	
	@NonNull
	private Chef chef; //이 멤버변수를 이용한 생성자는 자동 주입에 사용한다.
	private String name; 
}