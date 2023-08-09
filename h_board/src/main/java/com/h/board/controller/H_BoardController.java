package com.h.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.h.board.domain.H_BoardVO;
import com.h.board.service.H_BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller //컨트롤러 클래스, 스프링 빈으로 등록
@Log4j
@RequestMapping("/board")
@AllArgsConstructor //롬복의 모든 멤버변수를 파라미터로 갖는 생성자를 생성한다.
//멤버변수가 하나일시에 파라미터가 하나인 생성자를 만들어 준다.

public class H_BoardController {
	
	private H_BoardService service; //서비스 메서드(객체) 주입
	
	//리스트 출력
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list 호출!" + model);
		model.addAttribute("list", service.getList());
	}
	
	//게시글 조회, 수정
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("get & modify 호출!");
		model.addAttribute("h_board", service.get(bno));
	}
	
	//게시글 등록 폼
	@GetMapping("/register")
	public void registerForm() {
		log.info("registerForm 호출!");
	}
	
	//게시글 등록 처리
	@PostMapping("/register")
	public String register(H_BoardVO board, RedirectAttributes rttr) {
		log.info("register 호출!" + board);

		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:list";
	}
	
	//게시글 수정
	@PostMapping("/modify")
	public String modify(H_BoardVO board, RedirectAttributes rttr) {
		log.info("modify 호출! " + board);
			if(service.modify(board)) {
				rttr.addFlashAttribute("result", "success");
			}
			return "redirect:list";
	}
	
	//게시글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove 호출!" + bno);
			if(service.remove(bno)) {
				rttr.addFlashAttribute("result", "success");
			}
			return "redirect:list";
	}
}