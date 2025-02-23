package com.tnasfer.gbict.domain.ocr.sevice;


import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.ocr.entity.OcrDocument;
import com.tnasfer.gbict.domain.ocr.repository.OcrRepository;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OcrService {
    private static final Logger log = LoggerFactory.getLogger(OcrService.class);
    private final OcrRepository repository;


    @Transactional
    public OcrDocument convertOcrToOriginFile(Document document) throws IOException {
        URL url = new URL(document.getUrl());
        return repository.save(getOcrDocument(url,document));
    }

    @Transactional
    public OcrDocument convertOcrToOriginFile(String fileUri) throws IOException {
        URL uri = new URL(fileUri);
        return repository.save(getOcrDocument(uri));
    }

    //Ocr String 가져 오기
    private StringBuilder convertOcr(URL url) throws IOException {
        ByteString imgString = ByteString.readFrom(url.openStream());

        Image img = Image.newBuilder().setContent(imgString).build();
        Feature feature = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feature)
                .setImage(img)
                .build();
        List<AnnotateImageRequest> requestList = new ArrayList<>();
        requestList.add(request);

        ImageAnnotatorClient client = ImageAnnotatorClient.create();
        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requestList);
        StringBuilder sb = new StringBuilder();

        for (AnnotateImageResponse imageResponse : response.getResponsesList()){
            if (imageResponse.hasError()){
                throw new BusinessLogicException(imageResponse.getError().getMessage(), ExceptionCode.BAD_REQUEST_SORT_DATA);
            }
            sb.append(imageResponse.getFullTextAnnotation().getText());
        }
        return sb;
    }

    // TODO: ocr 을 문서에 저장 -> 참조키 있음 (실제 서비스 에 할용할 코드임)
    private OcrDocument getOcrDocument(URL url,Document document) throws IOException {
        StringBuilder convertOcr = convertOcr(url);

        OcrDocument ocrDocument = OcrDocument.builder()
                .convertString(convertOcr.toString())
                .document(document)
                .build();

        log.info("convertString : {}",convertOcr);
        return ocrDocument;
    }

    // TODO: ocr 을 문서에 저장 -> 참조키 null 값 임시 저장 (url 만있을 떄 테스트 용임)
    private OcrDocument getOcrDocument(URL url) throws IOException {
        StringBuilder convertOcr = convertOcr(url);

        OcrDocument ocrDocument = OcrDocument.builder()
                .convertString(convertOcr.toString())
                .build();

        log.info("convertString : {}", convertOcr);
        return ocrDocument;
    }
}
