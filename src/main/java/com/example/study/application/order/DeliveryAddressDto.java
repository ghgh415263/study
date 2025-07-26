package com.example.study.application.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeliveryAddressDto {

    @NotBlank(message = "배송지 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "우편번호는 필수입니다.")
    private String zipCode;

    @NotBlank(message = "기본 주소는 필수입니다.")
    private String baseAddress;

    @NotBlank(message = "상세 주소는 필수입니다.")
    private String detailAddress;
}