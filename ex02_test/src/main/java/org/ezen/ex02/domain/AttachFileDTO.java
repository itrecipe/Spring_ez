package org.ezen.ex02.domain;

import lombok.Data;

//첨부파일 관련 클래스
@Data
public class AttachFileDTO {

	private String fileName; //원본파일명
	private String uploadPath; //업로드할 경로(YYYY/MM/DD만 가능)
	private String uuid; //UUID값
	private boolean image; //이미지는 true, 이미지가 아닌 경우는 false
}
