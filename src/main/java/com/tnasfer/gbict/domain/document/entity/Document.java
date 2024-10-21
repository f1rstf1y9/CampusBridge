package com.tnasfer.gbict.domain.document.entity;


import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.ocr.entity.OcrDocument;
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
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(nullable = false)
    private String translated;

    @Setter
    @Column(nullable = false)
    private String url;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    // TODO: 현재 양방향 매핑을 이용하고 있음 단방향으로 바꿀지 혹은 하나에 엔티티에서 관리할지 상의 필요해 보임
    @Setter
    @OneToOne(mappedBy = "document",cascade = CascadeType.ALL)
    private OcrDocument ocrDocument;


    @Getter
    public enum Status{
        ACTIVE("active"),
        INACTIVE("inactive");
        private final String status;

        Status(String status) {
            this.status = status;
        }
    }
}
