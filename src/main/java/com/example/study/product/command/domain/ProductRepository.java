package com.example.study.product.command.domain;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);
}
