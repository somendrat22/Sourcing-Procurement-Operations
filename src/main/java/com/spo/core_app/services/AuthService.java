package com.spo.core_app.services;

import com.spo.core_app.dtos.request.LoginRequestDto;
import com.spo.core_app.dtos.responses.LoginResponseDto;
import com.spo.core_app.exceptions.UnAuthorizedException;
import com.spo.core_app.models.Employee;
import com.spo.core_app.models.Operation;
import com.spo.core_app.models.Role;
import com.spo.core_app.utilities.Jwtutility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {

    private EmployeeService employeeService;
    private Jwtutility jwtutility;

    @Autowired
    public AuthService(EmployeeService employeeService,
                       Jwtutility jwtutility){
        this.employeeService = employeeService;
        this.jwtutility = jwtutility;
    }

    public LoginResponseDto authenticateEmployee(LoginRequestDto loginRequestDto){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        Employee employee = employeeService.validateEmployeeCredentials(email, password);
        // Generate the token -> Jwt Token
        String token =  jwtutility.generateJwtToken(employee);
        return LoginResponseDto.builder()
                .token(token)
                .loginTime(LocalDateTime.now())
                .build();
    }

    public Employee isUserAllowedToPerformOperation(
            String token,
            String operation
    ){
        Employee employee = jwtutility.validateToken(token);
        List<Role> roles = employee.getRoles();
        for(Role role : roles){
            for (Operation operation1 : role.getOperations()){
                if(operation1.getOperationName().equals(operation)){
                    return employee;
                }
            }
        }
        throw new UnAuthorizedException("User is not allowed to perform this operation");
    }

}
