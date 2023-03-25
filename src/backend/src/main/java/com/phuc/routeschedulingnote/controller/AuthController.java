package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.auth.JwtDto;
import com.phuc.routeschedulingnote.dto.auth.UserGetDto;
import com.phuc.routeschedulingnote.dto.auth.UserPostDto;
import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.security.UserDetailsImpl;
import com.phuc.routeschedulingnote.service.AuthService;
import com.phuc.routeschedulingnote.support.UserDetailsWithJwt;
import com.phuc.routeschedulingnote.support.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    public ApiResponse<JwtDto> login(@RequestBody UserPostDto userPostDto) {

        User loggingUser = modelMapper.map(userPostDto, User.class);
        UserDetailsWithJwt userDetailsWithJwt = authService.login(loggingUser);

        UserDetailsImpl loggedUser = userDetailsWithJwt.getUserDetails();
        String token = userDetailsWithJwt.getJwt();

        Set<String> roles = loggedUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        UserGetDto userGetDto = new UserGetDto(
                loggedUser.getId(),
                loggedUser.getUsername(),
                roles
        );
        JwtDto jwtDto = new JwtDto(token, userGetDto);
        return ApiResponse.success(jwtDto);
    }

    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody UserPostDto userPostDto) {
        User signingUser = modelMapper.map(userPostDto, User.class);
        authService.signup(signingUser);
        return ApiResponse.success("Signup successfully");
    }
}
