package com.example.course_registration_api.security;

import com.example.course_registration_api.repository.AdministratorRepository;
import io.micrometer.common.util.StringUtils;
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
import java.util.ArrayList;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtils jwtUtils;
    private AdministratorRepository administratorRepository;

    public JwtAuthenticationFilter(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println(request.getHeader("Authorization") + " " + "insideDoFilterInternal");

        String header = request.getHeader("Authorization");

        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7, header.length());
        System.out.println(token);
        System.out.println("Before sending to getClaims");
        Map<String, Object> claims = jwtUtils.getClaimsFromJwtToken(token);

        if (claims == null) {
            filterChain.doFilter(request, response);
            return;
        }

        int universityId = (int) claims.get("universityId");
        String password = (String) claims.get("password");

        if (!administratorRepository.validateAdministrator(universityId, password).isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                universityId, null, new ArrayList<>()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}