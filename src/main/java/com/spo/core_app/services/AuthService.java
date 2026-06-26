package com.spo.core_app.services;

import com.spo.core_app.dtos.request.LoginRequestDto;
import com.spo.core_app.dtos.responses.LoginResponseDto;
import com.spo.core_app.models.Employee;
import com.spo.core_app.utilities.Jwtutility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
