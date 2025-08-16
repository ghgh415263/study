package com.example.study.product.ui;

import com.example.study.common.ApiSuccessResponse;
import com.example.study.product.command.application.ProductDto;
import com.example.study.product.command.application.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiSuccessResponse<Void> save(@Valid @RequestBody ProductDto dto){
        productService.saveProduct(dto);
        return ApiSuccessResponse.empty();
    }
}
