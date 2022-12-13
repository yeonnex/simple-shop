package com.myshop.order.domain;

import com.myshop.member.domain.MemberId;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Orderer {
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    private MemberId memberId;

    public Orderer(MemberId memberId) {
        this.memberId = memberId;
    }
}
