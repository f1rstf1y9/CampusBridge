package com.tnasfer.gbict.domain.document.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.LocalDateTime;

@Builder
@Getter
public class DocumentDto {

    @Getter
    @Builder
    public static class PostRequest {

        @NotBlank
        private String title;
        @NotBlank
        private String original;

    }
    @Getter
    @Builder
    public static class PostResponse {

        private String title;
        private String translated;
        private String url;
    }

    @Getter
    @Builder
    public static class GetResponse{
        private long id;
        private String original;
        private String translated;
        private LocalDateTime createdAt;
    }
}
