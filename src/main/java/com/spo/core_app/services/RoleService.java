package com.spo.core_app.services;

import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.dtos.request.CreateRoleRequest;
import com.spo.core_app.models.Company;
import com.spo.core_app.models.Employee;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Role createRole(CreateRoleRequest createRoleRequest,
                           Employee employee){

        List<String> operationNames = createRoleRequest.getOperationName();
        // Tp create actual role we need to get operations object
        List<Operation> operations = operationService.fetchOperationsByName(operationNames);
        Role role = Role.builder()
                .roleId(SystemUtility.generate("ROLE"))
                .roleName(employee.getCompany().getLegalName() + "_" + createRoleRequest.getRoleName())
                .operations(operations)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(employee.getEmail())
                .updatedBy(employee.getEmail())
                .build();
        roleRepository.save(role);

        return role;
    }

    public List<Role> fetchRolesById(List<UUID> roleIds){
        List<Role> roles = new ArrayList<>();
        for(UUID id : roleIds){
            roles.add(roleRepository.findById(id).orElse(null));
        }
        return roles;
    }
}
