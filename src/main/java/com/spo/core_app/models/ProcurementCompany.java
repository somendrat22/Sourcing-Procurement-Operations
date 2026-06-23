package com.spo.core_app.models;

import com.spo.core_app.enums.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "procurement_companies")
@Entity
@SuperBuilder
public class ProcurementCompany extends Company{

    private String procurementCompanyId;
    // Procurement Organization Details
    private String procurementHead;
    private String procurementEmail;
    private String procurementPhone;

    // Financial Controls
    @Enumerated
    private Currency baseCurrency;
    private BigDecimal annualProcurementBudget;
    private BigDecimal availableBudget;

    // Approval Configuration
    private Boolean approvalRequired;
    private Integer approvalLevels;

    // Purchasing Controls
    private BigDecimal autoApprovalThreshold;
    private BigDecimal rfqRequiredThreshold;
    private BigDecimal rfpRequiredThreshold;

    // Procurement Policies
    private Boolean contractRequired;


    // ERP Integration
    private String erpSystem;
    private String erpCompanyCode;
    private String costCenterPrefix;

    // Procurement Metrics
    private Integer activeSuppliers;
    private Integer activeContracts;
    private Integer activePurchaseOrders;
    private Integer activePurchaseRequests;


    // Compliance
    private Boolean complianceReviewRequired;
    private Boolean legalReviewRequired;

    // Dates
    private LocalDate goLiveDate;
    private LocalDate lastAuditDate;
}
