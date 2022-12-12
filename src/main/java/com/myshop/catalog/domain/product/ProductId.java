package com.myshop.catalog.domain.product;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
public class ProductId {
    private String id;
    public ProductId(String id) {
        this.id = id;
    }
}
