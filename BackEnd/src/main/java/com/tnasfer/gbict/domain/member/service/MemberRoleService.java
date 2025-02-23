package com.tnasfer.gbict.domain.member.service;


import com.tnasfer.gbict.domain.member.entity.Role;
import com.tnasfer.gbict.domain.member.repository.MemberRoleRepository;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRoleService {
    public final MemberRoleRepository repository;

    public List<Role> findRoleListByMemberId(long id){
        List<Role> roleList =  repository.findAllByMember_Id(id);
        if (roleList.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return roleList;
    }
}
