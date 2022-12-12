package com.myshop.member.application;

import com.myshop.member.domain.Member;
import com.myshop.member.domain.MemberRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Member 응용 서비스에서 공통되는 로직을 구현
 */
public class MemberServiceHelper {
    public static Member findExistingMember(MemberRepository repo, Long memberId) {
        return repo.findById(memberId).orElseThrow(EntityNotFoundException::new);
    }
}
