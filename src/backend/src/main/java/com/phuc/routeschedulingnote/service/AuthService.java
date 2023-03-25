package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.support.UserDetailsWithJwt;

public interface AuthService {
    UserDetailsWithJwt login(User user);
    void signup(User user);
}
