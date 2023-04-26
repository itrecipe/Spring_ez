package org.ezen.ex02.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//페이지 관련 DTO 클래스
@ToString
@Setter
@Getter
public class Criteria {

	private int pageNum;
	private int amount;
	
	public Criteria() { //Controller list에 cri값이 전달 안될때 초기값
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
