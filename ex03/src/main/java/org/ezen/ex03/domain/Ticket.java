package org.ezen.ex03.domain;

import lombok.Data;

@Data //생성자는 기본형만 만들어준다 (getter, setter, toString, equals, canEqual, hashCode)
public class Ticket {
	private int tno;
	private String owner;
	private String grade;
}
