package com.tnasfer.gbict.domain.ocr.mapper;

import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.ocr.dto.OcrDto;
import com.tnasfer.gbict.domain.ocr.entity.OcrDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OcrMapper {
     default OcrDto.ResponseConvertDocs ResponseConvertDocsToOcrDocument(OcrDocument ocrDocument){
        return OcrDto.ResponseConvertDocs.builder()
                .convertDocumentId(ocrDocument.getId())
                .DocumentId(ocrDocument.getDocument() != null ? ocrDocument.getDocument().getId() : 0)
                .convertString(ocrDocument.getConvertString())
                .build();
    }
}
