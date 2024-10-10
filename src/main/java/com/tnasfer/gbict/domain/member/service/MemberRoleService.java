package com.tnasfer.gbict.domain.member.service;


import com.tnasfer.gbict.domain.member.entity.Role;
import com.tnasfer.gbict.domain.member.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRoleService {
    public final MemberRoleRepository repository;

    public List<Role> findRoleListByMemberId(long id){
        return repository.findAllByMember_Id(id);
    }
}
