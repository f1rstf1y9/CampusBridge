package com.tnasfer.gbict.domain.member.auth.util;

import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.repository.MemberRepository;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUsersDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByMemberId(username).orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return new CustomUserDetail(member);
    }
}
