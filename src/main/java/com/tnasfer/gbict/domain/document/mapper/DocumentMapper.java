package com.tnasfer.gbict.domain.document.mapper;

import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {
    Document documentToRequestDto(DocumentDto.Request request);
    DocumentDto.Response responseDtoToDocument(Document document);
}
