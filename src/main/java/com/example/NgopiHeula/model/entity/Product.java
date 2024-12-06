package com.example.NgopiHeula.model.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, insertable=false, updatable=false)
    private Category category;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    public Product(Integer productId,Integer categoryId, String name,BigDecimal price, Boolean isAvailable){
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }
}
