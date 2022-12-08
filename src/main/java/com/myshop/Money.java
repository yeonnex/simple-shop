package com.myshop;

import lombok.Getter;

@Getter
public class Money {
    private int value;
    Money(int value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }
    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }
}
