package com.tnasfer.gbict.domain.document.service;

import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.mapper.DocumentMapper;
import com.tnasfer.gbict.domain.document.repository.DocumentRepository;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {
    private final S3Client s3Client;
    private final DocumentMapper mapper;
    private final DocumentRepository repository;
    private final String DOCUMENT_FILE_URL = "/document";

    @Value("${cloud.s3.dns}")
    private String S3_DNS;
    @Value("${cloud.s3.bucket}")
    private String buket;
    private String ext;



    public Document saveDocument(MultipartFile file, Document document, Member member) throws IOException {

        //사용자 확인 및 확장자 구하기
        verifyExistsTitle(document.getTitle(),member.getId());
        this.ext = setExt(file);

        // 파일 이름 및 저장 위치 설정
        StringBuilder fileName = new StringBuilder(S3_DNS+DOCUMENT_FILE_URL);
        fileName.append("/").append(document.getTitle()).append(UUID.randomUUID()).append(".").append(ext);

        // 디비에 저장할 파일 저장 정보를 확인 하고 객체 구성
        log.info("fileName : {} ", fileName.toString());
        setDocumentData(document, fileName.toString(), member);
        insertDocumentToS3(file, fileName.toString());

        //구성된 객체 디비에 저장
        return repository.save(document);

    }

    //s3버킷에 파일 저장
    private void insertDocumentToS3(MultipartFile file, String fileUrl) throws IOException {
        String concatStr = fileUrl.replace(S3_DNS+"/","");
        log.info("concatStr : {} ", concatStr);
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(buket)
                        .key(concatStr)
                        .contentType(file.getContentType())
                        .build(), RequestBody.fromBytes(file.getInputStream().readAllBytes()));

    }

    //TODO: 번역 기능 추가 해야함
    private void setDocumentData(Document document, String fileUrl, Member member) {
        document.setTranslated(" ");
        document.setUrl(fileUrl);
        document.setStatus(Document.Status.ACTIVE);
        document.setMember(member);
    }

    //파일 이름 중복 확인
    private void verifyExistsTitle(String title,long memberId) {
        if (repository.findByTitleAndMember_Id(title,memberId).isPresent()){
            throw new BusinessLogicException(ExceptionCode.DOCUMENT_TITLE_EXISTS);
        }
    }


    //확장자 구하기
    //TODO: 허용 확장자 정해야 함 + 확장자 구하는 방식을 고쳐야 함
    private String setExt(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
    }

}
