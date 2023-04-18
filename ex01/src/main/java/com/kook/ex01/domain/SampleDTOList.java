package com.kook.ex01.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

//bean 클래스
@Data
public class SampleDTOList {
	private List<SampleDTO> list;
	
	//@Data가 만드는 기본형 생성자를 재정의
	public SampleDTOList() {
		list = new ArrayList<>();
	}
}
