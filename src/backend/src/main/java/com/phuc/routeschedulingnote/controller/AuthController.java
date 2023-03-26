package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.auth.JwtDto;
import com.phuc.routeschedulingnote.dto.auth.UserGetDto;
import com.phuc.routeschedulingnote.dto.auth.UserPostDto;
import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.security.UserDetailsImpl;
import com.phuc.routeschedulingnote.service.AuthService;
import com.phuc.routeschedulingnote.service.UserService;
import com.phuc.routeschedulingnote.support.UserDetailsWithJwt;
import com.phuc.routeschedulingnote.support.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    public ApiResponse<JwtDto> login(@RequestBody UserPostDto userPostDto) {

        User loggingUser = modelMapper.map(userPostDto, User.class);
        UserDetailsWithJwt userDetailsWithJwt = authService.login(loggingUser);

        UserDetailsImpl userDetails = userDetailsWithJwt.getUserDetails();
        String token = userDetailsWithJwt.getJwt();

        User loggedUser = userService.getUserFromUsername(userDetails.getUsername());

        UserGetDto userGetDto = modelMapper.map(loggedUser, UserGetDto.class);
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
