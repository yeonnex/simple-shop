package com.myshop.order.domain;

import com.myshop.member.domain.MemberId;
import jakarta.persistence.*;

@Embeddable
public class Orderer {
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    private MemberId memberId;
}
