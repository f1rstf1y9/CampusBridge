package com.tnasfer.gbict.global.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
public class PageNationResponse<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public PageNationResponse(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(
                page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages());
    }

    public static <T> PageNationResponse<T> of(Page<T> page){
        return new PageNationResponse<>(page.getContent(),page);
    }

    public static <T> PageNationResponse<T> of(List<T> data, Page page){
        return new PageNationResponse<>(data,page);
    }


}
