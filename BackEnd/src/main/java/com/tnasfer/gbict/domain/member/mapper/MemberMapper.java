package com.tnasfer.gbict.domain.member.mapper;

import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member SignUpDtoToMember(MemberDto.SingUp singUp);
    Member LoginDtoToMember(MemberDto.Login login);
    MemberDto.Response.Login memberToLoginResponse(Member member);
    MemberDto.Response.getInfo memberToGetInfoResponse(Member member);
}
