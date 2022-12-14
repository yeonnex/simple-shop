package com.myshop.order.domain.event;

import com.myshop.order.domain.OrderNo;
import com.myshop.order.domain.ShippingInfo;

public class ShippingInfoChangedEvent {
    private OrderNo orderNo;
    private long timestamp;
    private ShippingInfo shippingInfo;

    public ShippingInfoChangedEvent(OrderNo orderNo, ShippingInfo shippingInfo) {
        this.orderNo = orderNo;
        this.shippingInfo = shippingInfo;
        this.timestamp = System.currentTimeMillis();
    }
}
