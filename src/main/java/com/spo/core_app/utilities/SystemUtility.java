package com.spo.core_app.utilities;

import com.spo.core_app.models.GlobalRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;





@Service
public class SystemUtility {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static void setGlobalRecordProperties(GlobalRecord globalRecord,
                                                 String createdBy,
                                                 String updatedBy,
                                                 LocalDateTime createdAt,
                                                 LocalDateTime updatedAt){
        globalRecord.setCreatedAt(createdAt);
        globalRecord.setUpdatedAt(updatedAt);
        globalRecord.setCreatedBy(createdBy);
        globalRecord.setUpdatedBy(updatedBy);
    }

    public static String generate(String prefix){
        if (prefix == null || prefix.isBlank()) {
            throw new IllegalArgumentException("Prefix cannot be null or empty");
        }

        int randomNumber = 100 + RANDOM.nextInt(900);
        // Generates 100 -> 999

        return prefix.toUpperCase()
                .replaceAll("\\s+", "_")
                + "-"
                + randomNumber;
    }

    // COMPANY-001, ATTACHMENT-00

}
