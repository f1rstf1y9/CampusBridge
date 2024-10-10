package com.tnasfer.gbict.domain.member.entity;

import com.tnasfer.gbict.global.baseTime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    // 식별 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 유저 로그인 아이디
    @Setter
    @Column(nullable = false)
    private String memberId;


    //유저 패스워드
    @Setter
    @Column(nullable = false)
    private String password;


    //유저 닉네임
    @Setter
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roleNameList;



    public void setRoleNameList(Role role){
        if (roleNameList==null){
            this.roleNameList = new ArrayList<>(List.of(role));
        }else{
            roleNameList.add(role);
        }

    }


}
