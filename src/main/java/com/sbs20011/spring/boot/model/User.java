package com.sbs20011.spring.boot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	private int userSeq;
	private String name;
	private String regDate;
}
