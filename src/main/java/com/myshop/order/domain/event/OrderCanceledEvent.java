package com.myshop.order.domain.event;

import com.myshop.common.event.Event;
import lombok.Getter;

@Getter
public class OrderCanceledEvent extends Event {
    private String orderNumber;
    public OrderCanceledEvent(String orderNumber) {
        super(); // 생성시간 초기화
        this.orderNumber = orderNumber;
    }
}
