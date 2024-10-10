package com.tnasfer.gbict.domain.document.repository;

import com.tnasfer.gbict.domain.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByTitleAndMember_Id(String title,long memberId);
}
