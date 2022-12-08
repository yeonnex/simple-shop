package com.myshop.order.domain;

import com.myshop.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Member, Long> {
}
