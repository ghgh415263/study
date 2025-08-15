package com.example.study.product.command.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProductTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_tag_id")
    private Long id;

    @Column(nullable = false)
    private String tagName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductTag(String tagName){
        this.tagName = tagName;
    }

    public void setProduct(Product product){
        this.product = product;
    }
}
