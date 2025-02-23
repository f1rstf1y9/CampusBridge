package com.tnasfer.gbict.domain.member.auth.util;

import com.tnasfer.gbict.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetail extends Member implements UserDetails {
    public CustomUserDetail(Member member) {
        super(member.getId(), member.getMemberId(), member.getPassword(), member.getName(),List.of());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    @Override
    public String getUsername() {
        return this.getMemberId();
    }
}
