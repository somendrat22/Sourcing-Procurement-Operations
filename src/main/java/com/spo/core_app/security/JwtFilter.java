package com.spo.core_app.security;

import com.spo.core_app.models.User;
import com.spo.core_app.utilities.Jwtutility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private Jwtutility jwtutility;

    @Autowired
    public JwtFilter(Jwtutility jwtutility){
        this.jwtutility = jwtutility;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            filterChain.doFilter(request, response); // If you will call doFilter without setting some kind of auth you will reject the request
            return;
        }
        token = token.trim();
        // We need to validate the token
        User user = jwtutility.validateToken(token);
        if(user == null){
            filterChain.doFilter(request, response); // If you will call doFilter without setting some kind of auth you will reject the request
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
