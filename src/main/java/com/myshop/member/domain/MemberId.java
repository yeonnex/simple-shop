package com.myshop.member.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MemberId implements Serializable {
    private String id;
}
