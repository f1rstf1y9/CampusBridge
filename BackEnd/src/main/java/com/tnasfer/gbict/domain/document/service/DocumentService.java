package com.tnasfer.gbict.domain.document.service;

import com.tnasfer.gbict.domain.document.entity.Document;
import com.tnasfer.gbict.domain.document.mapper.DocumentMapper;
import com.tnasfer.gbict.domain.document.repository.DocumentRepository;
import com.tnasfer.gbict.domain.member.entity.Member;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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


    // 문서 저장
    @Transactional
    public Document saveDocument(MultipartFile file, Document document, Member member) throws IOException {

        //사용자 확인 및 확장자 구하기
        this.ext = setExt(file);

        // 파일 이름 및 저장 위치 설정
        StringBuilder fileName = new StringBuilder(S3_DNS+DOCUMENT_FILE_URL);
        fileName.append("/").append(UUID.randomUUID()).append(".").append(ext);

        // 디비에 저장할 파일 저장 정보를 확인 하고 객체 구성
        log.info("fileName : {} ", fileName.toString());

        setDocumentData(document, fileName.toString(), member);
        insertDocumentToS3(file, fileName.toString());

        //구성된 객체 디비에 저장
        return repository.save(document);

    }


    public Document getDocument(long id){
        return repository.findById(id).orElseThrow(
                ()->new BusinessLogicException(ExceptionCode.DOCUMENT_NOT_FOUND)
        );
    }

    //TODO 로직 확인 하기 멤버와 문서가 같은 디비 인지 확인
    // (하나의) 문서 상세 조회
    public Document getDocumentByIdFromDocumentId(long documentId, long memberId) {
        return repository.findByIdAndMember_Id(documentId,memberId).orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.DOCUMENT_NOT_FOUND)
        );
    }

    public Page<Document> getPageNationDocument(long memberId, int page, int size ,String sort){
        if (!sort.equals("desc") && !sort.equals("asc")){
            throw new BusinessLogicException(ExceptionCode.BAD_REQUEST_SORT_DATA);
        }
        Pageable pageable = sort.equals("desc") ?
                PageRequest.of(page-1,size, Sort.by(Sort.Order.desc("createdAt"))) :
                PageRequest.of(page-1,size, Sort.by(Sort.Order.asc("createdAt")));

        return repository.findAllByMember_Id(memberId,pageable);
    }

    //s3버킷에 파일 저장
    private void insertDocumentToS3(MultipartFile file, String fileUrl) throws IOException {
        String concatStr = fileUrl.replace(S3_DNS+"/","");
        log.info("concatStr : {} ", concatStr);

        if (ext.equalsIgnoreCase("pdf") || Objects.equals(file.getContentType(), "application/pdf")){
            ByteArrayOutputStream byteArrayOutputStream = convertPdfFile(file);
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(buket)
                    .key(concatStr)
                    .contentType("image/png")
                    .build(), RequestBody.fromBytes(byteArrayOutputStream.toByteArray())
            );
        }
        else{
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(buket)
                    .key(concatStr)
                    .contentType(file.getContentType())
                    .build(), RequestBody.fromBytes(file.getInputStream().readAllBytes()));
        }


    }

    //PDF 이미지 변환
    private ByteArrayOutputStream convertPdfFile(MultipartFile file){
        try{
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            ByteArrayOutputStream convertFileByte = new ByteArrayOutputStream();
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);
            ImageIO.write(image,"png",convertFileByte);

            return convertFileByte;

        }catch (IOException e){
            throw new BusinessLogicException(ExceptionCode.BAD_REQUEST_SORT_DATA);
        }

    }


    //TODO: 번역 기능 추가 해야함
    private void setDocumentData(Document document, String fileUrl, Member member) {
        document.setTranslated(" ");
        document.setUrl(fileUrl);
        document.setStatus(Document.Status.ACTIVE);
        document.setMember(member);
    }

    //확장자 구하기
    //TODO: 허용 확장자 정해야 함
    private String setExt(MultipartFile file) {

        String[] getExtArr = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        return getExtArr[getExtArr.length-1];

    }


}
