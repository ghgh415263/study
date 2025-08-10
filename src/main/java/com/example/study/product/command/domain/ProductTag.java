package com.example.study.product.command.domain;

import jakarta.persistence.*;

@Entity
public class ProductTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_tag_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
