package com.phuc.routeschedulingnote.support;

import com.phuc.routeschedulingnote.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsWithJwt {

    private String jwt;
    private UserDetailsImpl userDetails;
}
