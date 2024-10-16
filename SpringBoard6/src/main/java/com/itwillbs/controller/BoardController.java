package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*") // /board/~ 로 시작하는 모든주소를 처리하는 컨트롤러
public class BoardController {
	
	// BoardService 객체 주입
	@Inject
	private BoardService bService;
	
	private static final Logger logger 
	     = LoggerFactory.getLogger(BoardController.class);
	
	
	// http://localhost:8088/controller/regist
	// http://localhost:8088/regist
	// http://localhost:8088/board/regist
	// 글쓰기 - 게시판에 작성될 내용을 입력받는 동작 - GET
	@RequestMapping(value = "/regist",method = RequestMethod.GET)
	public String registGET() throws Exception {
		logger.debug(" /regist ->  registGET() 호출 ");
		logger.debug(" /views/board/regist.jsp 페이지 매핑 ");		
		
		return "/board/regist";
	}
	
	// 글쓰기 - 입력받은 정보를 처리하는 동작 - POST
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String registPOST(BoardVO vo) throws Exception {
		logger.debug(" /regist.jsp -> registPOST() 호출 ");
		
		// 한글처리 인코딩 => 생략(web.xml 필터)
		// 전달된 정보(파라메터)를 저장
		logger.debug("vo : "+vo);
		
		// 서비스객체 -> DAO객체 호출
		bService.regist(vo);
		logger.debug(" 글쓰기 완료! ");
		// 페이지 이동(list)
		
		return "redirect:/board/listAll";
	}
	
	// 게시판 리스트
	
	
	
	
	
	
}// controller