package com.disqo.talent_service.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.disqo.talent_service.persistance.entity.Specialization;
import com.disqo.talent_service.persistance.entity.Talent;
import com.disqo.talent_service.service.AmazonClientService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class AmazonClientServiceImpl implements AmazonClientService {

    private AmazonS3 amazonS3;

    @Value("${amazon.s3.endpoint}")
    private String url;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.access-key}")
    private String accessKey;

    @Value("${amazon.s3.secret-key}")
    private String secretKey;

    @PostConstruct
    private void init() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String uploadFile(MultipartFile file, Talent talent) {
        log.info("Requested to upload a file to AWS S3 - {}", file.getName());
        File fileObj = convertMultiPartFileToFile(file);
        final Specialization specialization = talent.getSpecialization();
        final String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        final String fileName = new StringBuilder()
                .append(specialization.getSpecializationType())
                .append("-").append(yearMonth)
                .append("-").append(talent.getName()).append(talent.getSurname())
                .append("-").append("CV").toString();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        log.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return fileName;
    }


    public byte[] downloadFile(String fileName) {
        log.info("Requested to download a file from AWS S3 - {}", fileName);
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            log.info("IN downloadFile AWS : {} file successfully downloaded", fileName);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        log.info("Requested to delete a file from AWS S3 - {}", fileName);
        amazonS3.deleteObject(bucketName, fileName);
        log.info("IN deleteFile AWS : {} file successfully deleted", fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        log.info("Requested to covert a MultipartFile to a File object - {}", file.getName());
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("IN convertMultiPartFileToFile AWS : {} file successfully converted", file.getName());
        return convertedFile;
    }
}





