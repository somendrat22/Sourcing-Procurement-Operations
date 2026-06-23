package com.spo.core_app.services;

import com.spo.core_app.dtos.responses.FileUploadResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AwsService implements MultiMediaService{
    @Override
    public FileUploadResult uploadDocument(MultipartFile file, String path, String fileName) {
        return null;
    }

    @Override
    public void getImage() {

    }

    @Override
    public void deleteImage() {

    }
}
