package com.tnasfer.gbict.domain.ocr.entity;


import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.global.baseTime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

// TODO: 현재 OCR 테스트를 위해 참조키 널 값을 허용 하고 있음 실제 서비스시에 널값을 사용하지 않도록 할 예정
public class OcrDocument extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String convertString;


    // TODO: 현재 양방향 매핑을 이용하고 있음 단방향으로 바꿀지 혹은 하나에 엔티티에 관리할지 상의 필요해 보임
    @Setter
    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;



}
