package com.myshop.order.domain.service;

import com.myshop.coupon.domain.Coupon;
import com.myshop.member.domain.Member;
import com.myshop.common.model.Money;
import com.myshop.order.domain.OrderLine;
import com.myshop.member.domain.MemberRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CalculateDiscountService {
    public Money calculateDiscountAmounts(List<OrderLine> orderLines,
                                          List<Coupon> coupons) {
        Money couponDiscount = coupons.stream()
                .map(coupon -> calculateDiscount(coupon))
                .reduce(new Money(0), (v1, v2) -> v1.add(v2));
        return couponDiscount;
    }

    private Money calculateDiscount(Coupon coupon) {
        return null;
    }
}
