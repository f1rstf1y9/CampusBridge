package com.tnasfer.gbict.domain.member.repository;

import com.tnasfer.gbict.domain.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByMember_Id(long id);
}
