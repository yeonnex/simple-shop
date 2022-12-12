package com.myshop.order.domain;

import com.myshop.catalog.domain.product.ProductId;
import com.myshop.common.jpa.MoneyConverter;
import com.myshop.common.model.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
public class OrderLine {

    @Column(name = "product_id")
    private ProductId productId;
    private int quantity;

    @Column(name = "price")
    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Column(name = "amounts")
    @Convert(converter = MoneyConverter.class)
    private Money amounts;
    public OrderLine(ProductId productId,int quantity, Money price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }
}
