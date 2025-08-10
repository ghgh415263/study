package com.example.study.product.command.application;

import com.example.study.product.command.domain.Product;
import com.example.study.product.command.domain.ProductRepository;
import com.example.study.product.command.domain.ProductTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long saveProduct(ProductDto dto){
        Product product = new Product(dto.name(), dto.price(), dto.stockQuantity());
        productRepository.save(product);

        for(ProductTag productTag : dto.productTags()){
            product.setProductTags(productTag);
        }
        return productRepository.save(product).getId();
    }

}
