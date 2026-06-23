package com.spo.core_app.services;


import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.dtos.responses.FileUploadResult;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageKitService implements MultiMediaService {

    private ImageKit imageKit;

    @Autowired
    public ImageKitService(ImageKit imageKit){
        this.imageKit = imageKit;
    }

    @Override
    public FileUploadResult uploadDocument(MultipartFile file, String path, String fileName) {
        FileUploadResult result = null;
        for(int i = 0; i < SystemConstant.FILE_UPLOAD_RETRY_ATTEMPTS; i++){
            try{
                byte [] fileBytes = file.getBytes();
                // Before uploading the document -> FileCreateRequest
                FileCreateRequest fileCreateRequest = new FileCreateRequest(fileBytes, fileName);
                fileCreateRequest.setFolder(path);
                // Upload file on imagekit
                io.imagekit.sdk.models.results.Result imageKitResult = imageKit.upload(fileCreateRequest);
                result = FileUploadResult.builder()
                        .fileName(imageKitResult.getName())
                        .fileId(imageKitResult.getFileId())
                        .fileLink(imageKitResult.getUrl())
                        .fileSize(imageKitResult.getSize())
                        .fileType(imageKitResult.getFileType())
                        .build();
                return result;
            }catch (Exception e){
                log.error(String.format("%d upload attempt failed because of following reason: %s", (i+1), e.getMessage()));
            }
        }
        return result;
    }

    @Override
    public void getImage() {

    }

    @Override
    public void deleteImage() {

    }
}
