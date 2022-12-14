package com.myshop.common.event;

import com.myshop.order.domain.event.OrderCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderCanceledEventHandler {
    private RefundService refundService = null;
    public OrderCanceledEventHandler(RefundService refundService) {
        this.refundService = refundService;
    }

    @Async
    @EventListener(OrderCanceledEvent.class)
    public void handle(OrderCanceledEvent event) {
        refundService.refund(event.getOrderNumber());
    }

}
