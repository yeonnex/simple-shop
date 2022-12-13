package com.myshop.order.application;

import com.myshop.order.domain.service.CalculateDiscountService;
import jakarta.transaction.Transactional;

@Transactional
public class OrderService {
    private CalculateDiscountService discountService;

}
