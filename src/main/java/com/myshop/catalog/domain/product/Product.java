package com.myshop.catalog.domain.product;

import com.myshop.common.jpa.MoneyConverter;
import com.myshop.common.model.Money;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Convert(converter = MoneyConverter.class)
    private Money price;
    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductSellStatus sellStatus;


}
