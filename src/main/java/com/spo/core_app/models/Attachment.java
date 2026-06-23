package com.spo.core_app.models;

import com.spo.core_app.enums.AttachmentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
@Entity
public class Attachment extends GlobalRecord{
    private String attachmentId;
    private String attachmentUrl;
    private String attachmentType;
    private String attachmentDescription;
    private String fileOriginalName;
    private String fileSystemName;
}
