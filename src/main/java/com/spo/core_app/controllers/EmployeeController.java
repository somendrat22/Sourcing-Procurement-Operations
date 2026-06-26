package com.spo.core_app.controllers;

import com.spo.core_app.dtos.request.LoginRequestDto;
import com.spo.core_app.dtos.responses.LoginResponseDto;
import com.spo.core_app.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {

    private AuthService authService;

    @Autowired
    public EmployeeController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity loginEmp(
            @RequestBody LoginRequestDto loginRequestDto
            ){
        LoginResponseDto loginResponseDto = authService.authenticateEmployee(loginRequestDto);
        return new ResponseEntity(loginResponseDto, HttpStatus.OK);
    }


}
