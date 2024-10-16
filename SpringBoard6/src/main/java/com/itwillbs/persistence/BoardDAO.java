package com.itwillbs.persistence;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	public void createBoard(BoardVO vo) throws Exception;
}
