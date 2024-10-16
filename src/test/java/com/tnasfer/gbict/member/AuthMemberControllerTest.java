package com.tnasfer.gbict.member;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.mapper.MemberMapper;
import com.tnasfer.gbict.domain.member.service.MemberService;
import com.tnasfer.gbict.global.member.auth.WithCustomMockMember;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "compusbridge.store", uriPort = 443)
public class AuthMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService service;

    @MockBean
    private MemberMapper mapper;

    @Test
    @WithCustomMockMember
    void getMemberInfo() throws Exception {

        // Given
        Member member = new Member();  // 필요한 필드에 맞게 생성
        MemberDto.Response.getInfo response = MemberDto.Response.getInfo.builder().memberId("memberSignUpId").name("memberNickName").build();

        // When
        given(service.findByMemberFromId(Mockito.anyLong())).willReturn(member);
        given(mapper.memberToGetInfoResponse(member)).willReturn(response);

        // Then
        this.mockMvc.perform(get("/auth/memberInfo")
                        .header("Authorization", "Bearer { JWT }")
                        .principal(() -> "1"))  // Authentication principal로 ID 전달
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value("memberSignUpId"))
                .andExpect(jsonPath("$.name").value("memberNickName"))
                .andDo(print())
                .andDo(document("get-member-info",
                        preprocessRequest(prettyPrint()),  // 요청과 응답을 예쁘게 포맷팅
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer token for authentication")
                        ),
                        responseFields(
                                fieldWithPath("memberId").description("멤버 로그인 아이디"),
                                fieldWithPath("name").description("멤버 닉네임")
                        )
                ));
    }
}

