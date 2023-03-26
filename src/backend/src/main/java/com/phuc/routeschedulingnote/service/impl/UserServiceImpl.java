package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.exception.CoreApiException;
import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.repository.UserRepository;
import com.phuc.routeschedulingnote.security.UserDetailsImpl;
import com.phuc.routeschedulingnote.service.UserService;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import com.phuc.routeschedulingnote.support.error.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ErrorType notFoundUserError = new ErrorType(
                HttpStatus.NOT_FOUND,
                ExitCode.E2001,
                "Username not found"
        );
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new CoreApiException(notFoundUserError));
    }

    public User getUserFromUsername(String username) {
        ErrorType notFoundUserError = new ErrorType(
                HttpStatus.NOT_FOUND,
                ExitCode.E2001,
                "Username not found"
        );
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CoreApiException(notFoundUserError));
    }
}
