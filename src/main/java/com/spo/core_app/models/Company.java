package com.spo.core_app.models;

import com.spo.core_app.enums.CompanyStatus;
import com.spo.core_app.enums.CompanyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends GlobalRecord{
    private String companyId;
    private String legalName;
    private String displayName;
    @Enumerated
    private CompanyType companyType;
    @Enumerated
    private CompanyStatus companyStatus;
    private String mainLogoUrl;
    private String taxId;
    private String taxRegNumber;
    private String govtRegNumber;
    private String primaryContactNumber;
    private String contactName;
    private String contactEmail;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String country;
    @OneToMany
    private List<Activity> activities;
    @OneToMany
    private List<Attachment> attachments;
}
