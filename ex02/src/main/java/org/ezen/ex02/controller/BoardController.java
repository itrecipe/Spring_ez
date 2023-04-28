package org.ezen.ex02.controller;

import org.ezen.ex02.domain.BoardVO;
import org.ezen.ex02.domain.Criteria;
import org.ezen.ex02.domain.PageDTO;
import org.ezen.ex02.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller //컨트롤러 클래스로 스프링 bean으로 등록한다. 
//컨트롤러는 꼭 컨터롤러 어노테이션을 추가하여 알릴것
@Log4j //로그를 찍기 위한 어노테이션
@RequestMapping("/board")
//요청 경로를 처리하기 위한 어노테이션
//요청을 해당 메서드로 연결하나, 클래스에 지정시 공통 경로가 된다.
@AllArgsConstructor
//Lombok의 모든 멤버변수를 파라미터로 갖는 생성자를 생성 해준다.
//멤버 변수가 하나이므로 파라미터 하나인 생성자
public class BoardController {
	private BoardService service;
	//서버스의 메서드를 사용하기 위해 주입받기 위한 멤버변수
	//멤버변수가 하나인 생성자가 존재시 자동 주입되어 @Autowired생략(스프링 4.3부터)
	
	/*
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
		//return type이 void이면 mapping의 url과 동일한 이름의 jsp(list.jsp)로 반환한다.
	}
	*/
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		//cri를 자동 수집 하므로 cri가 없을시 기본형 생성자가 설정하는 값으로 수집한다.
		log.info("list : " + cri);
		model.addAttribute("list", service.getList(cri));
		
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		//123은 총 게시글 수 total을 의미하며 임시로 고정해둔 값이다.
		
		//실제 게시글 메소드
		int total = service.getTotal(cri);
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("----registerForm");
		//return은 register.jsp
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		//1회용 데이터 처리
		
		return "redirect:list";
		//sendRedirect()로 브라우저에서 전달하는 경로로 요청한다.
		//return값이 redirect:나 jsp 페이지 이름일시 반환형을 String으로 줘야한다.
	}
	/*
	 * 페이지를 고려 하지 않고 만든 get
	 
	//상세보기(조회)는 get으로 처리한다.
	@GetMapping({"/get", "/modify"})
	//요청의 파라미터도 동일하고, 리턴값도 동일하며, Model에 실어주는 데이터도 동일할때 배열 형태로 Mapping 해준다.
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("get");
		model.addAttribute("board", service.get(bno));
	}
	*/
	
	//리스트창에서 조회창으로 이동시 페이지 번호를 유지하기 위해 cri 객체를 사용하고 강제로 Model에 포함 시키는 과정이다.
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		//bean 규칙의 DTO객체는 자동으로 model에 포함된다.
		//@ModelAttribute("cri")는 model에 cri 속성으로 cri 객체를 강제로 저장한다.
		//기본형을 Model에 포함 시킬때 사용한다.
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	
	/*
	 * 페이지를 고려 하지 않은 경우 (수정기능)
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify : " + board);
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:list";
		//return값이 redirect:나 jsp 페이지 이름일시 반환형을 String으로 줘야한다.
	}
	*/
	
	//페이지 정보를 고려하여 만든 (수정기능)
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		
		log.info("modify : " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
	
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		//list로 검색조건을 넘겨준다.
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:list";
	}
	
	/* 페이지 정보를 고려하지 않은 경우 (삭제기능)
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove..." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:list";
	}
	*/
	
	//페이지 정보를 고려하고 만든 경우 (삭제기능)
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove... : " + bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		/*
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		//list로 검색조건을 넘겨준다.
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		*/
		
		System.out.println("쿼리 스트링 : " + cri.getListLink());
		return "redirect:list" + cri.getListLink(); 
		//query문자열 이므로 ?을 붙일 필요가 없고, 한줄로 보내자는 의미인다.
		//위 코드처럼 처리 해주면 한글깨짐 현상을 염려할 필요가 없다.
	}
}