package com.spo.core_app.utilities;

import com.spo.core_app.constants.SystemConstant;
import com.spo.core_app.models.Employee;
import com.spo.core_app.models.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Jwtutility {



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



}
