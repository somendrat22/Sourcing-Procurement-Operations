package com.spo.core_app.services;

import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.models.Company;
import com.spo.core_app.models.Operation;
import com.spo.core_app.models.Role;
import com.spo.core_app.repostories.OperationRepository;
import com.spo.core_app.repostories.RoleRepository;
import com.spo.core_app.utilities.SystemUtility;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {

    private OperationService operationService;
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(OperationService operationService,
                       RoleRepository roleRepository){
        this.operationService = operationService;
        this.roleRepository = roleRepository;
    }

    public Role createMaintRole(Company company){
        List<Operation> operations = operationService.fetchAllProcurementCompanyMaintOperations();
        Role role = Role.builder()
                .roleId(SystemUtility.generate(SystemConstant.ROLE_ENTITY_NAME))
                .roleName(company.getLegalName() + "_" +  "MAINT")
                .createdBy("system")
                .updatedBy("system")
                .operations(operations)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return roleRepository.save(role);
    }
}
