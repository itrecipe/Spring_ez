package com.kook.ex00.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	//@Select어노테이션은 mybatis를 제공하는 어노테이션으로서 select문을 처리한다.
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	//xml로 구현
	public String getTime2();
}
