package com.protocoloApp.config;

import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {
    
    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {

        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        final String username = obtainUsername(request);
        final String password = obtainPassword(request);
        
        if (username == null || password == null) {
            throw new BadCredentialsException("user.notfound");
        }
        
        return super.attemptAuthentication(request, response);
    }
}
