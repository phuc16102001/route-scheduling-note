package com.phuc.routeschedulingnote.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class UserGetDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("roles")
    private Set<RoleGetDto> roles;
}
