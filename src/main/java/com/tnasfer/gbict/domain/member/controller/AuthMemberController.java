package com.tnasfer.gbict.domain.member.controller;


import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.mapper.MemberMapper;
import com.tnasfer.gbict.domain.member.service.MemberService;
import com.tnasfer.gbict.global.Authenticate.AuthenticationName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthMemberController {
    private final MemberService service;
    private final MemberMapper mapper;


    @GetMapping(value = "/memberInfo")
    public ResponseEntity<?> getMemberInfo(@AuthenticationName Long id){
        Member member = service.findByMemberFromId(id);
        MemberDto.Response.getInfo responseGetInfo = mapper.memberToGetInfoResponse(member);
        return new ResponseEntity<>(responseGetInfo, HttpStatus.OK);
    }
}
