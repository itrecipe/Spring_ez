package org.ezen.ex02.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.ezen.ex02.domain.AttachFileDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
		
		
		String uploadFolder = "c:/upload";
		
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
	
	/*
	// 파일 업로드
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public String uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("update ajax Post...");
		
		String uploadFolder = "c:/upload";
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			System.out.println("파일명 : " + uploadFileName);
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
			log.info("only file name : " + uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			}
			catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
			
		} //end for
		return "success";
	}
*/	
	/*
	//날짜 형식으로 파일 업로드 하기
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public String uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("update ajax post...");
		
		String uploadFolder = "c:/upload";

		//날짜별로된 폴더를 이용한 경로
		File uploadPath = new File(uploadFolder, getFolder());
		
		if(uploadPath.exists() == false) { 
			uploadPath.mkdirs(); //File 객체의 경로를 이용해서 폴더를 생성한다.
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			System.out.println("파일명 : " + uploadFileName);
			
			//순수 파일명
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
			
			log.info("only file name : " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			//임의의 UUID 객체 생성
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//File saveFile = new File(uploadFolder, uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName); //날짜 형식 경로
			
			try {
				multipartFile.transferTo(saveFile); //원본 파일 저장
			}
			catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return "success";
	}
	*/
	
	/*
	//썸네일 형식으로 파일 업로드 하기 (썸네일 만들기)
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public String uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("update ajax post...");
		
		String uploadFolder = "c:/upload";

		//날짜별로된 폴더를 이용한 경로
		File uploadPath = new File(uploadFolder, getFolder());
		
		if(uploadPath.exists() == false) { 
			uploadPath.mkdirs(); //File 객체의 경로를 이용해서 폴더를 생성한다.
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			System.out.println("파일명 : " + uploadFileName);
			
			//순수 파일명
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
			
			log.info("only file name : " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			//임의의 UUID 객체 생성
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//File saveFile = new File(uploadFolder, uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName); //날짜 형식 경로
			
			try {
				multipartFile.transferTo(saveFile); //원본 파일 저장
				
				//이미지 파일인지 아닌지를 체크
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					//섬네일 이름의 출력 스트림을 생성한다.
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					//출력 스트림에 저장된 thumbnail로 부터 읽어와서 크기 100 * 100의 섬네일 파일을 생성한다.
					thumbnail.close(); //끝나면 close 처리
				}
			}
			catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return "success";
	}
	*/
	
	//브라우저에서 업로드 결과를 보여주기 위한 JSON으로 첨부파일 관련 객체 보내기
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		log.info("update ajax post...");
		
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();		
		String uploadFolder = "c:/upload";

		String uploadFolderPath = getFolder();
		// make folder --------
		File uploadPath = new File(uploadFolder, uploadFolderPath);
				
		if(uploadPath.exists() == false) { 
			uploadPath.mkdirs(); //File 객체의 경로를 이용해서 폴더를 생성한다.
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			System.out.println("파일명 : " + uploadFileName);
			
			//순수 파일명
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
			
			log.info("only file name : " + uploadFileName);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			//임의의 UUID 객체 생성
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				//File saveFile = new File(uploadFolder, uploadFileName);
				File saveFile = new File(uploadPath, uploadFileName); //날짜 형식 경로
				
				multipartFile.transferTo(saveFile); //원본 파일 저장
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				//이미지 파일인지 아닌지를 체크
				if(checkImageType(saveFile)) {

					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					//섬네일 이름의 출력 스트림을 생성한다.
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					//출력 스트림에 저장된 thumbnail로 부터 읽어와서 크기 100 * 100의 섬네일 파일을 생성한다.
					thumbnail.close(); //끝나면 close 처리
				}
				list.add(attachDTO);
			}
			catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	};
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date); //날짜를 yyyy-MM-dd형식의 문자열로 반환
		
		return str.replace("-", File.separator); //파일구분자로 -문자 변경
	}
	
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			//Path 객체를 이용해 파일의 content 형식 반환
			
			return contentType.startsWith("image");
			//image일시 true를 반환
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) { //실제 이미지 데이터를 바이트 배열로 보낸다.
		
		//실제 이미지 데이터를 바이트 배열로 보낸다(외부 경로에 있는 파일에는 직접 접근이 불가능하여 바이트 배열로 데이터를 보낸다.)
		//fileName은 전체 경로로 보낸다(YYYY/MM/DD/S_UUID/이름)
		log.info("fileName : " + fileName);
		
		File file = new File("c:/upload/" + fileName);
		
		log.info("file" + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("content-Type", Files.probeContentType(file.toPath()));
			//header에 Content-Type에 MIME 추가 한다.
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			//file객체를 byte배열로 반환하여 JSON으로 반환한다.
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}