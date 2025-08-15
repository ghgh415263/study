package com.example.study.product.command.infra;

import com.example.study.product.command.domain.Product;
import com.example.study.product.command.domain.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaProductRepository implements ProductRepository{

    private final EntityManager entityManager;

    @Override
    public Product save(Product product){
        if (product.getId() == null) {
            entityManager.persist(product);
            return product;
        } else {
            return entityManager.merge(product);
        }
    }

    @Override
    public Product findById(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id))
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다."));
    }

}
