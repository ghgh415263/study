package com.example.study.product.command.domain;

import com.example.study.common.persistance.BaseUpdateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Audited
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductTag> productTags = new ArrayList<>();

    public Product(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void setProductTags(ProductTag tags) {
        this.productTags.add(tags);
        tags.setProduct(this);
    }
}
