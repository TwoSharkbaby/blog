package com.naver.www.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateReqDto {
	
	@NotNull(message = "password 키캆이 없습니다.")
	@NotBlank(message = "password를 입력하세요.")
	private String password;
	private String phone;

}
