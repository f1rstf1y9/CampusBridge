package com.tnasfer.gbict.domain.member.service;

import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.entity.Role;
import com.tnasfer.gbict.domain.member.repository.MemberRepository;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Member createMember(Member member){
        verifyExistsMemberId(member);
        String password = passwordEncoder.encode(member.getPassword());
        member.setPassword(password);
        member.setRoleNameList(Role.builder().member(member).roleName(Role.RoleName.USER).build());
        return repository.save(member);
    }

    public Member findByMemberFromId(long id){
        return repository.findById(id).orElseThrow(
                ()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
    }

    private void verifyExistsMemberId(Member member) {
        Optional<Member> verifyMember = repository.findByMemberId(member.getMemberId());
        if (verifyMember.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }
}
