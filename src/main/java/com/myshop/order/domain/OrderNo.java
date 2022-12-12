package com.myshop.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class OrderNo implements Serializable {
    @Column(name = "order_number")
    private int number;
}
