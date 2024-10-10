package com.tnasfer.gbict.domain.member.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login{
        @NotBlank
        private String memberId;
        @NotBlank
        private String password;
    }

    @Getter
    @Builder
    public static class SingUp{
        @NotBlank
        private String memberId;
        @NotBlank
        private String password;
        @NotBlank
        private String name;
    }

    public static class Response{

        @Getter
        @Builder
        public static class Login{
            @NotBlank
            private long id;
            @NotBlank
            private String memberId;
            @NotBlank
            private String name;
        }

        @Getter
        @Builder
        public static class getInfo{
            @NotBlank
            private String memberId;
            @NotBlank
            private String name;
        }
    }


}
