package com.tnasfer.gbict.domain.document.controller;


import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.mapper.DocumentMapper;
import com.tnasfer.gbict.domain.document.service.DocumentService;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping(value = "/auth/docs")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentMapper mapper;
    private final DocumentService documentService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> postDocument(@RequestPart(value = "document")MultipartFile documentFile,
                                          @RequestPart(value = "documentInfo")DocumentDto.Request request,
                                          Principal principal) throws IOException {
        Member member = memberService.findByMemberFromId(Long.parseLong(principal.getName()));
        Document documentInfo = mapper.documentToRequestDto(request);
        Document saveDocument = documentService.saveDocument(documentFile,documentInfo, member);
        DocumentDto.Response response = mapper.responseDtoToDocument(saveDocument);
        return ResponseEntity.ok().body(response);
    }
}
