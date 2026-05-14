package com.naukri.database_api.security;

import java.io.IOException;
import java.util.Collections;

import com.naukri.database_api.services.CustomersDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomersDetailsService customersDetailsService;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //bearerToken  gives = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        //aAnd the parameter will be the Authorization
        String bearerToken  = request.getHeader("Authorization");

        //step-1 extract token
        String token = null;
        String userName = null;
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            token = bearerToken.substring(7);
            userName = jwtUtil.extractUserNameFromToken(token);
        }

        //TODO validate token
        //TODO set token to context
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customersDetailsService.loadUserByUsername(userName);

            if(jwtUtil.validToken(token)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
