package org.ezen.ex02.controller;

import org.ezen.ex02.domain.MemberVO;
import org.ezen.ex02.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member")
public class MemberController {
	
	@Setter(onMethod_= @Autowired)
	private MemberService mservice;
	
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("error : " + error);
		
		log.info("logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error -- Check your Account");
		}
		if(logout != null) {
			model.addAttribute("logout", "LogOut!");
		}
	}
	
	@GetMapping("/accsssError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		
		log.info("custom logout");
	}
	
	//회원 가입시 ID 체크
	@GetMapping(value="/idChk", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF8")
	@ResponseBody
	public String memberJoinIdChk(String userid) {
		log.info("Join ID 중복체크");
		System.out.println("userid : " + userid);
		String result = mservice.joinIdCheck(userid);
		return result;
	}
	
	//회원 가입창
	@GetMapping("/memberJoin")
	public void memberJoinGet() {
		log.info("Join 입력창");	
	}
	
	//회원가입 처리
	@PostMapping("/memberJoin")
	public String memberJoinPost(MemberVO vo) {
		System.out.println("vo : " + vo.getUserName());
		
		int result = mservice.joinRegister(vo);
		
		if(result > 0) {
			return "member/customLogin";
		} 
		else {
			return "redirect:memberJoin";
		}
	}
}