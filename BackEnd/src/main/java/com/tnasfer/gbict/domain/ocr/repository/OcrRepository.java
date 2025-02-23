package com.tnasfer.gbict.domain.ocr.repository;

import com.tnasfer.gbict.domain.ocr.entity.OcrDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcrRepository extends JpaRepository<OcrDocument, Long>{


}
