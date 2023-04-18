package com.kook.ex01.domain;

import lombok.Data;

//bean 클래스
@Data
/*
 * 자동으로 getter, setter, equals, canEqual, hashCode, SampleDTO(기본 생성자) 를 생성해준다.
 */
public class SampleDTO {

	private String name;
	private int age;
}
