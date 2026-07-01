package com.spo.core_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier_company")
public class SupplierCompany {

    // Supplier Details
    @Id
    @Column(unique = true, nullable = false)
    private String supplierId;              // SUP-000001


    private String supplierType;


    private String supplierStatus;


    private String supplierCategory;


    private String supplierTier;

    // Business Information
    private Integer yearEstablished;

    private Integer employeeCount;

    private String website;

    private String description;

    // Financial Information
    private Double annualRevenue;

    private String currency;

    private String paymentTerms;

    // Banking Information
    private String bankName;

    private String accountHolderName;

    private String accountNumber;

    private String ifscCode;

    private String swiftCode;

    // Business Capability
    private String primaryIndustry;

    private String secondaryIndustry;

    private String productsOffered;

    private String servicesOffered;

    private String manufacturingLocations;

    private String warehouseLocations;

    // Performance Metrics
    private Double supplierRating;

    private Integer completedOrders;

    private Integer activeContracts;

    private Integer totalRFQsParticipated;

    private Integer totalRFPsParticipated;

    private Integer totalPOsReceived;

    // Compliance
    private Boolean gstVerified;

    private Boolean taxVerified;

    private Boolean bankVerified;

    private Boolean kycVerified;

    private Boolean documentsVerified;

    private Boolean approved;

    // Approval
    private String approvedByEmployeeId;

    private LocalDateTime approvedOn;

    private String rejectionReason;

    // Portal Settings
    private Boolean selfServiceEnabled;

    private Boolean notificationsEnabled;
}
