package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.User;

public interface UserService {
    User getUser();
    User getUserFromUsername(String username);
}
