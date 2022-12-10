package com.myshop.catalog.domain.category;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CategoryId {
    @Column(name = "category_id")
    private Long value;
}
