package com.example.study.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberJoinDto {

	@NotBlank(message = "로그인 아이디는 필수입니다.")
	@Size(min = 4, max = 20, message = "로그인 아이디는 4자 이상 20자 이하이어야 합니다.")
	private String loginId;

	@NotBlank(message = "비밀번호는 필수입니다.")
	@Size(min = 8, max = 30, message = "비밀번호는 8자 이상 30자 이하이어야 합니다.")
	private String password;

	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "유효한 이메일 주소여야 합니다.")
	private String email;

	@NotBlank(message = "이름은 필수입니다.")
	@Size(max = 50, message = "이름은 50자 이하이어야 합니다.")
	private String name;

	@Valid
	@NotNull(message = "주소는 필수입니다.")
	private AddressDto address;

	public MemberJoinDto(String loginId, String password, String email, String name, AddressDto address) {
		this.loginId = loginId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
	}
}
