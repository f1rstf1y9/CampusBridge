package com.tnasfer.gbict.domain.ocr.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OcrDto {

    @Builder
    @Getter
    public static class ResponseConvertDocs{
        private String convertString;
        private long convertDocumentId;
        private long DocumentId;
    }
}
