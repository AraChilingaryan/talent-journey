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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AmazonClientServiceImpl implements AmazonClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonClientServiceImpl.class);

    private AmazonS3 amazonS3;

    @Value("${amazon.s3.endpoint}")
    private String url;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.access-key}")
    private String accessKey;

    @Value("${amazon.s3.secret-key}")
    private String secretKey;

    // Getters for parents.
    protected AmazonS3 getClient() {
        return amazonS3;
    }

    protected String getUrl() {
        return url;
    }

    protected String getBucketName() {
        return bucketName;
    }

    @PostConstruct
    private void init() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String uploadFile(MultipartFile file, Talent talent) {
        LOGGER.info("Requested to upload a file to AWS S3 - {}", file.getName());
        File fileObj = convertMultiPartFileToFile(file);
        final Specialization specialization = talent.getSpecialization();
        final String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        final String fileName = new StringBuilder()
                .append(specialization.getSpecializationType())
                .append("/").append(yearMonth)
                .append("/").append(talent.getFullName())
                .append("/").append(file.getOriginalFilename()).toString();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        LOGGER.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return fileName;
    }


    public byte[] downloadFile(String fileName) {
        LOGGER.info("Requested to download a file from AWS S3 - {}", fileName);
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            LOGGER.info("IN downloadFile AWS : {} file successfully downloaded", fileName);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        LOGGER.info("Requested to delete a file from AWS S3 - {}", fileName);
        amazonS3.deleteObject(bucketName, fileName);
        LOGGER.info("IN deleteFile AWS : {} file successfully deleted", fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        LOGGER.info("Requested to covert a MultipartFile to a File object - {}", file.getName());
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("IN convertMultiPartFileToFile AWS : {} file successfully converted", file.getName());
        return convertedFile;
    }

    public void createFolder(String folderName) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folderName, emptyContent, metadata);

        // send request to S3 to create folder
        amazonS3.putObject(putObjectRequest);
    }

    public String uploadFile(MultipartFile file) {
        LOGGER.info("Requested to upload a file to AWS S3 - {}", file.getName());
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        LOGGER.info("In uploadFile AWS : {} file successfully uploaded", fileName);
        return "File uploaded : " + fileName;
    }

//    @Override
//    public URL upload(final File sourceFile, final String key) {
//        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, sourceFile)
//                .withCannedAcl(CannedAccessControlList.PublicRead);
//        final PutObjectResult putObjectResult = amazonS3.putObject(putObjectRequest);
//        final URL url = amazonS3.getUrl(bucketName, key);
//        return url;
//    }

//    @Override
//    public URL uploadMultipartFile(final MultipartFile multipartFile, final String key) {
//        PutObjectRequest putObjectRequest = null;
//        try {
//            putObjectRequest = new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), null)
//                    .withCannedAcl(CannedAccessControlList.PublicRead);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        final PutObjectResult putObjectResult = amazonS3.putObject(putObjectRequest);
//        final URL url = amazonS3.getUrl(bucketName, key);
//        return url;
//    }
//
    public String uploadFileMeta(MultipartFile file, Talent talent) throws IOException {
        LOGGER.info("Requested to upload a file to AWS S3 - {}", file.getName());
        final Specialization specialization = talent.getSpecialization();
        final String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        final String fileName = new StringBuilder()
                .append(specialization.getSpecializationType())
                .append("/").append(yearMonth)
                .append("_").append(talent.getFullName())
                .append("_").append(file.getOriginalFilename()).toString();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(inputStream.available());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata));
        LOGGER.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return "File uploaded : " + fileName;
    }

//    @Override
//    public PutObjectRequest uploadFile(String key,
//                                       String contentType,
//                                       InputStream inputStream,
//                                       Map<String, String> metadata, MultipartFile file) throws IOException {
//        AmazonS3 client = amazonS3;
//
//        TransferManager tm = TransferManagerBuilder.standard()
//                .withS3Client(client)
//                .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
//                .build();
//
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(file.getContentType());
//        objectMetadata.setContentLength(inputStream.available());
//        if (metadata != null) {
//            for (String k: metadata.keySet()) {
//                objectMetadata.addUserMetadata(k, metadata.get(key));
//            }
//        }
//
//        try {
//            PutObjectRequest request = new PutObjectRequest(
//                    bucketName, key, inputStream, objectMetadata);
//            request.withCannedAcl(CannedAccessControlList.PublicRead);
//
//            Upload upload = tm.upload(request);
//            upload.waitForCompletion();
//
//            return request;
//        } catch (AmazonClientException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public S3ObjectInputStream get(final String key) {
//        final S3Object object = amazonS3.getObject(bucketName, key);
//        final S3ObjectInputStream objectContent = object.getObjectContent();
//        return objectContent;
//    }
}





