package com.myshop.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MemberId implements Serializable {
    @Column(name = "member_id")
    private String id;
}
