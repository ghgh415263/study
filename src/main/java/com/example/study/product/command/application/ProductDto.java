package com.example.study.product.command.application;

import com.example.study.product.command.domain.ProductTag;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProductDto(

        @NotBlank(message = "상품 이름은 필수입니다.")
        String name,

        @NotBlank(message = "상품 가격은 필수입니다.")
        int price,

        @NotBlank(message = "상품 수량은 필수입니다.")
        int stockQuantity,

        List<ProductTag> productTags
) {}
