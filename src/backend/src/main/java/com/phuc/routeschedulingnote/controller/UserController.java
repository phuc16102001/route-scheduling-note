package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.auth.UserGetDto;
import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.service.UserService;
import com.phuc.routeschedulingnote.support.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/user")
    ApiResponse<UserGetDto> getUser() {
        User user = userService.getUser();
        UserGetDto userGetDto = modelMapper.map(user, UserGetDto.class);
        return ApiResponse.success(userGetDto);
    }
}
