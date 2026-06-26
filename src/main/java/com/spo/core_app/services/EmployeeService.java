package com.spo.core_app.services;

import com.spo.core_app.constants.MessageConstant;
import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.enums.UserStatus;
import com.spo.core_app.exceptions.InvalidCredentialsException;
import com.spo.core_app.models.Company;
import com.spo.core_app.models.Employee;
import com.spo.core_app.models.ProcurementCompany;
import com.spo.core_app.models.Role;
import com.spo.core_app.repostories.EmployeeRepository;
import com.spo.core_app.utilities.SystemUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    private RoleService roleService;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(RoleService roleService,
                           EmployeeRepository employeeRepository){
        this.roleService = roleService;
        this.employeeRepository = employeeRepository;
    }

    // TCS_MAINT_USER
    public Employee createSuperAdminForCompany(Company company){
        Role role = roleService.createMaintRole(company);
        Employee emp = Employee.builder()
                .employeeId(SystemUtility.generate(SystemConstant.EMPLOYEE_ENTITY_NAME))
                .company(company)
                .password("Temp@123")
                .status(UserStatus.PENDING_ACTIVATION)
                .addressLine1(company.getAddressLine1())
                .addressLine2(company.getAddressLine2())
                .addressLine3(company.getAddressLine3())
                .email(company.getContactEmail())
                .roles(List.of(role))
                .businessUnit("ADMIN")
                .costCenter("ADMIN")
                .firstName(company.getLegalName() + "_" + "MAINT")
                .build();
        // SUPER_ADMIN_ROLE
        return employeeRepository.save(emp);
    }

    public Employee validateEmployeeCredentials(String email,
                                                String password){
        Employee employee = employeeRepository.findByEmail(email);
        if(employee != null && employee.getPassword().equals(password)){
            return employee;
        }
        throw new InvalidCredentialsException(MessageConstant.INVALID_CREDENTIALS_MESSAGE);
    }


}
