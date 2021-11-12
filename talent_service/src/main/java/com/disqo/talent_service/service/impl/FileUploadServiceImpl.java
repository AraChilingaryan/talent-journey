package com.disqo.talent_service.service.impl;

import com.disqo.talent_service.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        MultipartFile file1 = file;
        file.transferTo(new File("/Users/asyakhachatryan/Desktop/Test" + file.getOriginalFilename()));
    }
}
