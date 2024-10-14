package com.tnasfer.gbict.member;

import com.tnasfer.gbict.config.docs.RestDocsTest;
import com.tnasfer.gbict.domain.member.controller.PublicMemberController;
import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.domain.member.mapper.MemberMapper;
import com.tnasfer.gbict.domain.member.repository.MemberRepository;
import com.tnasfer.gbict.domain.member.service.MemberService;
import com.tnasfer.gbict.global.member.auth.WithCustomMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.print.DocFlavor;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MemberControllerTest extends RestDocsTest {

    private final MemberService service = Mockito.mock(MemberService.class);
    private final MemberMapper mapper = Mockito.mock(MemberMapper.class);

    @Autowired
    private MemberRepository repository;

    @Override
    protected Object initializeController() {
        return new PublicMemberController(service, mapper);
    }


    @DisplayName("회원 가입 API")
    @Test
    void signUpMemberTest() throws Exception{
        MemberDto.SingUp singUpRequest = MemberDto.SingUp.builder().memberId("memberId").name("userNickName").password("password").build();
        Member member = Member.builder().memberId("memberId").name("userNickName").id(1).build();
        given(mapper.SignUpDtoToMember(any(MemberDto.SingUp.class))).willReturn(new Member());
        given(service.createMember(any(Member.class))).willReturn(member);

        mockMvc.perform(RestDocumentationRequestBuilders.post("/public/signup")
                .content(objectMapper.writeValueAsString(singUpRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("signUp-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.STRING)
                                        .description("회원 로그인 아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("회원 이름(닉네임)"),
                                fieldWithPath("password")
                                        .description("회원 로그인 비밀번호")
                        )));
    }

//    @DisplayName("회원 로그인 API")
//    @Test
//    @WithCustomMockUser
//    @ExtendWith(SpringExtension.class)
//    void memberLoginTest() throws Exception{
//        MemberDto.Login testLogin = MemberDto.Login.builder().memberId("testMemberId").password("testPassword").build();
////        MemberDto.PostResponse.Login loginResponse = MemberDto.PostResponse.Login.builder().memberId("testMemberId").id(1).name("testMemberNickname").build();
//
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/public/login")
//                .content(objectMapper.writeValueAsString(testLogin))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("login-member",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                fieldWithPath("memberId").type(JsonFieldType.STRING)
//                                        .description("회원 로그인 아이디"),
//                                fieldWithPath("password").type(JsonFieldType.STRING)
//                                        .description("회원 로그인 비밀번호")),
//                        responseHeaders(
//                                headerWithName("Authorization").description("Bearer {accessToken....}")
//                        ),
//                        responseFields(
//                                fieldWithPath("id").type(JsonFieldType.NUMBER)
//                                        .description("회원 식별 아이디"),
//                                fieldWithPath("memberId").type(JsonFieldType.STRING)
//                                        .description("회원 로그인 아이디"),
//                                fieldWithPath("name").type(JsonFieldType.STRING)
//                                        .description("회원 이름(닉네임)")
//                        )
//
//                ));
//
//    }
}
