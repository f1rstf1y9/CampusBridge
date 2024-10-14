package com.tnasfer.gbict.domain.document.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.mapper.DocumentMapper;
import com.tnasfer.gbict.domain.document.service.DocumentService;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.service.MemberService;
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
                                          @RequestPart(value = "documentInfo") DocumentDto.PostRequest request,
                                          Principal principal) throws IOException, URISyntaxException {
        Member member = memberService.findByMemberFromId(Long.parseLong(principal.getName()));
        Document documentInfo = mapper.documentToRequestDto(request);
        Document saveDocument = documentService.saveDocument(documentFile,documentInfo, member);
//        DocumentDto.PostResponse response = mapper.responsePostDtoToDocument(saveDocument);
//        String token =  servletRequest.getHeader("Authorization");
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization",token);
//        headers.setLocation(new URI("http://localhost:8080"+"/ocr/id/"+saveDocument.getId()));
//        log.info(token);

        // TODO: 리다이렉트를 이용하여 서버에서 바로 OCR를 하도록 구성 였으나 결합도가 높아지는 단점이 보임
        //  프론트에서 OCR요청을 날리도록 할건지 논의가 필요해 보임
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(new URI("/public/ocr/id/"+saveDocument.getId())).build();
    }

    @GetMapping(value = {"/docsId/{docs_id}"})
    public ResponseEntity<?> getDocumentInfo(@PathVariable(value = "docs_id",required = true) long id){
        Document document = documentService.getDocument(id);
        return ResponseEntity.ok().body(mapper.getResponseDtoToDocument(document));
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
