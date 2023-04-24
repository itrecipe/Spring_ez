package org.ezen.ex02.controller;

import org.ezen.ex02.domain.BoardVO;
import org.ezen.ex02.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
		//return type이 void이면 mapping의 url과 동일한 이름의 jsp(list.jsp)로 반환한다.
	}
	
	@PostMapping("register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		//1회용 데이터 처리
		
		return "redirect:board/list";
		//sendRedirect()로 브라우저에서 전달하는 경로로 요청한다.
		//return값이 redirect:나 jsp 페이지 이름일시 반환형을 String으로 줘야한다.7
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("get");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify : " + board);
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
		//return값이 redirect:나 jsp 페이지 이름일시 반환형을 String으로 줘야한다.7
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove..." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
}