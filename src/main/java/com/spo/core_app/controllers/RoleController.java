package com.spo.core_app.controllers;

import com.spo.core_app.dtos.request.CreateRoleRequest;
import com.spo.core_app.exceptions.UnAuthorizedException;
import com.spo.core_app.models.Employee;
import com.spo.core_app.models.Role;
import com.spo.core_app.services.AuthService;
import com.spo.core_app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private AuthService authService;
    private RoleService roleService;

    @Autowired
    public RoleController(AuthService authService,
                          RoleService roleService){
        this.authService = authService;
        this.roleService = roleService;
    }

    @PostMapping("/create-role")
    public ResponseEntity createRole(
            @RequestBody CreateRoleRequest createRoleRequest,
            @RequestHeader String token
            ){
        // We are going to verify that user is having the role which contains CREATE_ROLE
        try{
            Employee employee = authService.isUserAllowedToPerformOperation(token, "CREATE_ROLE");
            // Role Service
            Role role = roleService.createRole(createRoleRequest, employee);
            return new ResponseEntity(role, HttpStatus.CREATED);
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
