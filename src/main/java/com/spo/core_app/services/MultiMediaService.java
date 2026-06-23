package com.spo.core_app.services;

import com.spo.core_app.dtos.responses.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface MultiMediaService {
    public FileUploadResult uploadDocument(MultipartFile file, String path, String fileName);
    public void getImage();
    public void deleteImage();
}
