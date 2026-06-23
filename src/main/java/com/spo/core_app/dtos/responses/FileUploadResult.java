package com.spo.core_app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResult {
    private String fileId;
    private String fileLink;
    private Long fileSize;
    private String fileName;
    private String fileType;
}
