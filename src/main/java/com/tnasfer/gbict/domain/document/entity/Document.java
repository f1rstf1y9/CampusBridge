package com.tnasfer.gbict.domain.document.entity;


import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.global.baseTime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Document extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String original;

    @Setter
    @Column(nullable = false)
    private String translated;

    @Setter
    @Column(nullable = false)
    private String url;

    @Setter
    @Column(nullable = false)
    private Status status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @RequiredArgsConstructor
    public enum Status{
        ACTIVE("active"),
        INACTIVE("inactive");
        private final String status;
    }
}
