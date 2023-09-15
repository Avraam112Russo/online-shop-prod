package com.russozaripov.onlineshopproduction.service.s3service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private static final String BUCKET_NAME = "bucket-russiano";
    private final AmazonS3Client amazonS3Client;

    public String add_New_File(MultipartFile file) throws IOException {
    String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
    String fileName = UUID.randomUUID().toString() + "." + fileExtension;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        amazonS3Client.putObject(BUCKET_NAME, fileName, file.getInputStream(), objectMetadata);
        amazonS3Client.setObjectAcl(BUCKET_NAME, fileName, CannedAccessControlList.PublicRead);
       return amazonS3Client.getResourceUrl(BUCKET_NAME, fileName);
    }
}
