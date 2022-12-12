package com.myshop.order.domain.service;

import com.myshop.member.domain.Member;
import com.myshop.common.model.Money;
import com.myshop.order.domain.OrderLine;
import com.myshop.member.domain.MemberRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CalculateDiscountService {
    private RuleDiscounter ruleDiscounter;
    private MemberRepository memberRepository;

    public CalculateDiscountService(RuleDiscounter ruleDiscounter) {
        this.ruleDiscounter = ruleDiscounter;
    }

    public Money calculateDiscount(List<OrderLine> orderLines, String customerId) {
        Member customer = memberRepository.findById(Long.valueOf(customerId)).orElseThrow(() ->  new EntityNotFoundException());
        return ruleDiscounter.applyRules(customer, orderLines);
    }
}
