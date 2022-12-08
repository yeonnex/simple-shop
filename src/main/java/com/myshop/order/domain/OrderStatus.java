package com.myshop.order.domain;

public enum OrderStatus {
    PAYMENT_WAITING,
    PREPARING,
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED;

    public boolean isShippingChangeable() {
        return switch (this) {
            case PAYMENT_WAITING, PREPARING -> true;
            case SHIPPED, DELIVERING, DELIVERY_COMPLETED, CANCELED -> false;
        };
    }
}
