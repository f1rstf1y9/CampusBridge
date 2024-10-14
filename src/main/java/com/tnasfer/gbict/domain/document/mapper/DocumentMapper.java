package com.tnasfer.gbict.domain.document.mapper;

import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {
    Document documentToRequestDto(DocumentDto.PostRequest request);
    DocumentDto.PostResponse responsePostDtoToDocument(Document document);
    default DocumentDto.GetResponse getResponseDtoToDocument(Document document){
        return DocumentDto.GetResponse.builder()
                .id(document.getId())
                .createdAt(document.getCreatedAt())
                .original(document.getOcrDocument().getConvertString())
                .translated(document.getTranslated())
                .build();
    }

    List<DocumentDto.GetResponse> getResponseDtoToDocumentList(List<Document> documentList);

}
