package com.myshop.common.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
}
