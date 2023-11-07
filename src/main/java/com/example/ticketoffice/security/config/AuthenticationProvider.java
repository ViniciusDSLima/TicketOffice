package com.example.ticketoffice.security.config;

import com.example.ticketoffice.security.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuthenticationProvider {

        UserServiceImpl userServiceImpl;

        public Authentication getAuthentication(String email){
            UserDetails userDetails = userServiceImpl.loadUserByUsername(email);
            if(Objects.isNull(userDetails)) return null;

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
}
