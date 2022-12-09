package com.myshop.common.model;

import ch.qos.logback.core.boolex.EvaluationException;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"value"})
public class Money implements Comparable<Money>{
    private Integer value;
    public Money(int value) {
        this.value = value;
    }

    public Money() {

    }

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }
    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    @Override
    public int compareTo(Money money) {
        return this.getValue().compareTo(money.value);
    }
}
