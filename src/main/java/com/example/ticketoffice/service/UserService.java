package com.example.ticketoffice.service;

import com.example.ticketoffice.entities.User;
import com.example.ticketoffice.repository.UserRepository;
import com.example.ticketoffice.request.LoginRequest;
import com.example.ticketoffice.request.UserRegisterRequest;
import com.example.ticketoffice.response.UserRegisterResponse;
import com.example.ticketoffice.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    AuthenticationManager authenticationManager;

    TokenService tokenService;

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public String login(LoginRequest loginRequest){
        var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = (User) authenticate.getAuthorities();

        return tokenService.generateToken(user.getEmail());
    }

    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest){
        String name = userRegisterRequest.name();
        String email = userRegisterRequest.email();
        String password = passwordEncoder.encode(userRegisterRequest.password());

        User user = userRepository.save(new User(name, email, password));

        return new UserRegisterResponse(user.getName(), user.getEmail());
    }
}
