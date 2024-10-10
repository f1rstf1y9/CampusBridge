package com.tnasfer.gbict.domain.document.entity;


import com.tnasfer.gbict.global.baseTime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String original;

    @Column(nullable = false)
    private String translated;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Status status;


    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ACTIVE("active"),
        INACTIVE("inactive");
        private final String status;
    }
}
