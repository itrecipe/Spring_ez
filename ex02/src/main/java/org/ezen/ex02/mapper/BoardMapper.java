package org.ezen.ex02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ezen.ex02.domain.BoardVO;

public interface BoardMapper {
	
	//mybatis의 @Select 어노테이션으로 처리한다.
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	/* PK값인 tbl_board테이블의 bno에 들어가는 seq_board(시퀀스)의
	   next.val값을 미리 알 필요가 없는 경우
	*/
	public void insert(BoardVO board);

	/* PK값인 tbl_board테이블의 bno에 들어가는 seq_board(시퀀스)의
	   next.val값을 미리 알 필요가 있는 경우
	 */
	public Integer insertSelectKey(BoardVO board);
	
	//PK값인 bno를 검색 조건으로 하여 일치하는 하나의 레코드를 매핑되는 boardVO 객체로 반환한다.
	public BoardVO read(Long bno);
	
	//PK값인 bno를 검색 조건으로 하여 일치하는 하나의 레코드를 삭제하고 삭제된 레코드 개수를 반환한다.
	public int delete(Long bno);
	
	//클라이언트에서 수정한 내용을 BoardVO로 수집해서 파라미터로 사용한다.
	public int update(BoardVO board);
}