package com.myshop.order.service;

import com.myshop.member.domain.Member;
import com.myshop.common.model.Money;
import com.myshop.order.domain.OrderLine;
import com.myshop.order.domain.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CalculateDiscountService {
    private RuleDiscounter ruleDiscounter;
    private CustomerRepository customerRepository;

    public CalculateDiscountService(RuleDiscounter ruleDiscounter) {
        this.ruleDiscounter = ruleDiscounter;
    }

    public Money calculateDiscount(List<OrderLine> orderLines, String customerId) {
        Member customer = customerRepository.findById(Long.valueOf(customerId)).orElseThrow(() ->  new EntityNotFoundException());
        return ruleDiscounter.applyRules(customer, orderLines);
    }
}
