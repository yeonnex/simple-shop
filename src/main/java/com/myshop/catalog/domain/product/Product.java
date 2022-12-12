package com.myshop.catalog.domain.product;

import com.myshop.catalog.domain.category.CategoryId;
import com.myshop.common.jpa.MoneyConverter;
import com.myshop.common.model.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY) // 하나의 상품이 여러 카테고리에 속할 수 있음. 상품과 카테고리는 M:N
    @CollectionTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "price")
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
