package com.kook.ex01.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kook.ex01.domain.SampleDTO;
import com.kook.ex01.domain.SampleDTOList;
import com.kook.ex01.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller // 자동으로 bean으로 등록, MVC의 controller로 사용
@RequestMapping("/sample")
//속성으로 지정된 요청 경로를 해당 메서드(바로 밑에 있다.)나 컨트롤러 클래스에서 처리한다.
//브라우저에서 요청시 /sample경로에 있는 요청을 처리한다.
@Log4j
public class SampleController {
	// initBinder보다 편리한 방식이 있으므로 테스트 이후 주석 처리를 한다.

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) { SimpleDateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
	 * false)); }
	 */

	@RequestMapping("")
	public String basic() {

		log.info("basic........................"); // 로그처리 객체
		return "sample/basic";
	}

	// 4.3버전 이전 까지는 @RequestMapping을 사용, method는 안적으면 post방식인지 get방식인지 자동으로 판단한다.
	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("basic get.............");
	}

	// 4.3버전 이후 부터 method방식에 따른 전용 어노테이션을 지원한다.
	// get전용은 @GetMapping, post전용은 @PostMapping을 사용한다.
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		// 리턴 타입이 void이면 요청 경로와 동일한 JSP 페이지로 이동한다.(sample/basicOnlyGet.jsp로 이동)
		log.info("basic get only get...................");
	}

	// bean클래스로 된 객체를 직접 파라미터로 받기
	// bean의 setter을 호출해서 해당 멤버변수를 설정한다.
	// 클라이언트로부터 오는 파라미터 변수명과 bean class의 멤버변수가 일치해야한다.(작성시 주의사항)
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {

		log.info("----" + dto);
		// return type이 String이면 return값은 ex01.jsp가 된다.
		return "sample/ex01";
	}

	// @RequestParam("클라이언트에서 보내는 파라미터명")은 두개의 파라미터명을 다르게 사용시 활용한다.
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {

		log.info("name" + name);
		log.info("age" + age);
		return "sample/ex02";
	}

	// 클라이언트에서 동일한 파라미터명으로 복수개를 보낼시에는 배열이나 List로 처리 해야한다.
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids : ---- " + ids);
		return "sample/ex02List";
	}

	// 복수개의 파라미터 값을 배열로 처리
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {

		log.info("array ids : " + Arrays.toString(ids));
		return "sample/ex02Array";
	}

	// 배열이나 리스트의 요소가 문자열이 아닌 객체형일시 처리도 가능
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {

		log.info("list dtos : " + list);
		return "sample/ex02Bean";
	}

	// client에서 오는 문자열을 Date형으로 자동 형변환 하기
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {

		log.info("todo : " + todo);
		return "ex02";
	}
	// 테스트 URL :
	// http://localhost:8181/ex01/sample/ex03?title=kook&dueDate=2023-04-18

	// bean클래스 규격의 객체는 이동할 jsp에 자동으로 포함되나 기본형은 되지 않는다.
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page) {

		log.info("dto : " + dto);
		log.info("page : " + page);

		return "sample/ex04";
	}
	// 테스트 URL : http://localhost:8181/ex01/sample/ex04?name=kook&age=30&page=3

	// 기본형을 이동할 JSP 페이지에 넣기 위해서는 @ModelAttribute("전달 속성명")
	@GetMapping("/ex04_01")
	public String ex04_01(SampleDTO dto, @ModelAttribute("page") int page) {

		log.info("dto : " + dto);
		log.info("page : " + page);

		return "sample/ex04";
	}
	// 테스트 URL : http://localhost:8181/ex01/sample/ex04_01?name=kook&age=30&page=3

	// RedirectAttribute클래스를 이용하여 sendRedirect형식으로 페이지 이동 해보기
	// HttpSession객체에 일회용 속성을 보내고 폐기 한다.
	// addFlashAttribute("속성명", 값);을 이용하여 저장한다.
	@GetMapping("/redirect01")
	public String redirect01(RedirectAttributes rttr) {

		rttr.addFlashAttribute("name", "kook");
		rttr.addFlashAttribute("age", 10);

		return "redirect:/"; // redirect: 키워드를 사용한다. /는 home.jsp로 이동
	}
	
	//return 타입이 void이면 요청경로명과 동일한 이름의 jsp로 이동한다.
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05..........");
		//실제로는 sample/ex05.jsp
	}
	
	//Model 파라미터 사용 예시
	//Model객체는 스프링에서 자동으로 생성
	@GetMapping("/model01")
	public String model01(Model model) {
		model.addAttribute("name", "kook");
		//model객체는 return시 이동할 페이지에 포함된다.
		return "sample/model01";
	}
	//테스트 URL : http://localhost:8181/ex01/sample/model01
}