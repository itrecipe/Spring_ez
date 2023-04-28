package org.ezen.ex02.domain;

import org.springframework.web.util.UriComponentsBuilder;

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
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//문자열 type을 배열로 변환(글자 한글자씩 배열로 변환한다.)
	//BoardMapper.xml에서 typeArr이름의 파라미터로 사용된다.
	//myBatis는 엄격한 bean 규칙을 따르지 않고, getter, setter를 활용
	public String[] getTypeArr() {
		
		return type == null ? new String[] {} : type.split("");
		//type은 문자열로 공백없이 온다. @("TWC")가 오는데 split을 하면 배열 {T, W, C}로 온다.
	}
	
	//Criteria멤버변수 4개를 하나의 쿼리문자열 형태로 만들어 준다.(? 파라미터=이름&파라미터이름=값)
	public String getListLink() {
		//파라미터 전송에 사용되는 문자열을 생성한다.
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("").queryParam("pageNum", this.pageNum)
			 .queryParam("amount", this.getAmount()).queryParam("type", this.getType())
			 .queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}