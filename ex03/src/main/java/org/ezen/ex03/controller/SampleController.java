package org.ezen.ex03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.ezen.ex03.domain.SampleVO;
import org.ezen.ex03.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController //REST방식의 컨트롤러
@RequestMapping("/sample") //공통경로
@Log4j //스프링의 로그 기록(정보, 경고, 에러 (기록))
public class SampleController {
	
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	//produces 속성은 해당 메서드가 생선하며 반환하는 MIME 타입
	//순수 문자열을 반환시 리턴타입은 String이다.
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕?";
	}
	
	@GetMapping(value = "/getSample", 
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	//produces속성은 생략해도 됨
	//SampleVO객체를 반환시에 json이나 xml로 번환(기본은 xml)
	public SampleVO getSample() {

		return new SampleVO(112, "스타", "로드");

	}
	
	@GetMapping(value = "/getSample2",produces = {MediaType.APPLICATION_JSON_VALUE})
	//5.3.26버젼에서는 .json은 동작 안하므로 produces로 조정
	public SampleVO getSample2() {
		return new SampleVO(113, "로켓", "라쿤");
	}	
	
	@GetMapping(value = "/getList")
	//JSON 배열 형태로 반환
	public List<SampleVO> getList() {
		
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + " First", i + " Last"))
				.collect(Collectors.toList());
		//1부터 9까지 반복하여 i값을 가진 SampleVO객체를 만들어 list의 컬렉션으로 반환한다.
	}
	
	@GetMapping(value = "/getMap")
	//Map으로 반환시 JSON 객체형으로 반환
	public Map<String, SampleVO> getMap() {
		
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		map.put("Last", new SampleVO(222, "아이언맨", "로버트 다우니 주니어"));
		
		return map;
	}
	
	@GetMapping(value = "/check", params = {"height", "weight"})
	//params 속성은 클라이언트에서 전달되는 파라미터 속성명이며 메서드의 파라미터로 매핑한다.
	//클라이언트에서 오는 값은 모두 문자열이나 메서드의 기본형으로 자동 형변환 한다.
	public ResponseEntity <SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			//status 메서드는 상태를 기록하고 body는 response의 body에 값을 기록 한다.
			//HttpStatus.BAD_GATEWAY는 502에러, 잘못 전달 되었다는 의미
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
			//HttpStatus.OK는 200
		}
		
		return result;
	}
	
	@GetMapping("/product/{cat}/{pid}")
	//요청 경로에 포함된 값을 처리한다.
	//@PathVariable(경로의 포함된 값)로 메서드의 파라미터와 매핑한다.
	//JSON으로 요청시 Test URL :  ex) http://localhost:8181/ex03/sample/product/kook/20.json
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
	
		return new String[] { "category : " + cat, "productid : " + pid };
		//출력결과 : ["category : kook","productid : 20"] 하나의 문장으로 넘어온다.
	}
	
	//@PostMapping("/ticket")
	@RequestMapping(value = "/ticket", method = RequestMethod.POST)
	//@RequestBody는 json으로 클라이언트에서 보낼시 자바의 객체로 변환한다. 
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert...Ticket : " + ticket);
		
		return ticket;
	}
}