package com.tnasfer.gbict.global.responseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
