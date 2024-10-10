package com.tnasfer.gbict.global.member.data;

import com.tnasfer.gbict.domain.member.entity.Member;

import java.util.List;

public class MockMember {

    public static Member createMember(){
        return Member.builder()
                .memberId("testMemberId")
                .name("userNickName")
                .password("testPassword")
                .roleNameList(List.of())
                .build();
    }
    public static Member getMember(){
        return Member.builder()
                .id(1)
                .memberId("testMemberId")
                .name("userNickName")
                .password("testPassword")
                .roleNameList(List.of())
                .build();
    }
}
