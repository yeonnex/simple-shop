package com.myshop.order.domain;

import com.myshop.common.model.Address;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class ShippingInfo {
    @Embedded
    private Receiver receiver;
    @Embedded
    private Address address;
}
