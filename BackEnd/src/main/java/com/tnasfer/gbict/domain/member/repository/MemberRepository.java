package com.tnasfer.gbict.domain.member.repository;

import com.tnasfer.gbict.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberId(String memberId);
}
