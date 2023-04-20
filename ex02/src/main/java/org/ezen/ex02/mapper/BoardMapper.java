package org.ezen.ex02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ezen.ex02.domain.BoardVO;

public interface BoardMapper {
	
	//mybatis의 @Select 어노테이션으로 처리한다.
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
}
