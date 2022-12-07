package com.myshop;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class Order {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private List<OrderLine> orderLines;
    private Money totalAmounts;
    private ShippingInfo shippingInfo;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    /**
     * 배송 정보를 바꾼다
     * @param newShippingInfo
     */
    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(shippingInfo);
    }

    /**
     * 주문을 취소한다
     */
    public void cancel() {
        verifyNotYetShipped();
        this.status = OrderStatus.CANCELED;
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null){
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void calculateTotalAmounts() {
        int sum = orderLines.stream()
                .mapToInt(x -> x.getAmounts())
                .sum();
        this.totalAmounts = new Money(sum);
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void verifyNotYetShipped() {
        if (! status.isShippingChangeable()) {
            throw new IllegalStateException("already shipped");
        }
    }
}
