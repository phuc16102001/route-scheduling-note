package com.phuc.routeschedulingnote.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserPostDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
