package com.spo.core_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Table(name = "operations")
public class Operation extends GlobalRecord {
    private String id; // Human readable Id -> OPR-1, OPR-2
    @Column(unique = true, nullable = false)
    private String operationName;
    private String operationCategory;
}
