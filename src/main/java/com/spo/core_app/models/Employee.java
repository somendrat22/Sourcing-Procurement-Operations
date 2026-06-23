package com.spo.core_app.models;

import com.spo.core_app.enums.EmploymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public final class Employee extends User {

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String designation;

    private String department;

    private String costCenter;

    private String businessUnit;


    @Enumerated
    private EmploymentType employmentType;

    @ManyToOne
    private Employee manager;

    private BigDecimal approvalLimit;

    private Boolean procurementApprover;

    private Boolean financeApprover;
}
