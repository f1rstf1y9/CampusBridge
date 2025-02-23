package com.tnasfer.gbict.domain.ocr.controller;


import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.service.DocumentService;
import com.tnasfer.gbict.domain.ocr.dto.OcrDto;
import com.tnasfer.gbict.domain.ocr.entity.OcrDocument;
import com.tnasfer.gbict.domain.ocr.mapper.OcrMapper;
import com.tnasfer.gbict.domain.ocr.sevice.OcrService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;



@RestController
//@RequestMapping(value = "/public/ocr")
@RequiredArgsConstructor
public class OcrController {
    private final OcrService ocrService;
    private final DocumentService documentService;
    private final OcrMapper mapper;


    // TODO: 클라이언트가 요청을 보낼지 서버가 리다이렉트해서 처리 할지 논의
    @SneakyThrows
    @GetMapping(value = "/public/ocr/id/{docs_id}")
    public ResponseEntity<?> convertToOcrFile(@Positive @PathVariable(value = "docs_id")int docsId){
        Document document = documentService.getDocument(docsId);
        OcrDocument ocrDocument = ocrService.convertOcrToOriginFile(document);
        OcrDto.ResponseConvertDocs responseConvertDocs = mapper.ResponseConvertDocsToOcrDocument(ocrDocument);
        return ResponseEntity.created(URI.create("/docsId"+responseConvertDocs.getDocumentId())).body(responseConvertDocs);
    }


    // TODO: OCR 테스트 용 -> 토큰 인증을 거치 도록 구성함
    @SneakyThrows
    @GetMapping(value = "/auth/ocr")
    public ResponseEntity<?> convertToOcrUrl(@RequestParam(value = "uri")String uri){
        OcrDocument ocrDocument = ocrService.convertOcrToOriginFile(uri);
        OcrDto.ResponseConvertDocs responseConvertDocs = mapper.ResponseConvertDocsToOcrDocument(ocrDocument);
        return ResponseEntity.created(URI.create("/docsId"+responseConvertDocs.getDocumentId())).body(responseConvertDocs);
    }
}
