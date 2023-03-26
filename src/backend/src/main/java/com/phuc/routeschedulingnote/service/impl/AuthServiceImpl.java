package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.exception.CoreApiException;
import com.phuc.routeschedulingnote.model.Role;
import com.phuc.routeschedulingnote.model.RoleEnum;
import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.repository.RoleRepository;
import com.phuc.routeschedulingnote.repository.UserRepository;
import com.phuc.routeschedulingnote.security.JwtUtils;
import com.phuc.routeschedulingnote.security.UserDetailsImpl;
import com.phuc.routeschedulingnote.service.AuthService;
import com.phuc.routeschedulingnote.support.UserDetailsWithJwt;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import com.phuc.routeschedulingnote.support.error.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetailsWithJwt login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new UserDetailsWithJwt(jwt, userDetails);
    }

    @Override
    public void signup(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (userRepository.existsByUsername(username)){
            ErrorType existedUser = new ErrorType(
                    HttpStatus.CONFLICT,
                    ExitCode.E2000,
                    "Existed user with username = " + username);
            throw new CoreApiException(existedUser);
        }

        User encodedUser = new User();
        encodedUser.setUsername(username);
        encodedUser.setPassword(passwordEncoder.encode(password));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER);
        roles.add(userRole);
        encodedUser.setRoles(roles);

        userRepository.save(encodedUser);
    }
}
