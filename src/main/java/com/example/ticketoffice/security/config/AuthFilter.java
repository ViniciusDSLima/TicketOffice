package com.example.ticketoffice.security.config;

import com.example.ticketoffice.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {

    AuthenticationProvider authenticationProvider;

    TokenService tokenService;

    @SneakyThrows({ServletException.class, IOException.class})
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        String token = Objects.nonNull(authorization) ? authorization.replace("Bearer",  "") : null;

        if(Objects.nonNull(token) && validatedToken(token)){
            String email = tokenService.getClaims(token).get("email").toString();
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.getAuthentication(email));
        }

        filterChain.doFilter(request, response);

    }

    boolean validatedToken(String token){
        return tokenService.validateToken(token);
    }
}
