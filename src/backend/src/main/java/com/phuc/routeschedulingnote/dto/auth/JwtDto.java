package com.phuc.routeschedulingnote.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDto {

    @JsonProperty("token")
    private String jwt;

    @JsonProperty("user")
    private UserGetDto userGetDto;
}
