package com.spo.core_app.controllers;

import com.spo.core_app.dtos.request.InviteEmployeeRequest;
import com.spo.core_app.dtos.request.LoginRequestDto;
import com.spo.core_app.dtos.responses.LoginResponseDto;
import com.spo.core_app.exceptions.UnAuthorizedException;
import com.spo.core_app.models.Employee;
import com.spo.core_app.services.AuthService;
import com.spo.core_app.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {

    private AuthService authService;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(AuthService authService,
                              EmployeeService employeeService){
        this.authService = authService;
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity loginEmp(
            @RequestBody LoginRequestDto loginRequestDto
            ){
        LoginResponseDto loginResponseDto = authService.authenticateEmployee(loginRequestDto);
        return new ResponseEntity(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/invite-emp")
    public ResponseEntity inviteEmp(
            @RequestBody InviteEmployeeRequest inviteEmployeeRequest,
            @RequestHeader String token
            ){
        try{
            Employee invitor = authService.isUserAllowedToPerformOperation(token, "INVITE_USER");
            Employee invitee = employeeService.inviteEmployee(inviteEmployeeRequest, invitor);
            return new ResponseEntity(invitee, HttpStatus.CREATED);
        }catch (UnAuthorizedException e){
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", e.getMessage());
            return new ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", e.getMessage());
            return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
