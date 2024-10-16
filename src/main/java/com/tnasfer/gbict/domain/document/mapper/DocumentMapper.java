package com.tnasfer.gbict.domain.document.mapper;

import com.tnasfer.gbict.domain.document.dto.DocumentDto;
import com.tnasfer.gbict.domain.document.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {
    DocumentDto.PostResponse responsePostDtoToDocument(Document document);

    default DocumentDto.GetDetailResponse getDetailResponseDtoToDocument(Document document){
        return DocumentDto.GetDetailResponse.builder()
                .id(document.getId())
                .original(document.getOcrDocument() == null ? "ocr test String" : document.getOcrDocument().getConvertString())
                .translated(document.getTranslated())
                .build();
    }


    default DocumentDto.GetResponse getResponseDtoToDocument(Document document){
        String title;
        if (document.getOcrDocument() == null){
            title = document.getId()+"";
        }
        else if (document.getOcrDocument().getConvertString().length() > 20) {
            title = document.getOcrDocument().getConvertString().substring(0, 20).replace("\n", " ");
        } else {
            title = document.getOcrDocument().getConvertString().replace("\n", " ");
        }
        return DocumentDto.GetResponse.builder()
                .id(document.getId())
                .createdAt(document.getCreatedAt())
                .url(document.getUrl())
                .title(title)
                .build();
    }

    List<DocumentDto.GetResponse> getResponseDtoToDocumentList(List<Document> documentList);

}
