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
	
	//검색을 위해 추가
	private String type; //검색종류(title, writer, content) (실제로는 약자 T, W, C 로 준다.)
	private String keyword;
	
		
	public Criteria() { //Controller list에 cri값이 전달 안될때 초기값
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//문자열 type을 배열로 변환(글자 한글자씩 배열로 변환한다.)
	public String[] getTypeArr() {
		
		return type == null ? new String[] {} : type.split("");
		//type은 문자열로 공백 @("TWC")
	}
}