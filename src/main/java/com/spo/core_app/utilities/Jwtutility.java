package com.spo.core_app.utilities;

import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.models.Employee;
import com.spo.core_app.models.Role;
import com.spo.core_app.models.User;
import com.spo.core_app.services.EmployeeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Jwtutility {

    private EmployeeService employeeService;

    @Autowired
    public Jwtutility(EmployeeService employeeService){
        this.employeeService = employeeService;
    }



    private final Key key = Keys.hmacShaKeyFor(SystemConstant.JWT_SECRET_PASSWORD.getBytes());


    public String generateJwtToken(Employee employee){

        // Claims -> User Email & Roles
        // Email -> Subject & Roles is the claim
        List<Role> roles = employee.getRoles();
        List<String> roleNames = new ArrayList<>();
        for(Role role: roles){
            roleNames.add(role.getRoleName());
        }

        return Jwts.builder()
                .setSubject(employee.getEmail())
                .claim("roles", roleNames)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SystemConstant.JWT_TOKEN_EXPIRATION_TIME))
                .signWith(key)
                .compact();

    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractSubjectFromJwt(String token){
        Claims claims = this.extractAllClaims(token);
        return claims.getSubject();
    }




    public Employee validateToken(String token){
        // decrypt token -> Email & List<String> roles
        String subject = this.extractSubjectFromJwt(token);
        return employeeService.getEmployeeByEmail(subject);
    }



}
