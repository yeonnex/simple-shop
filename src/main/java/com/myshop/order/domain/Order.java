package com.myshop.order.domain;

import com.myshop.common.jpa.MoneyConverter;
import com.myshop.common.model.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "purchase_order")
@Access(AccessType.FIELD)
public class Order implements Serializable {
    @EmbeddedId
    @Column(name = "order_number")
    private OrderNo number;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_line", joinColumns = {@JoinColumn(name = "order_number")})
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    @Column(name = "total_amounts")
    @Convert(converter = MoneyConverter.class)
    private Money totalAmounts;

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    private Orderer orderer;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo, Orderer orderer, OrderStatus status) {
        setOrderer(orderer);
//        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) throw new IllegalArgumentException("no Orderer");
        this.orderer = orderer;
    }

    /**
     * 배송 정보를 바꾼다
     *
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
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

//    private void setOrderLines(List<OrderLine> orderLines) {
//        verifyAtLeastOneOrMoreOrderLines(orderLines);
//        this.orderLines = orderLines;
//        calculateTotalAmounts();
//    }
//
//    private void calculateTotalAmounts() {
//        int sum = orderLines.stream()
//                .mapToInt(x -> x.getAmounts().getValue())
//                .sum();
//
//        this.totalAmounts = new Money(sum);
//    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void verifyNotYetShipped() {
        if (!status.isShippingChangeable()) {
            throw new IllegalStateException("already shipped");
        }
    }
}
