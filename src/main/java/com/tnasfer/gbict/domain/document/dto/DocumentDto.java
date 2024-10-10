package com.tnasfer.gbict.domain.document.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentDto {

    @Getter
    @Builder
    public static class Request {

        @NotBlank
        private String title;
        @NotBlank
        private String original;

    }
    @Getter
    @Builder
    public static class Response {

        private String title;
        private String translated;
        private String url;
    }
}
