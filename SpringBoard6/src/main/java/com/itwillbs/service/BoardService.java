package com.itwillbs.service;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	// 글쓰기
	public void regist(BoardVO vo) throws Exception;
}
