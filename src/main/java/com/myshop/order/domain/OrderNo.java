package com.myshop.order.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderNo implements Serializable {
    private int number;
}
