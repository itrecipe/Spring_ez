package org.ezen.ex02.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.ezen.ex02.domain.BoardAttachVO;
import org.ezen.ex02.mapper.BoardAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component //일반 클래스를 bean으로 등록하여 처리
public class FileCheckTask {
	
	@Setter(onMethod_= {@Autowired}) //bean으로 주입을 받겠다는 의미
	private BoardAttachMapper attachMapper; 

	private String getFolderYesterDay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance(); //오늘 날짜를 표시하는 객체
		
		cal.add(Calendar.DATE, -1); //날짜에서 1을 빼준 날짜
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron = "0 40 11 * * *") //초, 분, 시간, 일, 월, 요일, 임의로 11시 40분으로 삭제처리 설정해둠
	public void checkFiles() throws Exception {
		
		log.warn("File Check Task Run...");
		log.warn(new Date());
		
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("c:/upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());
		
		fileList.stream().filter(vo -> vo.isFileType() == true)
				.map(vo -> Paths.get("c:/upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
				.forEach(p -> fileListPaths.add(p));
		
		log.warn("============");
		
		fileListPaths.forEach(p -> log.warn(p));
		
		File targetDir = Paths.get("c:/uplaod", getFolderYesterDay()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("===============================================");
		
		for(File file : removeFiles) {
			
		}
				
		
	}
}
