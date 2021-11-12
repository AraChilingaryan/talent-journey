package com.disqo.talent_service.rest;

import com.disqo.talent_service.service.AmazonClientService;
import com.disqo.talent_service.service.FileUploadService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    private final AmazonClientService amazonClientService;
    private final FileUploadService fileUploadService;

    public FileUploadController(AmazonClientService amazonClientService, FileUploadService fileUploadService) {
        this.amazonClientService = amazonClientService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = amazonClientService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return ResponseEntity.ok(amazonClientService.deleteFile(fileName));
    }

    @PostMapping("upload")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
    }

    @PostMapping("create-folder")
    public void createFolder(@RequestBody String folderName) {
        amazonClientService.createFolder(folderName);
    }

    @PostMapping("upload/aws")
    public void uploadAWS(@RequestParam("file") MultipartFile file) {
        amazonClientService.uploadFile(file);
    }
}
