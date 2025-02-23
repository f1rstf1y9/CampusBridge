package com.tnasfer.gbict.domain.document.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.mapper.DocumentMapper;
import com.tnasfer.gbict.domain.document.service.DocumentService;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.service.MemberService;
import com.tnasfer.gbict.global.Authenticate.AuthenticationName;
import com.tnasfer.gbict.global.responseDto.PageNationResponse;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/auth/docs")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentMapper mapper;
    private final DocumentService documentService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> postDocument(@RequestPart(value = "document")MultipartFile documentFile,
                                          @AuthenticationName long memberId) throws IOException, URISyntaxException {
        Member member = memberService.findByMemberFromId(memberId);
        Document documentInfo = new Document();
        Document saveDocument = documentService.saveDocument(documentFile,documentInfo, member);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(new URI("/public/ocr/id/"+saveDocument.getId())).build();
    }

    @GetMapping(value = {"/docsId/{docs_id}"})
    public ResponseEntity<?> getDocumentInfo(@PathVariable(value = "docs_id",required = true) long documentId,
                                             @AuthenticationName long memberId){
        Document document = documentService.getDocumentByIdFromDocumentId(documentId, memberId);
        return ResponseEntity.ok().body(mapper.getDetailResponseDtoToDocument(document));
    }




    @GetMapping
    public ResponseEntity<?> getPageNationDocumentInfo(@RequestParam(value = "page", required = true, defaultValue = "1") @Positive int page,
                                                       @RequestParam(value = "size", required = true, defaultValue = "6") @Positive int size,
                                                       @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
                                                       Principal principal) throws JsonProcessingException {
        Page<Document> documentPage = documentService.getPageNationDocument(Long.parseLong(principal.getName()),page,size,sort);
        List<DocumentDto.GetResponse> getResponseList = mapper.getResponseDtoToDocumentList(documentPage.getContent());
        PageNationResponse<DocumentDto.GetResponse> pageNationResponse = new PageNationResponse<>(getResponseList,documentPage);

        ObjectMapper objectMapper = new ObjectMapper();
        log.info("info page : {}",objectMapper.writeValueAsString(pageNationResponse.getPageInfo()));
        return ResponseEntity.ok().body(pageNationResponse);
    }
}
