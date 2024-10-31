package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
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
	public String registPOST(BoardVO vo,RedirectAttributes rttr) throws Exception {
		logger.debug(" /regist.jsp -> registPOST() 호출 ");
		
		// 한글처리 인코딩 => 생략(web.xml 필터)
		// 전달된 정보(파라메터)를 저장
		logger.debug("vo : "+vo);
		
		// 서비스객체 -> DAO객체 호출
		bService.regist(vo);
		logger.debug(" 글쓰기 완료! ");
		
		// 뷰페이지에 전달정보 저장(1회성)
		rttr.addFlashAttribute("result", "INSERTOK");
		
		// 페이지 이동(list)
		
		//return "redirect:/board/listAll";
		return "redirect:/board/listPage";
	}
	
	// 게시판 리스트 - GET
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public void listAllGET(Model model) throws Exception{
		logger.debug(" /listAll -> listAllGET() 호출 ");
		
		// 서비스 -> DAO 메서드 호출 (출력할 정보 가져오기)
		List<BoardVO> boardList = bService.listAll();
		//logger.debug(" "+boardList);
		logger.debug(" 리스트 사이즈 : "+boardList.size());
		// Model 객체를 사용해서 정보를 저장
		//		model.addAttribute(boardList);
		model.addAttribute("boardList", boardList);
		
		// 연결된 뷰페이지에서 출력		
		logger.debug(" /board/listAll.jsp 페이지 이동 ");
	}
	
	// 게시판 리스트(page) - GET
	@RequestMapping(value = "/listPage",method = RequestMethod.GET)
	public String listPageGET(Criteria cri, Model model) throws Exception{
		logger.debug(" /listPage -> listPageGET() 호출 ");
		
//		Criteria cri = new Criteria();
//		cri.setPage(2);
//		cri.setPageSize(20);
		
		// 페이징처리 블럭정보 계산&정보 저장
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		//pageVO.setTotalCount(40); // 임시 - 총개수를 직접 작성
		pageVO.setTotalCount(bService.getTotalCount()); //  총개수를 DB에서 가져오기
		
		// 서비스 -> DAO 메서드 호출 (출력할 정보 가져오기)
		//List<BoardVO> boardList = bService.listAll();
		List<BoardVO> boardList = bService.listPage(cri);
		//logger.debug(" "+boardList);
		logger.debug(" 리스트 사이즈 : "+boardList.size());
		// Model 객체를 사용해서 정보를 저장
		//		model.addAttribute(boardList);
		model.addAttribute("boardList", boardList);
		// 페이징처리 정보 전달
		model.addAttribute("pageVO", pageVO);
		
		
		// 연결된 뷰페이지에서 출력		
		logger.debug(" /board/listAll.jsp 페이지 이동 ");
		
		return "/board/listAll";
	}
	
	
	// 글 본문내용 보기
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public String readGET(@RequestParam("bno") int bno /* BoardVO vo */
			             ,Model model) throws Exception {
		logger.debug(" /board/read -> readGET()  호출");
		logger.debug(" 전달정보 저장(파라메터) ");
		logger.debug(" bno : "+bno);
		
		logger.debug(" 디비에 글 조회수 1증가 ");
		bService.updateViewcnt(bno);
		
		logger.debug(" 디비에 글내용 정보를 가져와서 출력(전달) ");
		BoardVO resultVO = bService.read(bno);
		
		// 전달할 정보를 저장
		model.addAttribute(resultVO); // 전달이름 : boardVO

		// 페이지 이동
		return "/board/read";
	}
	
	// 게시판 글 수정하기-GET
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void  modifyGET(int bno,Model model) throws Exception{
		logger.debug(" /board/modify -> modifyGET() 호출 ");
		
		// 전달받을 정보(bno) 저장
		logger.debug(" bno : "+bno);
		
		// 서비스 -> DAO : 게시판 글정보를 가져오기
		BoardVO resultVO = bService.read(bno);
		// 가져온 글정보를 뷰페이지에 출력	
		// model 객체에 정보를 저장해서 전달
		model.addAttribute("boardVO", resultVO);
		
		//model.addAttribute(bService.read(bno)); //"boardVO"
	}
	
	// 게시판 글 수정하기 - POST
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.debug(" /board/modify -> modifyPOST() 호출 ");
		
		// 전달정보(파라메터) 저장(bno, title,content,writer)
		logger.debug(" vo : "+vo);
		
		// 서비스 -> DAO : 글정보 수정하기 동작
		bService.modify(vo);
		
		// 결과 확인 값을 전달(1회성)
		rttr.addFlashAttribute("result", "modifyOK");
		
		// 다시 글 리스트 페이지로 이동
		//return "redirect:/board/listAll";
		return "redirect:/board/listPage";
	}
	
	// 게시판 글 삭제 - POST
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno) throws Exception{
		logger.debug(" /board/remove -> removePOST()호출 ");
		
		// 전달정보 저장(bno)
		logger.debug(" bno : "+bno);
		// 서비스 -> DAO : 게시판 글 삭제 동작
		int result = bService.remove(bno);
		
		if(result == 0) {
			// 삭제 결과가 없음
			return "redirect:/board/read?bno="+bno;
		}
		
		// result != 0   글 삭제 성공
		
		//return "redirect:/board/listAll";
		return "redirect:/board/listPage";
	}
	
	
	
	
	
	
}// controller
