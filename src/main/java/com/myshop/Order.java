package com.myshop;

public class Order {
    private OrderStatus state;
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        if (!state.isShippingChangeable()) {
            throw new IllegalStateException("can't change shipping info in " + state);
        }
    }
}
