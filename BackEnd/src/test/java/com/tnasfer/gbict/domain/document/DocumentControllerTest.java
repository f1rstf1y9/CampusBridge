package com.tnasfer.gbict.domain.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.mapper.DocumentMapper;
import com.tnasfer.gbict.domain.document.service.DocumentService;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.service.MemberService;
import com.tnasfer.gbict.global.member.auth.WithCustomMockMember;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "compusbridge.store", uriPort = 443)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private DocumentMapper mapper;
    @MockBean
    private DocumentService documentService;
    @MockBean
    private MemberService memberService;


    @Test
    @WithCustomMockMember
    @DisplayName("문서 저장 API")
    void postDocumentControllerTest() throws Exception {

        Member member = new Member();
        Document saveDocument = new Document();
        saveDocument.setId(1);

        MockMultipartFile mockFile = new MockMultipartFile(
                "document",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                "Test content".getBytes());

        given(memberService.findByMemberFromId(Mockito.anyLong())).willReturn(member);
        given(documentService.saveDocument(
                any(MultipartFile.class),any(Document.class),any(Member.class))
        ).willReturn(saveDocument);

        this.mockMvc.perform(multipart("/auth/docs")
                        .file(mockFile)
                        .with(request -> {
                            request.setMethod("POST");
                            return request;
                        })
                        .header("Authorization", "Bearer { JWT }")
                        .accept(MediaType.IMAGE_PNG_VALUE)
                        .accept(MediaType.APPLICATION_PDF)
                        .accept(MediaType.IMAGE_JPEG)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .principal(() -> "1"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("location","/public/ocr/id/"+1L))
                .andDo(print())
                .andDo(document("post-document",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer token for authentication")
                        ),
                        responseHeaders(
                                headerWithName("location").description("redirect URL")
                        )

                ));



    }


//    @Test
//    @DisplayName("문서 상세 정보 확인 API")
//    @WithCustomMockMember
//    void getDocumentInfo() throws Exception {
//
//        DocumentDto.GetDetailResponse getDetailResponse = DocumentDto.GetDetailResponse.builder()
//                .id(1)
//                .original("OCR String")
//                .translated("translated String")
//                .build();
//
//        given(documentService.getDocumentByIdFromDocumentId(any(Long.class), any(Long.class))).willReturn(new Document());
//        given(mapper.getDetailResponseDtoToDocument(any(Document.class))).willReturn(getDetailResponse);
//
//        this.mockMvc.perform(get("/auth/docs/docsId/1")
//                .header("Authorization","Bearer { JWT }"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andDo(document("get-documentDetailInfo",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//
//                        )
//    }

}
