package com.myshop.order.service;

import com.myshop.member.domain.Member;
import com.myshop.common.model.Money;
import com.myshop.order.domain.OrderLine;

import java.util.List;

public interface RuleDiscounter {
    Money applyRules(Member customer, List<OrderLine> orderLines);
}
