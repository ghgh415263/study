package com.example.study.application;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddressDto {

    @NotBlank(message = "우편번호는 필수입니다.")
    private String zipCode;

    @NotBlank(message = "기본 주소는 필수입니다.")
    private String baseAddress;

    @NotBlank(message = "상세 주소는 필수입니다.")
    private String detailAddress;

    public AddressDto(String zipCode, String baseAddress, String detailAddress) {
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
    }
}