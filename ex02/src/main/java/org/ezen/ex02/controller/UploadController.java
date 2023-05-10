package org.ezen.ex02.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/upload")
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() { //여기서 리턴 타입이 void면 업로드 경로와 똑같은 요청 경로로 이동
		
		log.info("upload form");
		//views의 upload 촐더에 uploadForm.jsp로 이동
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		//servlet제공 업로드는 MultipartFile 클래스 객체로 처리하고 multiple이므로 배열, 파라미터명은 form의 name속성이다.
		//다를 경우는 @RequestParam을 사용한다. 
		
		
		String uploadFolder = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("------------------------------------------------------");
			log.info("Upload File Name" + multipartFile.getOriginalFilename()); //파일명
			log.info("Upload File Size" + multipartFile.getSize()); //파일 크기
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			//경로와 파일명을 사용하는 File 객체 생성
			try {
				multipartFile.transferTo(saveFile); //해당 경로에 File객체를 저장한다.
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end catch
		} // end for
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
}
