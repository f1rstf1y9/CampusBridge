package com.tnasfer.gbict.domain.member.entity;

import com.tnasfer.gbict.global.baseTime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="roles")
public class Role extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleName roleName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;



    //TODO: 권한 리스트 구현 및 테이블 확인 그리고 멤버 서비스 권환 저장 구현 해야함
    @Getter
    public enum RoleName{
        ADMIN("ROLE_ADMIN"),
        USER("ROLE_USER");
        private final String roleName;

        RoleName(String roleName) {
            this.roleName = roleName;
        }
    }

}
