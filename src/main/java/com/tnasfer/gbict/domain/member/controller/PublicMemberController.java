package com.tnasfer.gbict.domain.member.controller;

import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.mapper.MemberMapper;
import com.tnasfer.gbict.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public")
@RequiredArgsConstructor
public class PublicMemberController {
    private final MemberService service;
    private final MemberMapper mapper;

    @PostMapping("/signup")
    public ResponseEntity<?> postSignUp(@Valid @RequestBody MemberDto.SingUp singUp){
        Member member = mapper.SignUpDtoToMember(singUp);
        service.createMember(member);
        return ResponseEntity.ok().build();
    }

}
