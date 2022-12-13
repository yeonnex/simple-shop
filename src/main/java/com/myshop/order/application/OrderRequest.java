package com.myshop.order.application;

import com.myshop.member.domain.MemberId;
import com.myshop.order.domain.ShippingInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private MemberId memberId;
    private List<OrderProduct> orderProducts;
    private ShippingInfo shippingInfo;

}
