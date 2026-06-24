package com.spo.core_app.services;

import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.dtos.request.ProcurementCompanyRegistrationDto;
import com.spo.core_app.dtos.responses.FileUploadResult;
import com.spo.core_app.enums.AttachmentType;
import com.spo.core_app.models.Attachment;
import com.spo.core_app.models.ProcurementCompany;
import com.spo.core_app.repostories.AttachmentRepository;
import com.spo.core_app.repostories.ProcurementCompanyRepository;
import com.spo.core_app.strategy.MultiMediaServiceStrategy;
import com.spo.core_app.transformers.CompanyAdapter;
import com.spo.core_app.utilities.SystemUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProcurementCompanyService {

    private CompanyAdapter companyAdapter;
    private EmployeeService employeeService;
    private ProcurementCompanyRepository procurementCompanyRepository;
    private MultiMediaServiceStrategy multiMediaServiceStrategy;
    private AttachmentRepository attachmentRepository;


    @Autowired
    public ProcurementCompanyService(CompanyAdapter companyAdapter,
                                     ProcurementCompanyRepository procurementCompanyRepository,
                                     MultiMediaServiceStrategy multiMediaServiceStrategy,
                                     AttachmentRepository attachmentRepository,
                                     EmployeeService employeeService){
        this.companyAdapter = companyAdapter;
        this.procurementCompanyRepository = procurementCompanyRepository;
        this.multiMediaServiceStrategy = multiMediaServiceStrategy;
        this.attachmentRepository = attachmentRepository;
        this.employeeService = employeeService;
    }

    public ProcurementCompany registerProcurementCompany(
            ProcurementCompanyRegistrationDto procurementCompanyRegistrationDto,
            MultipartFile companyRegCertificate,
            MultipartFile companyLogo
    ){
       ProcurementCompany procurementCompany =  companyAdapter.mapProcurementCompanyDtoToModel(procurementCompanyRegistrationDto);
       // ImageKit Upload Document Flow -> Generic Flow
       MultiMediaService multiMediaService = multiMediaServiceStrategy.getService(SystemConstant.IMAGE_KIT_SERVICE_NAME);
       FileUploadResult companyRegDocResult = multiMediaService.uploadDocument(companyRegCertificate, SystemConstant.PROCUREMENT_COMPANY_DOC_BASE_PATH + "/" + procurementCompany.getSysId() + "/", "companyRegistrationDoc");
       Attachment companyRegAttachment  = Attachment.builder()
               .attachmentId(SystemUtility.generate(SystemConstant.ATTACHMENT_ENTITY_NAME))
               .attachmentType(companyRegDocResult.getFileType())
               .attachmentDescription("Company reg. doc")
               .attachmentUrl(companyRegDocResult.getFileLink())
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
               .createdBy(SystemConstant.APPLICATION_USER_NAME)
               .createdBy(SystemConstant.APPLICATION_USER_NAME)
               .build();
       companyRegAttachment = attachmentRepository.save(companyRegAttachment);
       FileUploadResult companyLogoResult = multiMediaService.uploadDocument(companyRegCertificate, SystemConstant.PROCUREMENT_COMPANY_DOC_BASE_PATH + "/" + procurementCompany.getSysId() + "/", "companyLogo");
       Attachment companyLogoAttachment = Attachment.builder()
               .attachmentId(SystemUtility.generate(SystemConstant.ATTACHMENT_ENTITY_NAME))
               .attachmentType(companyRegDocResult.getFileType())
               .attachmentDescription("Company logo")
               .attachmentUrl(companyLogoResult.getFileLink())
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
               .createdBy(SystemConstant.APPLICATION_USER_NAME)
               .createdBy(SystemConstant.APPLICATION_USER_NAME)
               .build();
        companyLogoAttachment = attachmentRepository.save(companyLogoAttachment);
        List<Attachment> attachments = List.of(companyRegAttachment, companyLogoAttachment);
        procurementCompany.setAttachments(attachments);
        procurementCompanyRepository.save(procurementCompany);
        // Create super admin user for the company

        employeeService.createSuperAdminForCompany(procurementCompany);

        return procurementCompany;

    }
}
